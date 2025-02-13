package com.example.finalproj;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Type;

@Entity(tableName = "Logs")
public class Logs {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Account;
    private String ReservationNum;
    private String type;
    public Logs(String Account, String ReservationNum, String type){
        this.Account = Account;
        this.ReservationNum = ReservationNum;
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getReservationNum() {
        return ReservationNum;
    }

    public void setReservationNum(String reservationNum) {
        ReservationNum = reservationNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
