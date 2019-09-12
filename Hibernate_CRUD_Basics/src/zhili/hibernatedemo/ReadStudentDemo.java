package zhili.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create SessionFactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")  // file name not required
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create Session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Leon", "Kang", "leon@gmail.com");
			System.out.println("Saving the student...");
			session.beginTransaction();
			session.save(tempStudent);
			session.getTransaction().commit();
			
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
