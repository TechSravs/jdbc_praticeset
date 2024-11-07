package com.jdbc1files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Firstprogramjdbc {

	public static void main(String[] args) {
	String url="jdbc:Mysql://127.0.0.1:3306/employee";
	String un="root";
	String pass="sravani";
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=DriverManager.getConnection(url,un,pass);
			System.out.println("connection succusfuly");
			Statement s=con.createStatement();
			String query="select*from emp";
			ResultSet res=s.executeQuery(query);
			while(res.next())
			{
				System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4));
			}
			res.close();
			s.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    }catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
	
	
	
		
	
		
			
			
	

	

		
	
	


