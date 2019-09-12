package zhili.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Course;
import zhili.hibernatedemo.entity.Instructor;
import zhili.hibernatedemo.entity.InstructorDetail;
import zhili.hibernatedemo.entity.Review;
import zhili.hibernatedemo.entity.Student;

public class DeleteCourses {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			List<Course> courses = session.createQuery("from Course").getResultList();
			
			System.out.println("Loaded courses: " + courses);
			
			for (Course course : courses) {
				session.delete(course);
			}
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
