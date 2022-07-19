package com.example.project2.controller;

import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Review;
import com.example.project2.entities.User;
import com.example.project2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<Borrow>  borrowBook(@RequestBody Borrow borrow) throws  SQLException {
        if(userService.borrowBook(borrow) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(borrow);
    }

    @GetMapping("/showBooks")
    public ResponseEntity<ArrayList<Book>> showBooks(){
        ArrayList<Book> booksList = userService.showBooks();
        if(booksList == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(booksList);
    }
    @GetMapping("/findBookByTitle/{title}")
    public ResponseEntity<List<Book>> findBookByTitleAndAuthor(@PathVariable String title){
        List<Book> booksList = userService.findBookByTitle(title);
        if(booksList == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(booksList);
    }

    @PostMapping("/addReview")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        if(userService.addReview(review) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(review);
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if(userService.register(user) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/showReviews")
    public ResponseEntity<List<Review>> showReviews(){
        List<Review> reviews = userService.showReviews();
        if(reviews == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(reviews);
    }


}
