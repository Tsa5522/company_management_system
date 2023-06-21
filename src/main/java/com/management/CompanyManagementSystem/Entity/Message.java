package com.management.CompanyManagementSystem.Entity;

import com.management.CompanyManagementSystem.Service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private long messageID;

    private Timestamp sendTime;
    private int fromUser;
    private String fromUserName;
    private String toUserName;

    private int toUser;
    private String title;
    private String content;
}
