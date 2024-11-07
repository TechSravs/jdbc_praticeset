package com.jdbc1files;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Batchprocessing {

	public static void main(String[] args) {
		String url="jdbc:Mysql://127.0.0.1:3306/employee";
		String un="root";
		String pass="sravani";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loades succesfully");
			try {
				Connection con=DriverManager.getConnection(url,un,pass);
				System.out.println("connection established");
				//String query="update emp set salary =salary+salary*0.20";//CRUD-update
				Statement s=con.createStatement();
				//System.out.println("number of rows updated: "+ s.executeUpdate(query));
				String query="delete from emp where name='rob'";//CRUD-delete
				System.out.println("number of rows deleted: "+s.executeUpdate(query));
				//only one value inserted
				//String query="insert into emp(`id`,`name`,`dsig`,`salary`)values(4,'rob','SME',25000)";
				//s.execute(query);
				//System.out.println("query executed succesfully");
				// use addBatch
				//String query1="insert into emp(`id`,`name`,`dsig`,`salary`)values('5','sam','ACCOUNT',20000)";
				//String query2="insert into emp(`id`,`name`,`dsig`,`salary`)values('6','shaik','SMS',30000)";
				//String query3="insert into emp(`id`,`name`,`dsig`,`salary`)values('7','sravs','MONEY',20000)";
				//s.addBatch(query1);
				//s.addBatch(query2);
				//s.addBatch(query3);
				//s.executeBatch();
				//System.out.println("query executed succesufully");
				//use PreparedStatement
				//String query="insert into emp(`id`,`name`,`dsig`,`salary`)values(?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				Scanner sc=new Scanner(System.in);
				//System.out.println("Enter id");
				//int id=sc.nextInt();
				//System.out.println("Enter name");
				//String name=sc.next();
				//System.out.println("Enter dsig");
				//String dsig=sc.next();
				//System.out.println("Enter salary");
				//int salary=sc.nextInt();
				//ps.setInt(1,id);
				//ps.setString(2,name);
				//ps.setString(3,dsig);
				//ps.setInt(4, salary);
				//ps.execute();
				//System.out.println("query executed succesufully");
				//how to inserted rows in java program
				System.out.println("Enter the number of rows to be inserted: ");
				int n=sc.nextInt();
				con.setAutoCommit(false);//ACID properties
				for(int i=1;i<=n;i++) {
					int id=sc.nextInt();
					String name=sc.next();
					String dsig=sc.next();
					int salary=sc.nextInt();
					ps.setInt(1,id);
					ps.setString(2,name);
					ps.setString(3,dsig);
					ps.setInt(4, salary);
					ps.execute();
				}
				con.commit();//ACID properties
				s.close();
				sc.close();
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
