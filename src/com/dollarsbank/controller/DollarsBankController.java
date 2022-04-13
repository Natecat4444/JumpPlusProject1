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
			
			customer = new Customer(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getString(4), rs.getString(5), rs.getString(6));
			
			query = "SELECT * FROM Account WHERE user_id=?;";
			
			
			statement = database.getConn().prepareStatement(query);
			statement.setInt(1, customer.getId());
			
			ResultSet rs1 = statement.executeQuery();
			if(!rs1.next()) {
				createAccount();
				rs1 = statement.executeQuery();
				rs1.next();

			}
			
			account = new Account(rs1.getInt(2), rs1.getDouble(1), rs1.getInt(3));
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void createAccount() {
		String query1 ="INSERT INTO Account(user_id, balance) values(?,0);";
		try {
			PreparedStatement stmt = database.getConn().prepareStatement(query1);
			stmt.setInt(1, customer.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean withdrawFunds(double amount) {
		String query ="UPDATE Account SET balance=? WHERE acc_id=?;";
		double balance = account.getBalance() - amount;
		if(balance <0 ) {
			return false;
		}
		try {
			PreparedStatement stmt = database.getConn().prepareStatement(query);
			stmt.setDouble(1, balance);
			stmt.setInt(2, account.getId());
			int success = stmt.executeUpdate();
			if(success <1) {
				return false;
			}
			account.setBalance(balance);
			
			query = "INSERT INTO Transaction(user_id, acc_id, type, ammount) values(?,?,?,?);";
			stmt = database.getConn().prepareStatement(query);
			stmt.setInt(1, customer.getId());
			stmt.setInt(2, account.getId());
			stmt.setString(3, "WITHDRAWL");
			stmt.setDouble(4, amount);
			success = stmt.executeUpdate();
			if(success <1) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TODO
		return false;
	}
	
	public boolean despositFunds(double amount) {
		String query ="UPDATE Account SET balance=? WHERE acc_id=?;";
		double balance = account.getBalance() + amount;
		if(balance <0 ) {
			return false;
		}
		try {
			PreparedStatement stmt = database.getConn().prepareStatement(query);
			stmt.setDouble(1, balance);
			stmt.setInt(2, account.getId());
			int success = stmt.executeUpdate();
			if(success <1) {
				return false;
			}
			account.setBalance(balance);
			
			query = "INSERT INTO Transaction(user_id, acc_id, type, ammount) values(?,?,?,?);";
			stmt = database.getConn().prepareStatement(query);
			stmt.setInt(1, customer.getId());
			stmt.setInt(2, account.getId());
			stmt.setString(3, "DEPOSIT");
			stmt.setDouble(4, amount);
			success = stmt.executeUpdate();
			if(success <1) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean transferFunds(int account_id, double amount) {
		String query ="UPDATE Account SET balance=? WHERE acc_id=?;";
		String query1 = "INSERT INTO Transaction(user_id, acc_id, type, ammount) values(?,?,?,?);";
		String query3 = "SELECT balance FROM Account WHERE acc_id=?;";
		try {
			PreparedStatement takeFunds = database.getConn().prepareStatement(query);
			PreparedStatement giveFunds = database.getConn().prepareStatement(query);
			PreparedStatement takeFundsTrans = database.getConn().prepareStatement(query1);
			PreparedStatement giveFundsTrans = database.getConn().prepareStatement(query1);
			PreparedStatement getSecondAccount = database.getConn().prepareStatement(query3);
			
			getSecondAccount.setInt(1, account_id);
			
			ResultSet rs = getSecondAccount.executeQuery();
			
			if(!rs.next()) {
				return false;
			}
			
			double bal = rs.getDouble(1);
			
			takeFunds.setDouble(1, account.getBalance()-amount);
			takeFunds.setInt(2, account.getId());
			
			giveFunds.setDouble(1, bal+amount); //Need to update this value to reflect what was in there
			giveFunds.setInt(2, account_id);
			
			takeFundsTrans.setInt(1, customer.getId());
			takeFundsTrans.setInt(2, account.getId());
			takeFundsTrans.setString(3, "TRANSFER");
			takeFundsTrans.setDouble(4, amount*-1);
			
			giveFundsTrans.setInt(1, customer.getId());
			giveFundsTrans.setInt(2, account_id);
			giveFundsTrans.setString(3, "TRANSFER");
			giveFundsTrans.setDouble(4, bal);
			
			int success = takeFunds.executeUpdate();
			if(success == 0) {
				return false;
			}
			
			giveFunds.executeUpdate();
			giveFundsTrans.executeUpdate();
			takeFundsTrans.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getCustomerInfo() {
		return customer.toString()+" Account num:"+account.getId();
	}
	
	public ArrayList<Transaction> getFiveTransactions(){
		String query = "Select * from Transaction WHERE acc_id = ? SORT BY occured desc LIMIT 5;";
		try {
			PreparedStatement stmt = database.getConn().prepareStatement(query);
			stmt.setInt(1, account.getId());
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
//				Transaction t = new Transaction();
				System.out.println("Get these set");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCustomerName() {
		return customer.getName();
	}

}
