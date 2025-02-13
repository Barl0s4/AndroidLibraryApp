package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproj.databinding.MainActivityBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MainActivityBinding binding;
    private AccountBookdb db;
    private List<AccountsDb> accountList;
    private Intent i;
    @Override
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountBookdb.getInstance(this);
        db.seed();
        accountList = db.getAccountDAO().getAllAccounts();


        binding.CreateAcc.setOnClickListener(this);
        binding.PlaceHold.setOnClickListener(this);
        binding.ManageSt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.CreateAcc){
            i = new Intent(MainActivity.this, CreateAccount.class);
        }else if(v.getId() == R.id.PlaceHold){
            i = new Intent(MainActivity.this, PlaceHolds.class);
        }else if (v.getId() == R.id.ManageSt){
            i = new Intent(MainActivity.this, ManageSystem.class);
        }
        startActivity(i);
    }
}
