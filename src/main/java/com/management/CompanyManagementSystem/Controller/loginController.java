package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public loginController(EmployeeService employeeService, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping({"/", "/login"})
    public String getLogin() {
        return "login";
    }
    @GetMapping("/loginFail")
    public String getLoginFail() {
        return "loginFail";
    }

    @GetMapping("/index")
    public String getIndexPage() {
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
