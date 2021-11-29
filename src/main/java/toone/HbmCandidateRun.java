package toone;

import jdk.jshell.spi.ExecutionControl;
import model.Base;
import model.Candidate;
import model.Vacancy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
            Vacancy junior = Vacancy.of("Java junior", "Fix bug");
            Vacancy middle = Vacancy.of("Java middle", "Developing");
            Vacancy senior = Vacancy.of("Java senior", "creating an architecture");

            session.save(junior);
            session.save(middle);
            session.save(senior);

            Base programmer = Base.of("Programmer");
            programmer.addVacancy(junior);
            programmer.addVacancy(middle);
            programmer.addVacancy(senior);

            session.save(programmer);

            Candidate candidate = Candidate.of("Yarik", "Java core", 100);
            candidate.setBase(programmer);

            session.save(candidate);
             */

            Candidate candidate = session.createQuery(
                    "select distinct c from Candidate c "
                            + " join fetch c.base b "
                            + " join fetch b.vacancies "
                            + " where c.id = :id", Candidate.class
            ).setParameter("id", 4).uniqueResult();

            for (Vacancy el : candidate.getBase().getVacancies()) {
                System.out.println(el);
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
