package com.management.CompanyManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogBook {
    private int id;
    private String operationType;
    private String operationUser;
    private String operationDetails;
    private Timestamp operationTimestamp;
}
