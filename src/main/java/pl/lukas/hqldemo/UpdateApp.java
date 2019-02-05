package pl.lukas.hqldemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hqldemo.entity.Employee;

import java.util.List;

public class UpdateApp {

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

        int idEmployee = 116;
        int salary = 15000;

        session.beginTransaction();

        String update = "update Employee e set e.salary=:salary where e.idEmployee=:idEmployee";
        Query query = session.createQuery(update);

        query.setParameter("salary", salary);
        query.setParameter("idEmployee", idEmployee);
        query.executeUpdate();

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();
    }
}
