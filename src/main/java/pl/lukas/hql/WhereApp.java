package pl.lukas.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hql.entity.*;
import java.util.List;

public class WhereApp { // zawęża wyniki wyszukiwania

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

        String where = "from Employee where firstName='Tadeusz'"; // nazwy kolumn podajemy z encji(prywatne pola) a nie z bazy danych
        String where2 = "from Employee where salary > 12000";
        String where3 = "from Employee where salary < 3000 or salary > 13000";
        String where4 = "from Employee where salary is null";
        String where5 = "from Employee where lastName in ('Hutton','Errazuriz','Wiśniewski')";

        Query query = session.createQuery(where5);
        List<Employee> list = query.getResultList();
        for (Employee employee : list) {
            System.out.println(employee);
        }
        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();

    }
}
