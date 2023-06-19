package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService{
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee findUserByEmail(String email) {
        return employeeMapper.findUserByEmail(email);
    }

    public boolean passwordMatches(Employee employee, String rawPassword) {
        return passwordEncoder.matches(rawPassword, employee.getPassword());
    }


    public boolean register(Employee employee) {
        boolean isSuccess = false;
        Optional<Employee> existingEmployee = Optional.ofNullable(employeeMapper.findUserByEmail(employee.getEmail()));
        if (existingEmployee.isPresent()) {
            return isSuccess;
        } else {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeMapper.register(employee);
            isSuccess = true;
            return isSuccess;
        }
    }
}



