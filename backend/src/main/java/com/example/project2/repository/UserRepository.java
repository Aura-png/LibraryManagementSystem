package com.example.project2.repository;

import com.example.project2.connection.ConnectionFactory;
import com.example.project2.entities.Book;
import com.example.project2.entities.Borrow;
import com.example.project2.entities.Review;
import com.example.project2.entities.User;
import com.example.project2.utilis.EmailValidation;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {

    public Borrow borrowBook(Borrow borrow) throws SQLException {
        long id = 0;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, String.valueOf(borrow.getUserId()));
            insertStatement.setString(2, String.valueOf(borrow.getBookId()));
            insertStatement.setString(3, borrow.getPeriod());
            int affectedRows = insertStatement.executeUpdate();
            if (affectedRows > 0) {
                try {
                    resultSet = insertStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        id = resultSet.getLong(1);
                    }
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                } finally {
                    ConnectionFactory.close(insertStatement);
                    ConnectionFactory.close(dbConnection);
                }
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
        System.out.println(id);
        return borrow;

    }
    public ArrayList<Book> showBooks(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement showStatement = null;
        ResultSet resultSet;
        ArrayList<Book> showBooks = new ArrayList<>();

        try{
            showStatement = dbConnection.createStatement();
            resultSet = showStatement.executeQuery(showStatementString);
            while (resultSet.next()){
                Book book = new Book(Integer.parseInt(resultSet.getString("id")
                ), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("genre"));
                showBooks.add(book);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }

        return showBooks;
    }
    public List<Book> findBookByTitle(String title){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement showStatement = null;
        ResultSet resultSet;
        List<Book> booksList = new ArrayList<Book>();
        Book book = null;
        try{
            showStatement = dbConnection.createStatement();
            resultSet = showStatement.executeQuery(showStatementString);
            while (resultSet.next()){
                if(resultSet.getString(2).equals(title)){
                    //System.out.print(resultSet.getString(2) + " - ");
                    //System.out.print(resultSet.getString(3) + " - ");
                    //System.out.print(resultSet.getString(4));
                    //System.out.println();
                    book = new Book(Integer.parseInt(resultSet.getString("id")
                    ), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("genre"));
                    booksList.add(book);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }

        return booksList;
    }

    public Review addReview(Review review) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertReviewStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, String.valueOf(review.getBookId()));
            insertStatement.setString(2, String.valueOf(review.getUserId()));
            insertStatement.setString(3, review.getReviewMessage());
            insertStatement.executeUpdate();
            resultSet = insertStatement.getGeneratedKeys();
            if(resultSet.next()) {
                System.out.println((review));
                return review;
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }

        return null;
    }
    private int countRows(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        int counter = 0;
        try{
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM user");
            resultSet.next();
            counter = resultSet.getInt(1);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(counter);
        return counter + 1;
    }
    private boolean verifyTitle(String password){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            statement = connection.prepareStatement(verifyString);
            statement.setString(1, password);
            rs = statement.executeQuery();
            if (!rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User register(User user) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement registerStatement = null;
        ResultSet resultSet = null;
        try {
            registerStatement = dbConnection.prepareStatement(registerStatementString, Statement.RETURN_GENERATED_KEYS);
            if(EmailValidation.isEmailValid(user.getEmail()) && verifyTitle(user.getPassword())){
                registerStatement.setString(1, user.getName());
                registerStatement.setString(2, user.getAddress());
                registerStatement.setString(3, user.getEmail());
                registerStatement.setString(4, user.getPassword());
                registerStatement.executeUpdate();
                resultSet = registerStatement.getGeneratedKeys();
            }
        } catch (SQLException sqlException) {
            System.out.println("Adresa de email sau parola invalida!");
            System.out.println("Inregistrare nereusita!");
            return  null;
        }

        return user;
    }
    public boolean login(String email, String password){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement loginStatement = null;
        ResultSet resultSet = null;
        try{
            loginStatement = dbConnection.prepareStatement(loginStatementString);
            loginStatement.setString(1,email);
            loginStatement.setString(2,password);
            resultSet = loginStatement.executeQuery();
        }catch (SQLException sqlException){
            System.out.println("Logare esuata!");
            return false;
        }finally {
            ConnectionFactory.close(loginStatement);
            ConnectionFactory.close(dbConnection);
        }

        return true;
    }
    public List<Review> showReviews(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement showStatement = null;
        ResultSet resultSet;
        ArrayList<Review> showReviews = new ArrayList<>();

        try{
            showStatement = dbConnection.createStatement();
            resultSet = showStatement.executeQuery(showReviewStatementString);
            while (resultSet.next()){
                Review review = new Review(Integer.parseInt(resultSet.getString("id")),Integer.parseInt((resultSet.getString("bookId"))),
                        Integer.parseInt((resultSet.getString("userId"))),resultSet.getString("reviewMessage"));

                showReviews.add(review);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }

        return showReviews;
    }
    public static void main(String[] args) throws SQLException {
        UserRepository userController = new UserRepository();
        //userController.borrowBook(new Borrow(3,33,"28.02 - 3.03"));
       //    userController.borrowBook(new com.example.project.Borrow(3,2,"29.02 - 3.03"));
        //userController.deleteBorrow(new com.example.project.Borrow(3,2,"28.02 - 3.03"));
        //userController.showBooks();
        //userController.findBookByTitleAndAuthor("Circe1","Madeline Miller");
        //userController.addReview(new com.example.project.Review(23,"Madona","Super"));
        //userController.register(new User("Aura","auraaqa@gmail.com","12f34567","ddffd"));
        userController.addReview(new Review(44,44,"abc"));
    }
}
