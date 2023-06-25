package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Service.DepartmentService;
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

@Controller
public class loginController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public loginController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    @GetMapping({"/", "/login"})
    public String getLogin() {
        return "login";
    }


    @GetMapping("/index")
    public String getIndexPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        return "index";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Employee employee) {
        boolean registeredEmployee = employeeService.register(employee);
        if (registeredEmployee) {
            return ResponseEntity.ok("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration Failed!");
        }
    }


}
