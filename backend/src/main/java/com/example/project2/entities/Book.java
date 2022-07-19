package com.example.project2.entities;

import lombok.Builder;

import javax.persistence.Id;
@Builder
public class Book {
    @Id
    private int id;
    private String title;
    private String author;
    private String genre;
    public Book(){

    }
    public Book(int id, String title, String author,String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }
    public Book(String title, String author,String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}' + " \n ";
    }
}
