package zhili.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Student;

public class CreateStudentDemo {

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
			Student tempStudent = new Student("Paul", "Wall", "paul@gmail.com");
			System.out.println("Saving the student...");
			session.beginTransaction();
			session.save(tempStudent);
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
