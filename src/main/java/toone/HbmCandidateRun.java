package toone;

import model.Candidate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmCandidateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            Candidate one = Candidate.of("Alex", "Spring", 100);
            Candidate two = Candidate.of("Nikolay", "Servlet", 120);
            Candidate three = Candidate.of("Nikitaa", "Hibernate",110);

            session.save(one);
            session.save(two);
            session.save(three);
             */
            for (Candidate el : (List<Candidate>) session.createQuery("from Candidate").list()) {
                System.out.println(el);
            }
            System.out.println(session.createQuery("from Candidate c where c.id = 1")
                    .uniqueResult());

            Query queryName = session.createQuery("from Candidate c where c.name = :name")
                    .setParameter("name", "Nikolay");
            System.out.println(queryName.uniqueResult());

            Query update = session.createQuery(
                    "update Candidate s set s.name = :name,"
                            + " s.experience = :experience, s.salary = :salary "
                            + "where s.id = :id"
            );
            update.setParameter("name", "Misha");
            update.setParameter("experience", "Java core");
            update.setParameter("salary", 150);
            update.setParameter("id", 3);
            update.executeUpdate();

            Query delete = session.createQuery("delete from Candidate where id = :id")
                    .setParameter("id", 1);
            delete.executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
