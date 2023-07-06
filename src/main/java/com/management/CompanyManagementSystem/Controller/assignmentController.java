package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.AssignmentDTO;
import com.management.CompanyManagementSystem.Entity.Employee;
import com.management.CompanyManagementSystem.Entity.LogBook;
import com.management.CompanyManagementSystem.Service.AssignmentService;
import com.management.CompanyManagementSystem.Service.EmployeeService;
import com.management.CompanyManagementSystem.Service.LogbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/assignments")
public class assignmentController {

    private final AssignmentService assignmentService;
    private final EmployeeService employeeService;
    private final LogbookService logbookService;

    public assignmentController(AssignmentService assignmentService, EmployeeService employeeService, LogbookService logbookService) {
        this.assignmentService = assignmentService;
        this.employeeService = employeeService;
        this.logbookService = logbookService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        assignmentService.createAssignment(assignmentDTO.getAssignment(), assignmentDTO.getEmployeeIds());
        LogBook logBook = new LogBook();
        logBook.setOperationDetails("用户新发布了任务:" + assignmentDTO.getAssignment().getAssignmentName());
        logBook.setOperationType("限时项目相关");
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        logBook.setOperationUser(employeeService.findUserByEmail(userName).getId());
        logBook.setOperationTimestamp(new Timestamp(new Date().getTime()));
        logbookService.logOperation(logBook);
        return new ResponseEntity<>(assignmentDTO, HttpStatus.OK);
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
