package com.management.CompanyManagementSystem.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    String findDepartmentByID(int departmentID);
}
