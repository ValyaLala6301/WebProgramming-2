package main.BookShop.service;

import main.BookShop.model.Book;
import main.BookShop.model.InfoBook;
import main.BookShop.model.Genres;

import java.util.List;

public interface BookService {
    String SERVICE_NAME = "BookService";

    List<Book> getAll();

    Book getBookById(long id);
    Book createBook(String Title, String Author);
    void updateBook (Book book);
    boolean removeBook(Book book);

    List<InfoBook> getInfoAboutBooks(Book book);

    InfoBook creatInformBook(Book book, int year, Genres genre, String information, String quote);

    void updateInformBook(Book book, InfoBook inform);

    boolean removeInform(Book book, InfoBook inform);


}
