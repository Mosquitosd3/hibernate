package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class MarkCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "marks")
    private List<ModelCar> models = new ArrayList<>();

    public static MarkCar of(String name) {
        MarkCar markCar = new MarkCar();
        markCar.name = name;
        return markCar;
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

    public List<ModelCar> getModels() {
        return models;
    }

    public void setModels(List<ModelCar> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarkCar markCar = (MarkCar) o;
        return id == markCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MarkCar{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", models="
                + models
                + '}';
    }
}
