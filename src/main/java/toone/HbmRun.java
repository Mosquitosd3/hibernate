package toone;

import model.CarBrand;
import model.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel golf = CarModel.of("Golf");
            CarModel polo = CarModel.of("Polo");
            CarModel pointer = CarModel.of("Pointer");
            CarModel tiguan = CarModel.of("Tiguan");
            CarModel passat = CarModel.of("Passat");

            session.save(golf);
            session.save(polo);
            session.save(pointer);
            session.save(tiguan);
            session.save(passat);

            CarBrand volkswagen = CarBrand.of("Volkswagen");
            volkswagen.addModel(session.load(CarModel.class, 1));
            volkswagen.addModel(session.load(CarModel.class, 2));
            volkswagen.addModel(session.load(CarModel.class, 3));
            volkswagen.addModel(session.load(CarModel.class, 4));
            volkswagen.addModel(session.load(CarModel.class, 5));

            session.save(volkswagen);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
