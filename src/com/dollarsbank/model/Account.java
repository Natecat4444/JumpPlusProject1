package com.dollarsbank.model;

public class Account {
	private int id;
	private double balance;
	private int user_id;
	
	
	
	
	public Account(int user_id) {
		this.user_id = user_id;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	

}
