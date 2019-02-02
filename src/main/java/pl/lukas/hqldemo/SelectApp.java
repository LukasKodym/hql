package pl.lukas.hqldemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hqldemo.entity.*;

import java.util.List;

public class SelectApp { // zwraca fragment, część kolumn z encji
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

        String select = "select firstName, lastName from Employee"; // po nazwach pól z encji
        String select2 = "select e.firstName, e.lastName from Employee as e"; // z wykorzystaniem aliasu
        String select3 = "select e.firstName, e.lastName from Employee e"; // z wykorzystaniem aliasu bez słówka as to samo co wyżej



        Query query = session.createQuery(select3);
        List<Object[]> result = query.getResultList();
        for (Object[] values : result) {
            System.out.println("firstName: " + values[0] + " lastName: " + values[1]);
        }

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();

    }

}
