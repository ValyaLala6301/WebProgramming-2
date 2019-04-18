package main.BookShop.dao;

import main.BookShop.model.InfoBook;
import main.BookShop.model.Genres;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public enum InMemoryInfo implements InfoBookDao{

    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Map<Long, InfoBook>> allInform = new HashMap<>();

    @Override
    public List<InfoBook> getInfoAboutBooks(long bookId) {
        Collection<InfoBook> infbook = allInform.getOrDefault(bookId, Collections.emptyMap()).values();
        return new ArrayList<>(infbook);
    }

    @Override
    public InfoBook createInform(long bookId, int year, Genres genre, String information, String quote) {
        long InfId = idGenerator.incrementAndGet();
        InfoBook newInfBook = new InfoBook(InfId, year, genre, information, quote);
        allInform.computeIfAbsent(bookId, id -> new LinkedHashMap<>()).put(InfId, newInfBook);
        return newInfBook;
    }

    @Override
    public boolean removePhone(long InfId, long bookId) {
        InfoBook removed = allInform.getOrDefault(bookId, Collections.emptyMap()).remove(InfId);
        return removed != null;
    }

    @Override
    public void updatePhone(InfoBook infobook, long bookId) {
        allInform.get(bookId).put(infobook.getId(), infobook);
    }
}

