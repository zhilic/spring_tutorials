package zhili.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Course;
import zhili.hibernatedemo.entity.Instructor;
import zhili.hibernatedemo.entity.InstructorDetail;
import zhili.hibernatedemo.entity.Review;
import zhili.hibernatedemo.entity.Student;

public class ManyToManyDemo {

	public static void main(String[] args) {
		
		// create SessionFactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")  // file name not required
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create Session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Course tempCourse = new Course("Spring For Beginners");
			System.out.println("Saving course: " + tempCourse);
			session.save(tempCourse);
			
			Student tempStudent1 = new Student("John", "Doe", "john@hotmail.com");
			Student tempStudent2 = new Student("Snow", "White", "snow@gmail.com");
			
			// associate the objects
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			System.out.println("\nSaving students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nSaved students: " + tempCourse.getStudents());

			// Student.getCourses() works only after committing saving the students
			// although the database works in flow without committing
			session.getTransaction().commit();
			
			// Try adding more courses to one student
			session = factory.getCurrentSession();
			session.beginTransaction();
			int studentId = tempStudent2.getId();
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());
			
			Course tempCourse1 = new Course("Linear Algebra");
			Course tempCourse2 = new Course("Probability");
			
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			System.out.println("\nSaving courses ...");
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			
//			System.out.println("\nCourses after adding new courses: " + tempStudent.getCourses());
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
			
		} finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}

	}

}
