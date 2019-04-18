package main.BookShop.dao;

import main.BookShop.model.Book;
import main.BookShop.exception.BookServiceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBooksDao implements BooksDao {
    private final DataSource dataSource;

    public DataBooksDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from BOOKS")
        ) {
            if (rs == null)
                throw new SQLException("Unable to load contacts");

            List<Book> books = new ArrayList<>();
            while (rs.next())
                books.add(retrieveBooks(rs));
            return books;
        } catch (SQLException e) {
            throw new BookServiceException(e);
        }
    }

    private Book retrieveBooks(ResultSet rs) throws SQLException {
        return new Book(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
        );
    }

    @Override
    public Book getById(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("select id, Title, Author from BOOKS where id = ?")
        ) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return retrieveBooks(rs);
            }
        } catch (SQLException e) {
            throw new BookServiceException(e);
        }
    }

    @Override
    public Book create(String Title, String Author) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into books (Title, Author) values (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, Title);
            statement.setString(2, Author);
            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
                throw new SQLException("Unable to create book");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new Book(id, Title, Author);
                } else {
                    throw new SQLException("Creating book failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new BookServiceException(e);
        }
    }

    @Override
    public void update(Book book) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE BOOKS " +
                        "SET Title = ?, Author = ?" +
                        "WHERE id = ?");
        ) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setLong(3, book.getId());

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
                throw new BookServiceException("Unable to update book");
        } catch (SQLException e) {
            throw new BookServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE from BOOKS " +
                        "WHERE id = ?");
        ){
            statement.setLong(1, id);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
                throw new BookServiceException("Unable to delete book");
            return true;
        } catch (SQLException e) {
            throw new BookServiceException(e);
        }
    }
}
