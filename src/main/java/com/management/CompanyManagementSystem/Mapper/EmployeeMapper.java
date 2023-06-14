package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Employee;

public interface EmployeeMapper {
    Employee findUserByEmail(String email);
}
