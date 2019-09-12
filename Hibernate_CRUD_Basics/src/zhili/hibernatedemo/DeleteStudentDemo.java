package zhili.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Student;

public class DeleteStudentDemo {

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
			// Method 1 to delete a record
			int studentId = 3;
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Deleting student: " + myStudent);
			session.delete(myStudent);
			
			// Method 2 to delete a record
//			session.createQuery("delete from Student where id = " + studentId);

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
