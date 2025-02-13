package com.example.finalproj;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountsDao {
    @Insert
    long addAccounts(AccountsDb accounts);

    @Insert
    long[] insertAccounts(AccountsDb... accounts);

    @Query("select * from ACCOUNT_TABlE")
    List<AccountsDb> getAllAccounts();

    @Query("select count(*) from ACCOUNT_TABLE")
    int count();

    @Query("SELECT COUNT(*) FROM " + AccountBookdb.ACCOUNT_TABlE + " WHERE username = :username")
    int countUsername(String username);
    @Query("SELECT COUNT(*) FROM " + AccountBookdb.ACCOUNT_TABlE + " WHERE password = :password")
    int countPassword(String password);



}