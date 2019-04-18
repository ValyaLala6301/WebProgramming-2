package main.BookShop.dao;

import main.BookShop.model.InfoBook;
import main.BookShop.model.Genres;

import java.util.List;

public interface InfoBookDao {
    List<InfoBook> getInfoAboutBooks(long bookId);

    InfoBook createInform(long bookId, int year, Genres genre, String information, String quote);

    void updateInform(InfoBook infobook, long bookId);

    boolean removeInform(long infoId, long bookId);


}
