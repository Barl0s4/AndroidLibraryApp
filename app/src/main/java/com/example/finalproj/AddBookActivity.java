package com.example.finalproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproj.databinding.AddBookActivityBinding;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener {
    private AccountBookdb db;
    private BooksDao booksDao;
    private AddBookActivityBinding binding;
    public void onCreate(Bundle savedInstant){
        super.onCreate(savedInstant);
        binding = AddBookActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AccountBookdb.getInstance(this);
        booksDao = db.getBookDAO();

        binding.confirm.setOnClickListener(this);
        binding.GoBack.setOnClickListener(this);

    }
    public void onClick(View v){
        if(v.getId() == R.id.GoBack){
            Intent back = new Intent(AddBookActivity.this, MainActivity.class);
            startActivity(back);
        }
        if(v.getId() == R.id.confirm){
            String author = binding.author.getText().toString();
            String title = binding.title.getText().toString();
            String genre = binding.genre.getText().toString();

            if(genreChecker(genre)){
                if(bookChecker(author, title)){
                    booksDao.addBooks(new BooksDb(title, author, genre));
                    Toast.makeText(this, "Book was made", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddBookActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }
    }
    private boolean genreChecker(String genre){
        if(genre.equals("Self-Help") || genre.equals("Historical Fantasy") || genre.equals("Computer Science")){
            return true;
        }
        Toast.makeText(this, "genre doesnt exist", Toast.LENGTH_SHORT).show();
        return false;
    }
    private boolean bookChecker(String author, String title){
        if(booksDao.getBooksByAuthor(author) != 0 ){
            if(booksDao.getBooksByTitle(title) != 0){
                return false;
            }
        }
        return true;
    }
}
