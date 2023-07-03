package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Assignment;
import com.management.CompanyManagementSystem.Entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AssignmentMapper {
    void create(Assignment assignment);
    void assignAssignmentToUser(Map<String, Object> map);

    List<Assignment> getAllAssignments();

    List<Assignment> findAssignmentsByEmployeeId(int EmployeeID);
}
