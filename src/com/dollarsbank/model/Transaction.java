package com.dollarsbank.model;

public class Transaction {
	private int id;
	private int user_id;
	private int acc_id;
	private String type;
	private double amount;
	
	public Transaction(int user_id, int acc_id, String type, double amount) {
		super();
		this.user_id = user_id;
		this.acc_id = acc_id;
		this.type = type;
		this.amount = amount;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(int acc_id) {
		this.acc_id = acc_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
