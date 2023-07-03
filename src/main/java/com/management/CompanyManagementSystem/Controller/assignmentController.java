package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Assignment;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Service.AssignmentService;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/assignments")
public class assignmentController {

    private final AssignmentService assignmentService;
    private final EmployeeService employeeService;

    public assignmentController(AssignmentService assignmentService, EmployeeService employeeService) {
        this.assignmentService = assignmentService;
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public String createAssignment(@ModelAttribute Assignment assignment, @RequestParam List<Integer> employeeIds) {
        assignmentService.createAssignment(assignment, employeeIds);
        return "redirect:/assignments";
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAllAssignments(Principal principal) {
        String currentUserName = principal.getName();
        Employee employee = employeeService.findUserByEmail(currentUserName);
        if(employee.getRoleID() == 0) {
            return new ResponseEntity<>(assignmentService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(assignmentService.findAssignmentsByEmployeeId(employee.getId()), HttpStatus.OK);
        }
    }

}
