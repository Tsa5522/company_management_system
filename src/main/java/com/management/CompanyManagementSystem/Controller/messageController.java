package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Message;
import com.management.CompanyManagementSystem.Service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/message")
public class messageController {
    private final MessageService messageService;

    public messageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping("/getMessages/{id}")
    public ResponseEntity<?> getMessages(@PathVariable String id) {
        int empId = Integer.parseInt(id);
        Collection<Message> allMessages = messageService.getMessages(empId);
        return new ResponseEntity<>(allMessages, HttpStatus.OK);
    }

    @RequestMapping("/findMessage/{messageID}")
    public ResponseEntity<?> findMessage(@PathVariable String messageID) {
        int msgID = Integer.parseInt(messageID);
        Message msg = messageService.findMessageByID(msgID);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
