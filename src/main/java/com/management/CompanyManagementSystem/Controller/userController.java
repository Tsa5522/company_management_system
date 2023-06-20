package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    @DeleteMapping("/deleteEmp/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        employeeService.deleteUserByID(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("addUser")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        employeeService.addEmp(emp);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
