package com.management.CompanyManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int gender;
    private int departmentID;
    private int roleID;

    public Role getRole(){
        return roleID == 0 ? Role.ROLE_ADMIN : Role.ROLE_USER;
    }
}
