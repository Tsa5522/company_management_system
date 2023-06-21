package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    void saveNewMessage(Message msg);
}
