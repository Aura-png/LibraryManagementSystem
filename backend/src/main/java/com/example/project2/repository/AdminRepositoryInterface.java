package com.example.project2.repository;



import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Login;
import com.example.project2.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AdminRepositoryInterface {
    String insertStatementString = "INSERT INTO book(title, author, genre) " + "VALUES(?,?,?)";
    String insertStatementInLibraryString = "INSERT into librarycontent (libraryId, bookId)" + " VALUES (?,?)";
    String deleteStatementString = "DELETE FROM book where title = ?";
    String showBooksStatementString = "SELECT * FROM book";
    String showBorrowsStatementString = "SELECT * FROM borrow";
    String showUsersStatementString = "SELECT * FROM user";
    String showStatementString = "SELECT * FROM book where title=?";
    String deleteBorrowStatementString = "DELETE FROM borrow where userId = ?";
    String verifyIdString = "SELECT userId FROM borrow WHERE userId = ?";
    String updateBookStatementString = "UPDATE book SET author=? WHERE title=?";
    String showBookStatement2Id = "SELECT * FROM book WHERE id=?";
    String showUserStatementString = "SELECT * FROM user WHERE email=?";

    Book addBook(Book book) throws SQLException;
    boolean deleteBook(String title);
    ArrayList<Book> showBooks();
    boolean deleteBorrow(int userId);
    ArrayList<User> showUsers();
    ArrayList<Borrow> showBorrows();
    Book updateBook(String author,Book book);
    Book findBookById(int id);
    User findUserByEmail(String email);
    List<Book> findBookByTitle(String title);
    Login login(String email, String password);
}
