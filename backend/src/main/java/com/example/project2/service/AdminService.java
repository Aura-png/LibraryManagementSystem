package com.example.project2.service;

import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Login;
import com.example.project2.entities.User;
import com.example.project2.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Book addBook(Book book)throws SQLException{
        return adminRepository.addBook(book);
    }

    public boolean deleteBook(String title){
        return adminRepository.deleteBook(title);
    }

    public ArrayList<Book> showBooks(){
        ArrayList<Book> booksList;
        booksList = adminRepository.showBooks();

        return booksList;
    }
    public ArrayList<Borrow> showBorrows(){
        ArrayList<Borrow> borrowsList;
        borrowsList = adminRepository.showBorrows();

        return borrowsList;
    }
    public ArrayList<User> showUsers(){
        ArrayList<User> usersList;
        usersList = adminRepository.showUsers();

        return usersList;
    }
    public boolean deleteBorrow(int userId){

        return adminRepository.deleteBorrow(userId);
    }
    public Book findBookById(int id) {

        return adminRepository.findBookById(id);
    }
    public List<Book> findBookByTitle(String title) {

        return adminRepository.findBookByTitle(title);
    }
    public Book updateBook(String author,Book book){
        System.out.println(book);
        return adminRepository.updateBook(author,book);
    }
    public Login login(String email, String password){

        return adminRepository.login(email,password);
    }
}
