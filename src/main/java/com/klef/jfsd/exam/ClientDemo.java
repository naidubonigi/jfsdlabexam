package com.klef.jfsd.exam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {
    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                     .addAnnotatedClass(Department.class)
                                                     .buildSessionFactory();

        // Get the current session by explicitly opening a session
        Session session = factory.openSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Update the department with id 1 (replace 1 with the actual deptId)
            String hql = "update Department set name = ?1, location = ?2 where id = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Updated Department Name");
            query.setParameter(2, "New Location");
            query.setParameter(3, 1); // Assuming deptId = 1 exists in the database

            // Execute the update
            int result = query.executeUpdate();
            System.out.println("Number of rows affected: " + result);

            // Commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the session
            session.close();
            factory.close();
        }
    }
}
