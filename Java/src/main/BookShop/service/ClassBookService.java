package main.BookShop.service;

import main.BookShop.dao.BooksDao;
import main.BookShop.dao.InfoBookDao;
import main.BookShop.model.Book;
import main.BookShop.model.InfoBook;
import main.BookShop.model.Genres;

import java.util.List;

public class ClassBookService  implements BookService{
    private final BooksDao booksDao;
    private final InfoBookDao infobookDao;

    public ClassBookService(BooksDao booksDao, InfoBookDao infobookDao) {
        this.booksDao = booksDao;
        this.infobookDao = infobookDao;
    }

    @Override
    public List<Book> getAll() {
        // TODO: add logging
        return booksDao.getAll();
    }

    @Override
    public Book getBookById(long id) {
        return booksDao.getById(id);
    }

    @Override
    public Book createBook(String Title, String Author) {
        return booksDao.create(Title, Author);
    }

    @Override
    public void updateBook (Book book) {
        booksDao.update(book);
    }

    @Override
    public boolean removeBook(Book book) {
        for (InfoBook inform : infobookDao.getInfoAboutBooks(book.getId())) {
            infobookDao.removeInform(inform.getId(), book.getId());
        }
        return booksDao.delete(book.getId());
    }

    @Override
    public List<InfoBook> getInfoAboutBooks(Book book) {
        return infobookDao.getInfoAboutBooks(book.getId());
    }

    @Override
    public InfoBook creatInformBook(Book book, int year, Genres genre, String information, String quote) {
        return infobookDao.createInform(book.getId(), year, genre, information, quote);
    }

    @Override
    public void updateInformBook(Book book, InfoBook infobook) {
        infobookDao.updateInform(infobook, book.getId());
    }

    @Override
    public boolean removeInform(Book book, InfoBook inform) {
        return infobookDao.removeInform(inform.getId(), book.getId());
    }
}
