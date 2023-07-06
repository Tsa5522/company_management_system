package com.management.CompanyManagementSystem.Mapper;

import com.management.CompanyManagementSystem.Entity.LogBook;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface LogbookMapper {
    void insertActivity(LogBook logBook);
    List<LogBook> getRecordPerWeek(Timestamp timestamp);
    List<LogBook> getRecordById(int id);
}
