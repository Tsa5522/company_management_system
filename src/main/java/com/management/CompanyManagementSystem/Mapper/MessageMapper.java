package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    void saveNewMessage(Message msg);
    List<Message> getMessages(int id);
    Message findMessage(long messageID);
}
