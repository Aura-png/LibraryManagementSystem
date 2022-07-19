package com.example.project2.controller;

import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Login;
import com.example.project2.entities.User;
import com.example.project2.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) throws SQLException {
        System.out.println(book);
        if(adminService.addBook(book) == null){
           return new ResponseEntity(HttpStatus.RESET_CONTENT);
       }

       return ResponseEntity.ok(book);
    }

    @DeleteMapping ("/deleteBook/{title}")
    public ResponseEntity deleteBook(@PathVariable String title){
        if(!adminService.deleteBook(title)){
            System.out.println(title);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
       // ArrayList<Book> booksList = adminService.showBooks();
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/deleteBorrow/{userId}")
    public ResponseEntity deleteBorrow(@PathVariable int userId){
        System.out.println(userId);
        if(!adminService.deleteBorrow(userId)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            //ArrayList<Borrow> borrowList = adminService.showBorrows();

        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping("/showBooks")
    public ResponseEntity<ArrayList<Book>> showBooks(){
        ArrayList<Book> booksList = adminService.showBooks();
        if(booksList == null){
            return new ResponseEntity(HttpStatus.RESET_CONTENT);

        }
        return ResponseEntity.ok(booksList);
    }
    @RequestMapping("/showUsers")
    public ResponseEntity<ArrayList<User>> showUsers(){
        ArrayList<User> usersList = adminService.showUsers();
        if(usersList == null){
            return new ResponseEntity(HttpStatus.RESET_CONTENT);

        }
        return ResponseEntity.ok(usersList);
    }
    @RequestMapping("/showBorrows")
    public ResponseEntity<ArrayList<Borrow>> showBorrows(){
        ArrayList<Borrow> borrowsList = adminService.showBorrows();
        if(borrowsList == null){
            return new ResponseEntity(HttpStatus.RESET_CONTENT);

        }
        return ResponseEntity.ok(borrowsList);
    }
    @GetMapping("findBookById/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id){
        Book book = adminService.findBookById(id);
        if(book == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(book);
    }
    @GetMapping("/findBookByTitle/{title}")
    public ResponseEntity<List<Book>> findBookByTitle(@PathVariable String title){
        List<Book> booksList = adminService.findBookByTitle(title);
        if(booksList == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(booksList);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if(adminService.updateBook(book.getAuthor(),book) == null){
            //System.out.println(book);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("/login")
    public Login login(@RequestBody User user){
        return adminService.login(user.getEmail(), user.getPassword());
    }




}
