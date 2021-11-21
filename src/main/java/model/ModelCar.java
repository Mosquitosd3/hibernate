package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    private MarkCar marks;

    public static ModelCar of(String name, MarkCar marks) {
        ModelCar modelCar = new ModelCar();
        modelCar.name = name;
        modelCar.marks = marks;
        return modelCar;
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

    public MarkCar getMarks() {
        return marks;
    }

    public void setMarks(MarkCar marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelCar modelCar = (ModelCar) o;
        return id == modelCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ModelCar{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", marks="
                + marks
                + '}';
    }
}
