package com.example.finalproj;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.finalproj.databinding.CreateAccountBinding;

import java.util.List;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {
    private CreateAccountBinding binding;
    private AccountsDao accountsDao;
    private AccountBookdb Database;
    private LogsDao logsDao;
    private int counterForUsedUsername;
    @Override
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = CreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Database = AccountBookdb.getInstance(this);
        accountsDao = Database.getAccountDAO();
        logsDao = Database.getLogsDAO();

        binding.CreateAccpg.setOnClickListener(this);
        binding.GoBack.setOnClickListener(this);

    }
    public void onClick(View v){
        if(v.getId() == R.id.GoBack){
            Intent back = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(back);
        }
        if(v.getId() == R.id.CreateAccpg){
            String Username = binding.Username.getText().toString();
            String Password = binding.Password.getText().toString();
            if(Username.isEmpty() || Password.isEmpty()){
                Toast.makeText(CreateAccount.this, "Username and/or Password are empty", Toast.LENGTH_SHORT).show();
                counterForUsedUsername++;
            } else if (!checkForUsedAccounts(Username)){
                counterForUsedUsername++;
            }else{
                createAccount(Username, Password);
                logsDao.addLog(new Logs("Account created", Username, "Created account"));
                Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_SHORT).show();
                goHome();
            }
            if(repeatBehavior()){
                Toast.makeText(this, "error going to main menu", Toast.LENGTH_SHORT).show();
                goHome();
            }
        }
    }
    private boolean checkForUsedAccounts(String username) {
        if(accountsDao.countUsername(username) != 0 ){
            Toast.makeText(this,"Username is taken", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void createAccount(String Username, String Password){
        Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_SHORT).show();
        accountsDao.addAccounts(new AccountsDb(Username, Password));
    }
    private boolean repeatBehavior(){
        return counterForUsedUsername == 2;
    }
    private void goHome(){
        Intent i = new Intent(CreateAccount.this, MainActivity.class);
        startActivity(i);
    }
}
