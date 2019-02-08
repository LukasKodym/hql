package pl.lukas.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hql.entity.Employee;

public class AggregateFunctionsApp {

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

        String avg = "select avg(e.salary) from Employee e";
        String sum = "select sum(e.salary) from Employee e";
        String min = "select min(e.salary) from Employee e";
        String max = "select max(e.salary) from Employee e";
        String count = "select count(e.) from Employee e";


        Query query = session.createQuery(count);

        Long result = (Long) query.getSingleResult();

        session.getTransaction().commit();

        System.out.println("Wynik: " + result);

        // zakończeine obiektu SessionFactory
        factory.close();
    }
}
