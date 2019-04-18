
package main.BookShop.model;

/*Создаем Класс Книга: ID, Название и Автор*/
public class Book {
    private final long id;

    private final String Title;
    private final String Author;

    public Book(long id, String Title, String Author) {
        this.id = id;
        this.Title = Title;
        this.Author = Author;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book contact = (Book) o;

        if (id != contact.id) return false;
        if (!Title.equals(contact.Title)) return false;
        return Author.equals(contact.Author);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + Title.hashCode();
        result = 31 * result + Author.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }
}