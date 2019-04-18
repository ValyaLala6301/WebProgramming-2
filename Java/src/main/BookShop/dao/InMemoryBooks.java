package main.BookShop.dao;

import main.BookShop.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public enum InMemoryBooks implements BooksDao {
    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Book> allBooks = new HashMap<>();

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(allBooks.values());
    }

    @Override
    public Book getById(long id) {
        return allBooks.get(id);
    }

    @Override
    public Book create(String Title, String Author) {
        Book book = new Book(idGenerator.incrementAndGet(), Title, Author);
        allBooks.put(book.getId(), book);
        return book;
    }

    @Override
    public void update(Book book) {
        allBooks.put(book.getId(), book);
    }

    @Override
    public boolean delete(long id) {
        Book remove = allBooks.remove(id);
        return remove != null;
    }
}
