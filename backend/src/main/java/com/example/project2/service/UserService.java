package com.example.project2.service;

import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Review;
import com.example.project2.entities.User;
import com.example.project2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Borrow borrowBook(Borrow borrow) throws SQLException {
        return userRepository.borrowBook(borrow);
    }
    public ArrayList<Book> showBooks(){
        ArrayList<Book> booksList = null;
        booksList = userRepository.showBooks();

        return booksList;
    }

    public List<Book> findBookByTitle(String title){
        return userRepository.findBookByTitle(title);
    }
    public Review addReview(Review review){
        return userRepository.addReview(review);
    }
    public User register(User user){
        return userRepository.register(user);
    }
    public List<Review> showReviews(){
        return userRepository.showReviews();
    }
}
