package com.example.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		// In Hibernate, data manipulation is done by "Session" objects which are generally short lived and one time.
		// In order create sessions, we need a "SessionFactory" object. This is a heavy weight object and will typically be
		// created only once. After that we can get sessions from this factory.
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.buildSessionFactory();
	
		Session mySession = sessionFactory.getCurrentSession();
		
		try {
			// Create the objects
//			Instructor instructor1 = new Instructor("Akkas", "Ali", "akkasali@gmail.com");
			
//			InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/abc", "Gardening");
			
			Instructor instructor1 = new Instructor("Abdul", "Kuddus", "abdulkuddus@gmail.com");
			
			InstructorDetail instructorDetail1 = new InstructorDetail("youtube.com/def", "Hiking");
			
			// Associate the objects
			instructor1.setInstructorDetail(instructorDetail1);
			
			// Start the transaction
			mySession.beginTransaction();
			
			// Saving the instructor. This will also save the associated objects
			System.out.println("Saving instructor: " + instructor1);
			mySession.save(instructor1);
			
			// Commit transaction
			mySession.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			sessionFactory.close();
		}
	}

}
