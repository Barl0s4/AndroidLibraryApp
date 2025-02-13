package com.example.finalproj;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BooksDao {
    @Insert
    long addBooks(BooksDb books);
    @Query("delete from books where author = :author and title =:title")
    void removeBookByAuthorAndTitle(String author, String title);
    @Query("select count(*) from books")
    int count();
    @Query("select * from books Where genre is :genre")
    List<BooksDb> getBooksByGenre(String genre);
    @Query("select count(*) from books Where author is :author")
    int getBooksByAuthor(String author);
    @Query("select count(*) from books Where title is :title")
    int getBooksByTitle(String title);
}
