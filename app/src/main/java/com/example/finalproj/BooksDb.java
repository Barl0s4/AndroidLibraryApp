package com.example.finalproj;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "books")
public class BooksDb {
    @PrimaryKey(autoGenerate = true)
    private int bookId;
    private String title;
    private String author;
    private String genre;



    public BooksDb(String title, String author, String genre){
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @NonNull
    @Override
    public String toString(){
        return title;
    }

}
