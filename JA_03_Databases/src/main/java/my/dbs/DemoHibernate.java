package my.dbs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DemoHibernate {
    public static void main(String[] args) {
        SessionFactory factory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();
        Session session = factory.openSession();

        String filter = "A";

        String hql = "SELECT p FROM Product p WHERE p.name LIKE :p1 ";
        List<Product> products = session.createQuery(hql)
                                    .setParameter("p1", filter + "%")
                                    .list();
        products.forEach( p -> {
            System.out.println(p.getName() + " " + p.getPrice());
        });

        Product p = products.get(0);
        p.setPrice(123.0);
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit(); // ошибка прав, но SQL хороший

    }
}
