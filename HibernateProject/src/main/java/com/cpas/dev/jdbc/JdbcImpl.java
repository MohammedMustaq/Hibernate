package com.cpas.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.caps.dev.DAO.DAOInterface;
import com.caps.dev.beans.Person;

public class JdbcImpl implements DAOInterface {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	Scanner in = new Scanner(System.in);
	static Person person=new Person();
	@Override
	public void createPersonDetails() {

		System.out.println("Enter the Person Details");
		System.out.println("-------------------");
		Scanner in = new Scanner(System.in);

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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl="jdbc:mysql://localhost:3307/capsv4_db";

			con = DriverManager.getConnection(dbUrl,"root","dinga"); //1st version of getConnection
			String sql = "insert into person_info values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, person.getPersonId());
			pstmt.setString(2,person.getPersonName());
			pstmt.setString(3,person.getPersonAge());
			pstmt.setString(4, person.getEmailId());
			pstmt.setString(5, person.getAddress());

			int count = pstmt.executeUpdate();

			if(count > 0) {
				System.out.println("Person Details added in your favourite list");
			}else {
				System.out.println("Failed!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void update() {
		try {
			System.out.println("Enter person id to update the email: ");
			person.setPersonId(Integer.parseInt(in.nextLine()));

			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl="jdbc:mysql://localhost:3307/capsv4_db";
			con = DriverManager.getConnection(dbUrl,"root","dinga"); 

			System.out.println(" update the Email: ");
			String email= in.nextLine();

			String sql = "update person_info set email_id=? where person_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(2,person.getPersonId());
			pstmt.setString(1,email);

			int count = pstmt.executeUpdate();

			if(count > 0) {
				System.out.println("Email_id updated");
			}else {
				System.out.println("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void delete() {
		System.out.println("Enter person id: ");
		person.setPersonId(Integer.parseInt(in.nextLine()));
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl="jdbc:mysql://localhost:3307/capsv4_db";
			con = DriverManager.getConnection(dbUrl,"root","dinga"); //1st version of getConnection
			String sql = "delete from person_info where person_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, person.getPersonId());
			int count = pstmt.executeUpdate();

			if(count > 0) {
				System.out.println("Person deleted");
			}else {
				System.out.println("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void search() {
		System.out.println("Enter person id: ");
		person.setPersonId(Integer.parseInt(in.nextLine()));

		try {

			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl="jdbc:mysql://localhost:3307/capsv4_db";
			con = DriverManager.getConnection(dbUrl,"root","dinga");

			String sql = "select * from person_info where "
					+ " person_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,person.getPersonId());

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				int regno=rs.getInt("person_id");
				String name=rs.getString("person_name");
				String age=rs.getString("person_age");
				String email=rs.getString("email_id");
				String address=rs.getString("address");

				System.out.println(regno);
				System.out.println(name);
				System.out.println(age);
				System.out.println(email);
				System.out.println(address);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


	}

}
