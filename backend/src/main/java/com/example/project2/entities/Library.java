package com.example.project2.entities;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int booksNumber;
    public List<String> genres;
    public Library(){
        genres = new ArrayList<>();
        genres.add("classic");
        genres.add("fantasy");
        genres.add("horror");
        genres.add("literary fiction");
        genres.add("detective and mistery");
    }
    public Library(int booksNumber) {
        this.booksNumber = booksNumber;
        genres = new ArrayList<>();
        genres.add("classic");
        genres.add("fantasy");
        genres.add("horror");
        genres.add("literary fiction");
        genres.add("detective and mistery");
    }

    public int getBooksNumber() {
        return booksNumber;
    }

    public void setBooksNumber(int booksNumber) {
        this.booksNumber = booksNumber;
    }

}
