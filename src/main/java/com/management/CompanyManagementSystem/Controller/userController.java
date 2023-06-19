package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {
    final
    EmployeeService employeeService;

    public userController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/currentRole")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
