package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproj.databinding.LogActivityBinding;

import java.util.List;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {
    private LogActivityBinding binding;
    private AccountBookdb db;
    private List<Logs> logs;
    private LogsAdapter logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LogActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountBookdb.getInstance(this);
        logs = db.getLogsDAO().AllLogs();

        logAdapter = new LogsAdapter(this, R.layout.item_logs, logs);
        binding.ListOfLogs.setAdapter(logAdapter);

        binding.Next.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Next) {
            Intent back = new Intent(LogActivity.this, AddBookActivity.class);
            startActivity(back);
        }
    }
}
