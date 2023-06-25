package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    Employee findUserByEmail(String email);
    Employee findUserByID(int id);
    void register(Employee employee);
    List<Employee> findUserList();
    void deleteUser(int id);
    void addEmployee(Employee employee);
    void editEmployee(Employee employee);
    void editPassword(@Param("password") String password, @Param("id") int id);
}
