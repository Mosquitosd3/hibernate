package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static Books of(String name) {
        Books books = new Books();
        books.name = name;
        return books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Books books = (Books) o;
        return id == books.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
