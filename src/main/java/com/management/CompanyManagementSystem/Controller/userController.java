package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    final
    EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    public userController(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
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
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        employeeService.addEmp(emp);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("editUser")
    public ResponseEntity<?> editEmployee(@RequestBody Employee emp) {
        employeeService.editEmp(emp);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
