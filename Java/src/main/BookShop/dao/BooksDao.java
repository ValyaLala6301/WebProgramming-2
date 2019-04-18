package main.BookShop.dao;

import main.BookShop.model.Book;
import java.util.List;
import java.util.function.Consumer;

public interface BooksDao {
    List<Book> getAll();
    /*Создаем Книгу, у которой есть Название, Автор и ID*/
    Book getById(long id);
    Book create(String Title, String Author);
    void update(Book book);
    boolean delete(long id);
}
