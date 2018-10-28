package com.caps.dev.hibernate;

import java.util.Scanner;

import com.caps.dev.DAO.DAOInterface;


public class MainClassHibernate {

	static Scanner scn=new Scanner(System.in);
	public static void main(String[] args) {
		try
		{

		DAOInterface ref=new HibernateImpl();
		
		String input="";
		System.out.println("Welcome to MyApp");
		boolean b=true;


		while(b)
		{
			System.out.println
			("Select the number"+"\n\n"+" "+"1 CreatePerson\n 2 DeletePerson\n 3 Search\n 4 Update");
			input=scn.nextLine();
			switch(input)
			{
			case "1":
				ref.createPersonDetails();break;

			case "2":
				ref.delete();break;
			case "3":
				ref.search();break;
			case "4":
				ref.delete();break;
				
			default:System.out.println("please enter the correct option");
			b=true;
			}
		}
		}
		finally
		{
			scn.close();
		}
	}
	
	
}







