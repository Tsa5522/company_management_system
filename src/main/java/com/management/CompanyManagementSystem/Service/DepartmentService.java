package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.Department;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService{
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public String findDepartment(Employee employee) {
        return departmentMapper.findDepartmentByID(employee.getDepartmentID());
    }

    public List<Department> findAll(){
        return departmentMapper.findAll();
    }
}
