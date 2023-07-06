package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.LogBook;
import com.management.CompanyManagementSystem.Service.DepartmentService;
import com.management.CompanyManagementSystem.Service.LogbookService;
import org.springframework.ui.Model;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class loginController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    private final LogbookService logbookService;
    @Autowired
    public loginController(EmployeeService employeeService, DepartmentService departmentService, LogbookService logbookService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.logbookService = logbookService;
    }
    @GetMapping({"/", "/login"})
    public String getLogin() {
        return "login";
    }


    @GetMapping("/index")
    public String getIndexPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("id", employee.getId());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        model.addAttribute("departments", departmentService.findAll());
        return "index";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Employee employee) {
        boolean registeredEmployee = employeeService.register(employee);
        LogBook logBook = new LogBook();
        logBook.setOperationDetails("新员工注册："+ employee.getFullName());
        logBook.setOperationType("员工变动相关");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        logBook.setOperationUser(employeeService.findUserByEmail(userName).getId());
        logBook.setOperationTimestamp(new Timestamp(new Date().getTime()));
        logbookService.logOperation(logBook);
        if (registeredEmployee) {
            return ResponseEntity.ok("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration Failed!");
        }
    }


}
