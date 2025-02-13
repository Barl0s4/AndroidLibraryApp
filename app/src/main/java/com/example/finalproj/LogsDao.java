package com.example.finalproj;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogsDao {
    @Insert
    void addLog(Logs Log);
    @Query("select * from Logs")
    List<Logs> AllLogs();
}
