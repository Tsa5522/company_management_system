package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    String findDepartmentByID(int departmentID);
    List<Department> findAll();
}
