import org.hibernate.SQLQuery;
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
            SQLQuery query = session.createSQLQuery("select student_name, course_name from purchaselist");
            List<Object[]> purchases = query.list();
            int studentId = 0, courseId = 0;
            for (Object[] purchase : purchases) {
                query = session.createSQLQuery("select id, name from students where name = :name");
                String str = purchase[0].toString();
                List<Object[]> rows = query.setString("name", str).list();
                if (rows != null) {
                    studentId = Integer.parseInt(rows.get(0)[0].toString());
                }
                query = session.createSQLQuery("select id, name from courses where name = :name");
                str = purchase[1].toString();
                List<Object[]> students = query.setString("name", str).list();
                if (rows != null) {
                    courseId = Integer.parseInt(students.get(0)[0].toString());
                }
                LinkedPurchase lp = new LinkedPurchase(new SubscriptionId(studentId, courseId));
                session.persist(lp);
            }
            transaction.commit();
        }
    }
}