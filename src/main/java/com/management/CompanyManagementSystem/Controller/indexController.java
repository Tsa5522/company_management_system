package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Department;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.DepartmentService;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class indexController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public indexController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
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
}
