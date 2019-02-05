package pl.lukas.hqldemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hqldemo.entity.Employee;

import java.util.List;

public class NamedParametersApp {

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

        String employeeFirstName = "Steven";
        String employeeLastName = "King";


        session.beginTransaction();

        String queryString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName='" +
                employeeFirstName + "' and e.lastName='" + employeeLastName + "'";

        Query query = session.createQuery(queryString);

        String namedParametersString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName=:firstName and e.lastName=:lastName";

        Query namedParametersQuery = session.createQuery(namedParametersString);
        namedParametersQuery.setParameter("firstName", employeeFirstName);
        namedParametersQuery.setParameter("lastName", employeeLastName);


        List<Object[]> result = namedParametersQuery.getResultList();
        for (Object[] values : result) {
            System.out.println("firstName: " + values[0] + ", lastName: " + values[1] + ", salary: " + values[2]);
        }

        session.getTransaction().commit();

        // zakończeine obiektu SessionFactory
        factory.close();


    }
}
