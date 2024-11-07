package com.jdbc1files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

public class TransscationManagement {

	public static void main(String[] args) {
		String url="jdbc:Mysql://127.0.0.1:3306/bank";
		String un="root";
		String pass="sravani";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loades succesfully");
			
			try {
				Connection con=DriverManager.getConnection(url,un,pass);
				System.out.println("connection established");
				Scanner sc=new Scanner(System.in);
				
				
				//login module
				System.out.println("welcome to bank----->");
				System.out.println("Enter account number: ");
				int acc_num=sc.nextInt();
				System.out.println("Enter pin: ");
				int pin=sc.nextInt();
				PreparedStatement ps1=con.prepareStatement("select*from account where acc_num=? and pin=?");
				ps1.setInt(1,acc_num);
				ps1.setInt(2,pin);
				ResultSet res1=ps1.executeQuery();
				res1.next();
				String name=res1.getString(2);
				int bal=res1.getInt(4);
				System.out.println("welcome "+name);
				System.out.println("Available balance is: "+bal);
				
				//Transfer module
				System.out.println("<----Transfer Details---->");
				System.out.println("Enter the beneficiary account number");
				int bcc_num=sc.nextInt();
				System.out.println("Enter the transfer amount");
	            int t_amount = sc.nextInt();
	            
	            con.setAutoCommit(false);
	            Savepoint s=con.setSavepoint();
	            
				PreparedStatement ps2=con.prepareStatement("update account set balance=balance - ? " + " where acc_num = ?");
				ps2.setInt(1, t_amount);
				ps2.setInt(2,acc_num);
				ps2.executeUpdate();
				System.out.println("<----Incoming credit request--->");
				System.out.println(name+"wants to transfer "+acc_num+ "wants to transfer "+t_amount);
				System.out.println("press Y to receive");
				System.out.println("press N to reject");
				String choice=sc.next();
				if(choice.equals("Y")) {
					PreparedStatement ps3=con.prepareStatement("update account set balance = balance + ? " + " where acc_num = ?");
					ps3.setInt(1, t_amount);
					ps3.setInt(2, bcc_num);
					ps3.executeUpdate();
					PreparedStatement ps4=con.prepareStatement("select*from account " + " where acc_num = ?");
					ps4.setInt(1,bcc_num);
					ResultSet res2=ps4.executeQuery();
					res2.next();
					System.out.println("update balance is: "+res2.getInt(4));
				}else {
					con.rollback(s);
					PreparedStatement ps5=con.prepareStatement("select*from account " +" where acc_num = ?");
					ps5.setInt(1,bcc_num);
					ResultSet res2=ps5.executeQuery();
					res2.next();
					System.out.println("existing balance is: "+res2.getInt(4));
				}
			
				
				con.commit();
				
				
				
				
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
