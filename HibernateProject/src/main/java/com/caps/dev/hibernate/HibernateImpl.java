package com.caps.dev.hibernate;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.caps.dev.DAO.DAOInterface;
import com.caps.dev.beans.Person;
import com.caps.dev.utils.HibernateUtils;

public class HibernateImpl implements DAOInterface {
	static Person person=new Person();
	static Scanner in = new Scanner(System.in);
	@Override
	public void createPersonDetails() {
		
		System.out.println("Enter the Person Details");
		System.out.println("-------------------");
		
	

		System.out.println("Enter person id: ");
		person.setPersonId(Integer.parseInt(in.nextLine()));

		System.out.println("Enter person name: ");
		person.setPersonName(in.nextLine());

		System.out.println("Enter person Age: ");
		person.setPersonAge(in.nextLine());

		System.out.println("Enter the persons email_id ");
		person.setEmailId(in.nextLine());
		
		System.out.println("Enter the persons address ");
		person.setAddress(in.nextLine());
				
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(person);
		tx.commit();
		System.out.println("Success");
	}
	
	
	@Override
	public void update() {
		
		
	}
	@Override
	public void delete() {
		System.out.println("Enter person id: ");
		person.setPersonId(Integer.parseInt(in.nextLine()));
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;


		try {
			emf = HibernateUtils.getEMF();
			em = emf.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			em.find(Person.class, 2);
			em.remove(person);
			tx.commit();
			System.out.println("Person Deleted");
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
		
	}
	@Override
	public void search() {
		System.out.println("Enter person id: ");
		person.setPersonId(Integer.parseInt(in.nextLine()));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mySQL2");
		EntityManager em = emf.createEntityManager();
		
//		em.getTransaction().begin();
		
		 person = em.find(Person.class,1);
		System.out.println(person);
		
//		em.getTransaction().commit();
		
		em.close();
		
	}

}
