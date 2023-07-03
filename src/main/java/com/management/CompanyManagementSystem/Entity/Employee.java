package com.management.CompanyManagementSystem.Entity;

import com.management.CompanyManagementSystem.Service.DepartmentService;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @ManyToMany(mappedBy = "employees")
    private Set<Assignment> assignments = new HashSet<>();
    private int id;
    private String fullName;
    private String password;
    private String email;
    private int gender;
    private int departmentID;
    private int roleID;

    public Role getRole(){
        return roleID == 0 ? Role.ROLE_ADMIN : Role.ROLE_USER;
    }
    public String getRoleString() {
        return roleID == 0 ? "管理员" : "用户";
    }
    public String getDepartmentString() {
        if (departmentID == 0) {
            return "后台测试";
        } else if (departmentID == 1) {
            return "销售部门";
        } else if (departmentID == 2) {
            return "人力资源";
        } else {
            return "技术部门";
        }
    }
    public String getGenderString() {
        return gender == 0 ? "女" : "男";
    }
}
