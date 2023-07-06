package com.management.CompanyManagementSystem.Service;

import com.management.CompanyManagementSystem.Entity.LogBook;
import com.management.CompanyManagementSystem.Mapper.LogbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LogbookService {
    @Autowired
    private final LogbookMapper logbookMapper;

    public LogbookService(LogbookMapper logbookMapper) {
        this.logbookMapper = logbookMapper;
    }

    public void logOperation(LogBook logBook){
        logbookMapper.insertActivity(logBook);
    }

    public List<LogBook> getWeeklyRecord() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        return logbookMapper.getRecordPerWeek(timestamp);
    }
    public List<LogBook> getRecordById(int id) {
        return logbookMapper.getRecordById(id);
    }




}
