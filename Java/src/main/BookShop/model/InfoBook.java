package main.BookShop.model;

public class InfoBook {
    private final long id;

    private final int year;
    private final String quote;
    private final String information;
    private final Genres genre;

    public InfoBook(long id, int year, Genres genre, String information, String quote) {
        this.id = id;
        this.year = year;
        this.genre = genre;
        this.information = information;
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getInformation() {
        return information;
    }

    public String getQuote() {
        return quote;
    }

    public Genres getGenres() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoBook infbook = (InfoBook) o;

        if (id != infbook.id) return false;
        if (!information.equals(infbook.information)) return false;
        return genre == infbook.genre;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + year;
        result = 31 * result + information.hashCode();
        result = 31 * result + quote.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", information='" + information + '\'' +
                ", quote='" + quote + '\'' +
                ", genre=" + genre +
                '}';
    }
}
