package com.example.finalproj;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {AccountsDb.class, BooksDb.class, Logs.class}, version = 28, exportSchema = false)
public abstract class AccountBookdb extends RoomDatabase {
    public static final String ACCOUNT_TABlE = "ACCOUNT_TABLE";
    public static volatile AccountBookdb instance;
    public abstract AccountsDao getAccountDAO();
    public abstract BooksDao getBookDAO();
    public abstract LogsDao getLogsDAO();
    public static synchronized AccountBookdb getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,AccountBookdb.class, "Accounts.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public void seed(){
        runInTransaction(() -> {
            if(getAccountDAO().count() == 0){
                AccountsDb admin = new AccountsDb("!admin2","!admin2");
                AccountsDb hShuard = new AccountsDb("hShuard","m@thl3t3");
                AccountsDb bMishra = new AccountsDb("bMishra","bioN@no");
                AccountsDb shirleyBee = new AccountsDb("shirleyBee","carmel2Chicago");
                getAccountDAO().insertAccounts(admin, hShuard, bMishra, shirleyBee);

                getBookDAO().addBooks(new BooksDb( "Meditaions","Marcus Aurelius","Self-Help" ));
                getBookDAO().addBooks( new BooksDb( "Letetrs to a Young Poet","Rainer Maria Rilke","Self-Help" ));
                getBookDAO().addBooks( new BooksDb( "Circe","Madeline Miller","Historical Fantasy" ));
                getBookDAO().addBooks( new BooksDb( "Intro to Mashine Learning","Anita Faul","Computer Science" ));
                getBookDAO().addBooks( new BooksDb( "The Fox Wife","Anita Faul","Historical Fantasy" ));
            }
        });

    }
}
