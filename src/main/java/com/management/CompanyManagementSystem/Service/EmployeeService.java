package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.Assignment;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final AssignmentService assignmentService;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder, AssignmentService assignmentService) {
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
        this.assignmentService = assignmentService;
    }

    public Employee findUserByEmail(String email) {
        return employeeMapper.findUserByEmail(email);
    }

    public Employee findUserById(int id) {
        return employeeMapper.findUserByID(id);
    }
    public boolean passwordMatches(Employee employee, String rawPassword) {
        return passwordEncoder.matches(rawPassword, employee.getPassword());
    }


    public boolean register(Employee employee) {
        boolean isSuccess = false;
        Optional<Employee> existingEmployee = Optional.ofNullable(employeeMapper.findUserByEmail(employee.getEmail()));
        if (existingEmployee.isPresent()) {
            return isSuccess;
        } else {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeMapper.register(employee);
            isSuccess = true;
            return isSuccess;
        }
    }

    public List<Employee> findUserList() {
        return employeeMapper.findUserList();
    }
    public boolean deleteUserByID(int id) {
        employeeMapper.deleteFromAssignmentUsers(id);
        employeeMapper.deleteUser(id);
        if (findUserById(id) != null) {
            return false;
        }
        return true;
    }

    public void addEmp(Employee emp) {
        employeeMapper.addEmployee(emp);
    }
    public void editEmp(Employee emp) {employeeMapper.editEmployee(emp);}
    public void editPassword(Employee emp) {
        employeeMapper.editPassword(emp.getPassword(), emp.getId());
    }
    public List<Employee> findEmpByDepartment(int departmentID) {
        return employeeMapper.findUserByDepartment(departmentID);
    }
    public int getEmployeeCountByDepartment(int departmentID) {
        List<Employee> employeeList = findEmpByDepartment(departmentID);
        return employeeList.size();
    }
}



