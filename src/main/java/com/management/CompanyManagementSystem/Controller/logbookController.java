package com.management.CompanyManagementSystem.Controller;

import com.management.CompanyManagementSystem.Entity.LogBook;
import com.management.CompanyManagementSystem.Service.LogbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/logbook")
public class logbookController {
    private final LogbookService logbookService;

    public logbookController(LogbookService logbookService) {
        this.logbookService = logbookService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getRecordLastWeek(){
        Collection<LogBook> logBooks = logbookService.getWeeklyRecord();
        return new ResponseEntity<>(logBooks, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable int id){
        Collection<LogBook> logBooks = logbookService.getRecordById(id);
        return new ResponseEntity<>(logBooks, HttpStatus.OK);
    }
}
