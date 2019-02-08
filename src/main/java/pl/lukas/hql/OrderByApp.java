package pl.lukas.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hql.entity.Employee;

import java.util.List;

public class OrderByApp {

    public static void main(String[] args) {

        // tworzenie obiektu Configuration
        Configuration conf = new Configuration();
        // wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");
        // wczytanie adnotacje klasy
        conf.addAnnotatedClass(Employee.class);
        // tworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();
        // pobieranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        String orderBy = "select e.firstName, e.lastName from Employee e order by e.firstName";
        String orderBy2 = "select e.firstName, e.lastName from Employee e order by e.lastName desc";
        String orderBy3 = "select e.firstName, e.lastName, e.salary from Employee e order by e.salary asc";




        Query query = session.createQuery(orderBy3);
        List<Object[]> result = query.getResultList();
        for (Object[] values : result) {
            System.out.println("firstName: " + values[0] + " lastName: " + values[1] + "salary: " + values[2]);
        }

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();

    }
}
