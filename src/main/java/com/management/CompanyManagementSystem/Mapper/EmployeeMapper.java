package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    Employee findUserByEmail(String email);
    void register(Employee employee);
    List<Employee> findUserList();
    void deleteUser(int id);
    void addEmployee(Employee employee);
}
