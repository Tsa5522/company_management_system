package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Entity.LogBook;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import com.management.CompanyManagementSystem.Service.LogbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class userController {
    final
    EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final LogbookService logbookService;

    public userController(EmployeeService employeeService, PasswordEncoder passwordEncoder, LogbookService logbookService) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
        this.logbookService = logbookService;
    }

    @GetMapping("/currentRole")
    public ResponseEntity<?> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findUserByEmail(auth.getName());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @DeleteMapping("/deleteEmp/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        LogBook logBook = new LogBook();
        logBook.setOperationDetails("员工"+ employeeService.findUserById(id).getFullName() +"被删除");
        logBook.setOperationType("员工变动相关");
        logBook.setOperationUser(SecurityContextHolder.getContext().getAuthentication().getName());
        logBook.setOperationTimestamp(new Timestamp(new Date().getTime()));
        logbookService.logOperation(logBook);
        boolean success = employeeService.deleteUserByID(id);
        if (success) {
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addUser")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        employeeService.addEmp(emp);
        LogBook logBook = new LogBook();
        logBook.setOperationDetails("新建员工："+ emp.getFullName());
        logBook.setOperationType("员工变动相关");
        logBook.setOperationUser(SecurityContextHolder.getContext().getAuthentication().getName());
        logBook.setOperationTimestamp(new Timestamp(new Date().getTime()));
        logbookService.logOperation(logBook);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PutMapping("editUser")
    public ResponseEntity<?> editEmployee(@RequestBody Employee emp) {
        employeeService.editEmp(emp);
        LogBook logBook = new LogBook();
        logBook.setOperationDetails("员工"+ emp.getFullName() + "信息被改动");
        logBook.setOperationType("员工变动相关");
        logBook.setOperationUser(SecurityContextHolder.getContext().getAuthentication().getName());
        logBook.setOperationTimestamp(new Timestamp(new Date().getTime()));
        logbookService.logOperation(logBook);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @PostMapping("forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody Employee emp) {
        Employee employee = employeeService.findUserByEmail(emp.getEmail());
        employee.setPassword(passwordEncoder.encode(emp.getPassword()));
        employeeService.editPassword(employee);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @PostMapping("editPassword")
    public ResponseEntity<?> editPassword(@RequestBody Employee emp) {
        Employee employee = employeeService.findUserByEmail(emp.getEmail());
        if (!passwordEncoder.matches(emp.getFullName(), employee.getPassword())){
            return new ResponseEntity<>("Incorrect old password", HttpStatus.BAD_REQUEST);
        } else {
            employee.setPassword(passwordEncoder.encode(emp.getPassword()));
            employeeService.editPassword(employee);
            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
        }
    }


}
