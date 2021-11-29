package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "base")
public class Base {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public static Base of(String name) {
        Base base = new Base();
        base.name = name;
        return base;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
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

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Base: name = %s", name);
    }
}
