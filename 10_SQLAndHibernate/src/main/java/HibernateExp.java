import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class HibernateExp {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, 2);
            System.out.println("Курс = " + course.getName());
            System.out.println("Преподаватель = " + course.getTeacher().getName() + "\nСтуденты :");
            course.getStudents().stream().map(Student::getName).forEach(System.out::println);
            transaction.commit();
        }
    }
}