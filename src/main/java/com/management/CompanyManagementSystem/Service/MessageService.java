package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.Message;
import com.management.CompanyManagementSystem.Mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }
    public void sendMessage(Message msg) {
        messageMapper.saveNewMessage(msg);
    }
    public List<Message> getMessages(int id){
        return messageMapper.getMessages(id);
    }
    public Message findMessageByID(long messageID){
        return messageMapper.findMessage(messageID);
    }
}
