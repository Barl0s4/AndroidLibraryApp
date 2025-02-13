package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.finalproj.databinding.LoginActivityBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private AccountsDao accountsDao;
    private String selectedBookTitle, selectedBookAuthor;
    private AccountBookdb Database;
    private int counterUsedForLogin;
    private LoginActivityBinding binding;
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Database = AccountBookdb.getInstance(this);
        accountsDao = Database.getAccountDAO();

        Intent intent = getIntent();
        selectedBookTitle = intent.getStringExtra("selectedBookTitle");
        selectedBookAuthor = intent.getStringExtra("selectedBookAuthor");

        binding.login.setOnClickListener(this);
        binding.GoBack.setOnClickListener(this);
    }
    public void onClick(View v){
        if(v.getId() == R.id.GoBack){
            Intent back = new Intent(LoginActivity.this, PlaceHolds.class);
            startActivity(back);
        }
        if(v.getId() == R.id.login){
            String Username = binding.Usernamelog.getText().toString();
            String Password = binding.Passwordlog.getText().toString();
            if(Username.isEmpty() || Password.isEmpty()){
                Toast.makeText(LoginActivity.this, "Username and/or Password are empty", Toast.LENGTH_SHORT).show();
            }
            if(checkForAccount(Username, Password)){
                Intent i = new Intent(LoginActivity.this, ConformationActivity.class);
                i.putExtra("selectedBookTitle", selectedBookTitle);
                i.putExtra("selectedBookAuthor",selectedBookAuthor);
                i.putExtra("Account", Username);
                startActivity(i);
            }
            if(repeatBehavior()){
                Toast.makeText(this, "error going to main menu", Toast.LENGTH_SHORT).show();
                goHome();
            }
        }
    }
    private boolean checkForAccount(String username, String password) {
        if(accountsDao.countUsername(username) != 0) {
            if (accountsDao.countPassword(password) != 0) {
                Toast.makeText(LoginActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        counterUsedForLogin++;
        Toast.makeText(LoginActivity.this, "Account dose not exist try again", Toast.LENGTH_SHORT).show();
        return false;
    }
    private void goHome(){
        Intent i = new Intent(LoginActivity.this, PlaceHolds.class);
        startActivity(i);
    }
    private boolean repeatBehavior(){
        return counterUsedForLogin == 2;
    }
}
