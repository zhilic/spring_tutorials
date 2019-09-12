package zhili.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Course;
import zhili.hibernatedemo.entity.Instructor;
import zhili.hibernatedemo.entity.InstructorDetail;
import zhili.hibernatedemo.entity.Review;

public class OneToManyDemo {

	public static void main(String[] args) {
		
		// create SessionFactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")  // file name not required
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create Session
		Session session = factory.getCurrentSession();
		
		try {
			// create the objects
			Instructor tempInstructor = 
					new Instructor("Susan", "Green", "susan@gmail.com");
					
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com",
							"Video Games");
			
			Course tempCourse1 = new Course("Video Game Mastercourse");
			Course tempCourse2 = new Course("Intro To Java");
			
			Review tempReview1 = new Review("Great course ... loved it!");
			Review tempReview2 = new Review("Cool course, job well done!");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			tempInstructor.addCourse(tempCourse1);
			tempInstructor.addCourse(tempCourse2);
			
			tempCourse1.addReview(tempReview1);
			tempCourse1.addReview(tempReview2);
			
			// save the courses
			session.beginTransaction();
			
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			// reviews related to tempCourse2 will also be saved
			System.out.println("\nSaving course: " + tempCourse1);
			session.save(tempCourse1);
			
			System.out.println("\nSaving course: " + tempCourse2);
			session.save(tempCourse2);
			
			// Try deleting a course without effecting the instructor
			int courseId = tempCourse2.getId();
			Course theCourse = session.get(Course.class, courseId);
			
			System.out.println("\nDeleting course: " + theCourse);
			session.delete(theCourse);

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
