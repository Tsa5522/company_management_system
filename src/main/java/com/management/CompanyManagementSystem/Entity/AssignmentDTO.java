package com.management.CompanyManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Assignment assignment;
    private List<Integer> employeeIds;

}

