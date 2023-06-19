package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService{
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public String findDepartment(Employee employee) {
        return departmentMapper.findDepartmentByID(employee.getDepartmentID());
    }
}
