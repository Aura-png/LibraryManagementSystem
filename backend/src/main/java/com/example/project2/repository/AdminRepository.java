package com.example.project2.repository;

import com.example.project2.connection.ConnectionFactory;
import com.example.project2.entities.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository implements AdminRepositoryInterface {
    public Library library = new Library(0);

    public Book addBook(Book book) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, book.getTitle());
            insertStatement.setString(2, book.getAuthor());
            insertStatement.setString(3, book.getGenre());
            insertStatement.executeUpdate();
            resultSet = insertStatement.getGeneratedKeys();
            if(resultSet.next()) {
                System.out.println((book));
                return book;
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }

        return null;
    }
    public List<Book> findBookByAuthor(String author){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        ResultSet resultSet;
        List<Book> booksList = new ArrayList<Book>();
        Book book = null;
        try{
            showStatement = dbConnection.prepareStatement(showStatementString);
            showStatement.setString(1,author);
            //resultSet = showStatement.executeQuery(showStatementString);
            resultSet = showStatement.executeQuery();
            while (resultSet.next()){

                //System.out.print(resultSet.getString(2) + " - ");
                //System.out.print(resultSet.getString(3) + " - ");
                //System.out.print(resultSet.getString(4));
                //System.out.println();
                book = new Book(Integer.parseInt(resultSet.getString("id")
                ), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("genre"));
                booksList.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(book);
        return booksList;
    }
    public List<Book> findBookByTitle(String title){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        ResultSet resultSet;
        List<Book> booksList = new ArrayList<Book>();
        Book book = null;
        try{
            showStatement = dbConnection.prepareStatement(showStatementString);
            showStatement.setString(1,title);
            //resultSet = showStatement.executeQuery(showStatementString);
            resultSet = showStatement.executeQuery();
            while (resultSet.next()){

                    //System.out.print(resultSet.getString(2) + " - ");
                    //System.out.print(resultSet.getString(3) + " - ");
                    //System.out.print(resultSet.getString(4));
                    //System.out.println();
                    book = new Book(Integer.parseInt(resultSet.getString("id")
                    ), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("genre"));
                    booksList.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(book);
        return booksList;
    }
    public boolean deleteBook(String title){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        ResultSet resultSet = null;
        int deletedBookId = -1;
        try{
            if(findBookByTitle(title) != null){
                deleteStatement = dbConnection.prepareStatement(deleteStatementString,Statement.RETURN_GENERATED_KEYS);
                library.setBooksNumber(library.getBooksNumber() - 1);
                deleteStatement.setString(1, title);
                //deletedBookId = book.getId();
                int affectedRows = deleteStatement.executeUpdate();
                System.out.println(affectedRows);
            }else{
                System.out.println("Cartea nu exista!");
                return false;
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

        return true;
    }
    public ArrayList<Book> showBooks(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement showStatement = null;
        ResultSet resultSet;

        ArrayList<Book> showBooks = new ArrayList<>();
        try{
            showStatement = dbConnection.createStatement();
            resultSet = showStatement.executeQuery(showBooksStatementString);
            while (resultSet.next()){
                //System.out.print(resultSet.getString(2) + " - ");
                //System.out.print(resultSet.getString(3) + " ");
                //System.out.println();
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
    public ArrayList<Borrow> showBorrows(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement showStatement = null;
        ResultSet resultSet;

        ArrayList<Borrow> showBorrows = new ArrayList<>();
        try{
            showStatement = dbConnection.createStatement();
            resultSet = showStatement.executeQuery(showBorrowsStatementString);
            while (resultSet.next()){
                //System.out.print(resultSet.getString(2) + " - ");
                //System.out.print(resultSet.getString(3) + " ");
                //System.out.println();
                Borrow borrow = new Borrow(Integer.parseInt(resultSet.getString("id")),Integer.parseInt(resultSet.getString("userId")
                ), Integer.parseInt(resultSet.getString("bookId")), resultSet.getString("period"));
                showBorrows.add(borrow);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }

        return showBorrows;
    }
    public Login login(String email, String password){
        User admin = findUserByEmail(email);
        if(admin == null){
            return null;
        }
        System.out.println(admin.getEmail().equals("admin@yahoo.com"));
        System.out.println(admin.getEmail());
        return new Login(admin.getId(),(admin.getEmail().equals("admin@yahoo.com") ? "admin": "user"));
    }
    public User findUserByEmail(String email){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        ResultSet resultSet;
        User user = null;
        try{
            showStatement = dbConnection.prepareStatement(showUserStatementString);
            showStatement.setString(1,email);
            //resultSet = showStatement.executeQuery(showStatementString);
            resultSet = showStatement.executeQuery();
            while (resultSet.next()){

                //System.out.print(resultSet.getString(2) + " - ");
                //System.out.print(resultSet.getString(3) + " - ");
                //System.out.print(resultSet.getString(4));
                //System.out.println();
                user = new User(Integer.parseInt(resultSet.getString("id")),resultSet.getString("name"), resultSet.getString("email"),
                        resultSet.getString("password"),resultSet.getString("address"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(user);
        return user;
    }
    public Book findBookById(int id){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        ResultSet resultSet;
        Book book = null;
        try{
            showStatement = dbConnection.prepareStatement(showBookStatement2Id);
            showStatement.setString(1,String.valueOf(id));
            resultSet = showStatement.executeQuery();
            if(resultSet.next()){
                book = new Book(Integer.parseInt(resultSet.getString("id")
                ), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("genre"));
                //System.out.println(resultSet.getString(1));
                //System.out.println(resultSet.getString("title"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }

        return book;
    }
    public Book updateBook(String author,Book book) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try{
            updateStatement = dbConnection.prepareStatement(updateBookStatementString);
                if(findBookByTitle(book.getTitle()) != null) {
                    updateStatement.setString(1, author);
                    updateStatement.setString(2, book.getTitle());
                    int rowAffected = updateStatement.executeUpdate();
                    //System.out.println(rowAffected);
                }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("nu exista");
            return null;
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        //System.out.println(book);
        return book;
    }
    public ArrayList<User> showUsers(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement showStatement = null;
        ResultSet resultSet;

        ArrayList<User> showUsers = new ArrayList<>();
        try{
            showStatement = dbConnection.createStatement();
            resultSet = showStatement.executeQuery(showUsersStatementString);
            while (resultSet.next()){
                //System.out.print(resultSet.getString(2) + " - ");
                //System.out.print(resultSet.getString(3) + " ");
                //System.out.println();
                User user = new User(Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("email"),resultSet.getString("password"));
                showUsers.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }

        return showUsers;
    }
    public boolean deleteBorrow(int userId){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        ResultSet resultSet = null;
        int deletedBookId = -1;
        try{
            if(!verifyId(userId)){
                deleteStatement = dbConnection.prepareStatement(deleteBorrowStatementString,Statement.RETURN_GENERATED_KEYS);
                library.setBooksNumber(library.getBooksNumber() - 1);
                deleteStatement.setString(1,String.valueOf(userId));
                //deletedBookId = book.getId();
                int affectedRows = deleteStatement.executeUpdate();
                System.out.println(affectedRows);
                return true;
            }else{
                System.out.println("Cartea nu exista!");
                return false;
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

        return true;
    }
    private boolean verifyId(int userId){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement ;
        ResultSet rs ;
        try{
            statement = connection.prepareStatement(verifyIdString);
            statement.setString(1, String.valueOf(userId));
            rs = statement.executeQuery();
            if (!rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static void main(String[] args) throws SQLException {
        AdminRepository adminRepository = new AdminRepository();
        //adminRepository.updateBook("new name",new Book("vb","aa","fantasy"));
        //System.out.println(adminRepository.verifyTitle("vb"));
        //System.out.println(adminRepository.findBookById(24));
        //System.out.println(adminRepository.showBorrows());
        //adminRepository.addBook(new Book("f","author","classic"));
        //adminRepository.addBook(new Book("fff","author","classic"));
        //adminRepository.addBook(new Book("ffff","author","classic"));
        //adminRepository.updateBook("Daniela",new Book("a","Aura","fantasy"));
        System.out.println(adminRepository.login("admin@yahoo.com","admin"));
        adminRepository.findBookByTitle("b");
    }
}
