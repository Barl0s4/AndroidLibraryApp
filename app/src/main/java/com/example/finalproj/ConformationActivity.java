package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproj.databinding.ConformationActivityBinding;

import java.util.Random;

public class ConformationActivity extends AppCompatActivity implements View.OnClickListener {
    private ConformationActivityBinding binding;
    private String AccountUser, BookTitle, BookAuthor;
    private LogsDao logsDao;
    private AccountBookdb db;
    private BooksDao booksDao;
    private int Num = randomNum();
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = ConformationActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AccountBookdb.getInstance(this);
        logsDao = db.getLogsDAO();
        booksDao = db.getBookDAO();

        String reservationNum = "Reservation Number: "+ Num;

        Intent intent = getIntent();
        AccountUser = intent.getStringExtra("Account");
        BookTitle = intent.getStringExtra("selectedBookTitle");
        BookAuthor = intent.getStringExtra("selectedBookAuthor");
        String accUsername = "Account User: "+AccountUser;
        String bookTitle = "Book Title: "+ BookTitle;

        logsDao.addLog(new Logs(AccountUser, reservationNum, "Book Hold"));
        removeBook();


        binding.accUser.setText(accUsername);
        binding.bookTitle.setText(bookTitle);
        binding.reservationNum.setText(reservationNum);
        binding.GoBack.setOnClickListener(this);
    }
    public int randomNum(){
        Random rand = new Random();
        return  Num = rand.nextInt(90000)+10000;
    }
    public void removeBook(){
        booksDao.removeBookByAuthorAndTitle(BookAuthor, BookTitle);
        Toast.makeText(this,BookTitle+ " was removed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.GoBack){
            Intent back = new Intent(ConformationActivity.this, MainActivity.class);
            back.putExtra("selectedBook", BookTitle);
            startActivity(back);
        }

    }
}
