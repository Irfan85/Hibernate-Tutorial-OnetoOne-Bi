package com.example.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import com.example.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
			// Start the transaction
			mySession.beginTransaction();
			
			int id = 3;
			
			InstructorDetail instructorDetail1 = mySession.get(InstructorDetail.class, id);
			
			System.out.println("Instructor Detail: " + instructorDetail1);
			
			Instructor instructor1 = instructorDetail1.getInstructor();
			
			System.out.println("Associated Instructor: " + instructor1);
			
			System.out.println("Deleting: " + instructorDetail1);
			
			// As the cascase is not set for remove operation in the InstructorDetail class, we have to break the association
			// with the Instructor object before deleting an InstructorDetail object by setting to foregin key in the object
			// of the Instructor object to null
			instructorDetail1.getInstructor().setInstructorDetail(null);
			mySession.delete(instructorDetail1);
			
			// Commit transaction
			mySession.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// This handles the connection leak issue
			mySession.close();
			
			sessionFactory.close();
		}
	}

}
