package pl.lukas.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.lukas.hql.entity.Employee;

public class DeleteApp {
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

        session.beginTransaction();

        String delete = "delete Employee e where e.idEmployee=:idEmployee";
        Query query = session.createQuery(delete);
        query.setParameter("idEmployee", idEmployee);
        int i = query.executeUpdate();

        System.out.println("ilość usuniętych rekordó: " + i);


        session.getTransaction().commit();
        // zakończeine obiektu SessionFactory
        factory.close();
    }

}
