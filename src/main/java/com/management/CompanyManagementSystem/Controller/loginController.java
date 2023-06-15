package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
