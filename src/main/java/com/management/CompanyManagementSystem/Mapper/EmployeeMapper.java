package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    Employee findUserByEmail(String email);
}
