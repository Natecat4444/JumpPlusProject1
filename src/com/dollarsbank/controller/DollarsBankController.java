package com.dollarsbank.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;
import com.dollarsbank.utility.database;

public class DollarsBankController {
	private Account account;
	private Customer customer;
	
	public DollarsBankController() {
		
	}
	
	public boolean login(String username, String password) {
		String query = "Select * from Customer WHERE username = ? AND password = ?;";
		try {
			PreparedStatement statement = database.getConn().prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			rs.next();

			System.out.println(rs.getString(0));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createUser(String name, String username, String password, String number, String address) {
		String query = "INSERT INTO Customer(name, username, password, number, address) values(?,?,?,?,?);";
		try {
			PreparedStatement statement = database.getConn().prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, number);
			statement.setString(5, address);
			
			int result = statement.executeUpdate();
			
			if(result >0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Transaction> getFiveTransactions(){
		return null;
	}
	
	public String getCustomerName() {
		return customer.getName();
	}

}
