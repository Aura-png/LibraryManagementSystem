package com.example.project2.repository;

import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Review;
import com.example.project2.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserRepositoryInterface {
    String insertStatementString = "INSERT INTO borrow(userId, bookId, period) " + "VALUES(?,?,?)";
    String showStatementString = "SELECT * FROM book";
    String insertReviewStatementString = "INSERT INTO review(bookId, userId, reviewMessage) " + "VALUES(?,?,?)";
    String loginStatementString = "SELECT FROM user WHERE email=? and password=?";
    String registerStatementString = "INSERT INTO user(name, address, email, password) " + "VALUES(?,?,?,?)";
    String verifyString = "SELECT password FROM user WHERE email = ?";
    String showReviewStatementString = "SELECT * FROM review";
    Borrow borrowBook(Borrow borrow) throws SQLException;
    ArrayList<Book> showBooks();
    List<Book> findBookByTitle(String title);
    Review addReview(Review review);
    User register(User user);
    boolean login(String email, String password);
    List<Review> showReviews();
}
