package com.management.CompanyManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private long messageID;

    private LocalDateTime sendTime;
    private int fromUser;
    private int toUser;
    private String title;
    private String content;
}
