package com.example.finalproj;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AccountBookdb.ACCOUNT_TABlE)
public class AccountsDb {
    @PrimaryKey(autoGenerate = true)
    private int accountId;
    @ColumnInfo(name = "username")
    private String user;
    @ColumnInfo(name = "password")
    private String pass;
    public AccountsDb( String user, String pass){
        this.user = user;
        this.pass = pass;
    }
    public int getAccountId() {
        return accountId;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public String toString(){
        return user;
    }

}
