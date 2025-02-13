package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproj.databinding.ManageSystemsBinding;

public class ManageSystem extends AppCompatActivity implements View.OnClickListener {
    private AccountBookdb db;
    private Bundle b;
    private AccountsDao accountsDao;
    private ManageSystemsBinding binding;
    private int counterUsedForLogin = 0;
    @Override
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = ManageSystemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountBookdb.getInstance(this);
        accountsDao = db.getAccountDAO();

        binding.login.setOnClickListener(this);
        binding.GoBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.GoBack) {
            Intent back = new Intent(ManageSystem.this, MainActivity.class);
            startActivity(back);
        }
        if (v.getId() == R.id.login){
            String Username = binding.Usernamelog.getText().toString();
            String Password = binding.Passwordlog.getText().toString();
            if(Username.isEmpty() || Password.isEmpty()){
                Toast.makeText(ManageSystem.this, "Username and/or Password are empty", Toast.LENGTH_SHORT).show();
            }
            if(Username.equals("!admin2") && Password.equals("!admin2")){
                Intent i = new Intent(ManageSystem.this, LogActivity.class);
                startActivity(i);
            }
            if(checkForAccount(Username, Password)){
                Toast.makeText(this, "Account is not an admin", Toast.LENGTH_SHORT).show();
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
                return true;
            }
        }
        counterUsedForLogin++;
        return false;
    }
    private boolean repeatBehavior(){
        return counterUsedForLogin == 2;
    }
    private void goHome(){
        Intent i = new Intent(ManageSystem.this, MainActivity.class);
        startActivity(i);
    }
}
