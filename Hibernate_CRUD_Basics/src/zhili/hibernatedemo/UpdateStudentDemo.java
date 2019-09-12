package zhili.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create SessionFactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")  // file name not required
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create Session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			System.out.println("Getting student with id: " + 1);
			Student myStudent = session.get(Student.class, 1);
			
			System.out.println("Updating student...");
			myStudent.setEmail("paul@hotmail.com");
			
			session.getTransaction().commit();
			
			// Update email for ALL students
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Update all students' email to be foo@gmail.com...");
			session.createQuery("update Student set email='foo@gmail.com'")
					.executeUpdate();
			
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
