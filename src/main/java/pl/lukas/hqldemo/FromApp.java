package pl.lukas.hqldemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hqldemo.entity.*;
import java.util.List;

public class FromApp { // wczytuje bazę danych

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

        String from = "FROM Employee";
        String from2 = "from Employee"; // wielkość liter dla słów kluczowych nie ma znaczneia
        String from3 = "from pl.lukas.hqldemo.entity.Employee"; // w przypadku gdy encje nazywają sie tak samo podajemy pełną ścieżkę do pakietu

        Query query = session.createQuery(from3); // można podstawić from2 i from3 i otrzymamy to samo

        List<Employee> list = query.getResultList();
        for (Employee employee : list) {
            System.out.println(employee);
        }

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();
    }
}
