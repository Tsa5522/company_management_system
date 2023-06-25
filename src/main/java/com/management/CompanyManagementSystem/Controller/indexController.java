package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Department;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.DepartmentService;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class indexController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public indexController(EmployeeService employeeService, DepartmentService departmentService,PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/allEmp")
    public String getEmployeeList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        Collection<Employee> employees = employeeService.findUserList();
        model.addAttribute("employees", employees);
        model.addAttribute("departmentS", departmentService);
        return "userList";
    }
    @GetMapping("/allEmp/addUser")
    public String addEmployeeToList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        Collection<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "addEmployee";
    }
    @GetMapping("/allEmp/editUser/{id}")
    public String editEmployee(Model model, @PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        Collection<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        int empId = Integer.parseInt(id);
        Employee editEmp = employeeService.findUserById(empId);
        model.addAttribute("editEmp", editEmp);
        model.addAttribute("encoder",passwordEncoder);
        return "editEmployee";
    }
    @GetMapping("/inbox")
    public String getMessageInbox(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        model.addAttribute("employee", employee);
        return "inbox";
    }
    @GetMapping("/inbox/send/{id}")
    public String sendMessage(Model model, @PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        model.addAttribute("employee", employee);
        int empId = Integer.parseInt(id);
        Employee toUser = employeeService.findUserById(empId);
        model.addAttribute("toUser", toUser);
        return "sendMessage";
    }

    @GetMapping("/messageDetail/{messageID}")
    public String messageDetail(Model model, @PathVariable String messageID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        model.addAttribute("employee", employee);
        long msgID = Integer.parseInt(messageID);
        model.addAttribute("msg", msgID);
        return "messageDetail";
    }

    @GetMapping("/forgotPassword")
    public String changePassword() {
        return "changePassword";
    }
    @GetMapping("/password")
    public String passwordSetting(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        model.addAttribute("currentEmp", employee);
        model.addAttribute("fullName", employee.getFullName());
        model.addAttribute("department",departmentService.findDepartment(employee));
        model.addAttribute("encoder",passwordEncoder);
        return "password";
    }
}
