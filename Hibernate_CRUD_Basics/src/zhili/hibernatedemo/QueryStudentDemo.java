package zhili.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create SessionFactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")  // file name not required
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create Session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// conditional queries
			theStudents = session.createQuery("from Student s where "
					+ "s.lastName='Wall' OR s.firstName='Joe'")
					.getResultList();
			System.out.println("\nStudents whose last name is 'Wall'");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'")
					.getResultList();
			System.out.println("\nStudents whose email is like '%gmail.com'");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
