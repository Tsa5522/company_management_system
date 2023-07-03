package com.management.CompanyManagementSystem.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    private int AssignmentID;
    private String AssignmentName;
    private String AssignmentDescription;
    private Timestamp AssignmentDeadline;
    @ManyToMany
    @JoinTable(
            name = "AssignmentUsers",
            joinColumns = @JoinColumn(name = "AssignmentID"),
            inverseJoinColumns = @JoinColumn(name = "EmployeeID"))
    private Set<Employee> employees = new HashSet<>();

}
