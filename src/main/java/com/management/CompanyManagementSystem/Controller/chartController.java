package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/charts")
public class chartController {
    private final EmployeeService employeeService;

    public chartController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{departmentId}")
    public List<Employee> getEmpInDepartment(@PathVariable String departmentId){
        int depID = Integer.parseInt(departmentId);
        return employeeService.findEmpByDepartment(depID);
    }
    @GetMapping("/employeeCount/{departmentID}")
    public int getCountInDepartment(@PathVariable String departmentID) {
        int dID = Integer.parseInt(departmentID);
        return employeeService.getEmployeeCountByDepartment(dID);
    }
}
