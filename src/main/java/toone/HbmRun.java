package toone;

import model.Author;
import model.Books;
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

            Books headFirstJava = Books.of("Head First Java");
            Books thinkingJava = Books.of("Thinking Java");
            Books coreJava = Books.of("Core Java");

            Author bruceEckel = Author.of("Bruce Eckel");
            bruceEckel.getBooks().add(headFirstJava);

            Author kathySierra = Author.of("Kathy Sierra");
            kathySierra.getBooks().add(thinkingJava);

            Author bretBates = Author.of("Bret Bates");
            bretBates.getBooks().add(thinkingJava);

            Author caysHorstmann = Author.of("Cay S. Horstmann");
            caysHorstmann.getBooks().add(coreJava);

            session.persist(bruceEckel);
            session.persist(kathySierra);
            session.persist(bretBates);
            session.persist(caysHorstmann);

            Author author = session.get(Author.class, 1);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
