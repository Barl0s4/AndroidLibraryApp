package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Room;

import com.example.finalproj.databinding.PlaceHoldsBinding;

import java.util.List;

public class PlaceHolds extends AppCompatActivity  implements View.OnClickListener{
    private PlaceHoldsBinding binding;
    private AccountBookdb db;
    private Bundle b;
    @Override
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = PlaceHoldsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountBookdb.getInstance(this);
        b = getIntent().getExtras();

        int idValue = 0;
        binding.GoBack.setOnClickListener(this);

        Spinner genreSpinner = findViewById(R.id.Genre);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.Genre,
                android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(spinnerAdapter);

        if (b != null) {
            idValue = b.getInt("accountId");
            Toast.makeText(this, "Account ID: " + idValue, Toast.LENGTH_SHORT).show();
        }
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = parent.getItemAtPosition(position).toString();
                displayBooksByGenre(selectedGenre);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.GoBack) {
            Intent back = new Intent(PlaceHolds.this, MainActivity.class);
            startActivity(back);
        }
    }
    public void displayBooksByGenre(String genre){
        List<BooksDb> booksByGenre = db.getBookDAO().getBooksByGenre(genre);
        LinearLayout books = findViewById(R.id.books);
        books.removeAllViews();
        for (BooksDb booksDb : booksByGenre) {
            if(booksDb.getGenre().isEmpty()) {
                Toast.makeText(this,"No books for this genre", Toast.LENGTH_SHORT).show();
                
            }else{
                Button bookButton = new Button(this);
                bookButton.setText(booksDb.getTitle());
                bookButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(PlaceHolds.this, "Selected Book: " + booksDb.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PlaceHolds.this, LoginActivity.class);
                        i.putExtra("selectedBookTitle", booksDb.getTitle());
                        i.putExtra("selectedBookAuthor", booksDb.getAuthor());
                        startActivity(i);
                    }
                });
                books.addView(bookButton);
            }
        }
    }
}
