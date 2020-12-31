import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HibernateExp {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession()) {
            List<LinkedPurchase> linkedPurchases = new ArrayList<>();
            Transaction transaction = session.beginTransaction();
            List<Purchase> purchases = session.createQuery("From Purchase").getResultList();
            for (Purchase purchase : purchases) {
                String hql = "from Student where name = '" + purchase.getStudentName() + "'";
                Student student = (Student) session.createQuery(hql).getSingleResult();
                hql = "from Course where name = '" + purchase.getCourseName() + "'";
                Course course = (Course) session.createQuery(hql).getSingleResult();
                LinkedPurchase lp = new LinkedPurchase(new SubscriptionId(student.getId(), course.getId()));
                linkedPurchases.add(lp);
                session.persist(lp);
            }
            transaction.commit();
        }
    }
}