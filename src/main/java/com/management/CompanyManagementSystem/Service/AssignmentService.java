package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.Assignment;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Mapper.AssignmentMapper;
import com.management.CompanyManagementSystem.Mapper.EmployeeMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class AssignmentService {

    private final AssignmentMapper assignmentMapper;
    private final EmployeeMapper employeeMapper;

    public AssignmentService(AssignmentMapper assignmentMapper, EmployeeMapper employeeMapper) {
        this.assignmentMapper = assignmentMapper;
        this.employeeMapper = employeeMapper;
    }

    public void createAssignment(Assignment assignment, List<Integer> employeeIds) {
        Set<Employee> employees = null;
        for (int empID:
            employeeIds) {
            employees.add(employeeMapper.findUserByID(empID));
        }
        assignment.setEmployees(employees);
        assignmentMapper.create(assignment);
        for (Integer employeeId : employeeIds) {
            Map<String, Object> map = new HashMap<>();
            map.put("assignmentId", assignment.getAssignmentID());
            map.put("employeeId", employeeId);
            assignmentMapper.assignAssignmentToUser(map);
        }
    }

    public List<Assignment> getAll() {
        return assignmentMapper.getAllAssignments();
    }

    public List<Assignment> findAssignmentsByEmployeeId(int id) {
        return assignmentMapper.findAssignmentsByEmployeeId(id);
    }

}
