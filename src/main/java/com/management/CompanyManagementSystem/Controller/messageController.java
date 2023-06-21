package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.Message;
import com.management.CompanyManagementSystem.Service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
