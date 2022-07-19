package com.example.project2.entities;

public class Review {
    private int id;
    private int bookId;
    private int userId;
    private String reviewMessage;

    public Review(int bookId, int userId, String reviewMessage) {
        this.bookId = bookId;
        this.userId = userId;
        this.reviewMessage = reviewMessage;
    }
    public Review(int id,int bookId, int userId, String reviewMessage) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.reviewMessage = reviewMessage;
    }
    public Review() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(String userName) {
        this.userId = userId;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId='" + userId + '\'' +
                ", reviewMessage='" + reviewMessage + '\'' +
                '}';
    }
}
