package com.example.project2.entities;

public class Borrow {
    private int id;
    private int userId;
    private int bookId;
    private String period;

    public Borrow(int id,int userId, int bookId, String period) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.period = period;
    }
    public Borrow(int userId,int bookId, String period) {
        this.userId = userId;
        this.bookId = bookId;
        this.period = period;
    }

    public Borrow() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", period='" + period + '\'' +
                '}';
    }
}
