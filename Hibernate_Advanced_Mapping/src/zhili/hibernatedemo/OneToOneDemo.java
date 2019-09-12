package zhili.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import zhili.hibernatedemo.entity.Instructor;
import zhili.hibernatedemo.entity.InstructorDetail;

public class OneToOneDemo {

	public static void main(String[] args) {
		
		// create SessionFactory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")  // file name not required
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create the objects
			Instructor tempInstructor1 = 
					new Instructor("Chad", "Darby", "darby@luv2code.com");
					
			InstructorDetail tempInstructorDetail1 = 
					new InstructorDetail(
							"http://www.luv2code.com/youtube",
							"Luv 2 code!!");
			
			Instructor tempInstructor2 = 
					new Instructor("Madhu", "Patel", "madhu@luv2code.com");
					
			InstructorDetail tempInstructorDetail2 = 
					new InstructorDetail(
							"http://www.youtube.com",
							"Guitar");
			
			// associate the objects
			tempInstructor1.setInstructorDetail(tempInstructorDetail1);
			tempInstructorDetail2.setInstructor(tempInstructor2);

			session.beginTransaction();
			
			// save the instructor
			// NOTE: it will ALSO save the details object because of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor1);
			session.save(tempInstructor1);
			
			System.out.println("Saving instructor detail: " + tempInstructorDetail2);
			session.save(tempInstructorDetail2);
			
			// Try deleting InstructorDetail without deleting corresponding Instructor
			// First, need to remove the associated object reference
			// by breaking the bi-directional link
			int theId = tempInstructorDetail2.getId();
			System.out.println("Deleting an InstructorDetail object" 
					+ "without deleting the Instructor object...");
			InstructorDetail theDetail = session.get(InstructorDetail.class, theId);
			System.out.println("Deleting InstructorDetail: " + theDetail);
			theDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(theDetail);
			
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
