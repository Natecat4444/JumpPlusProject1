package com.dollarsbank.model;

public class Customer {
	private String username;
	private String password;
	private int id;
	private String name;
	private String number;
	private String address;
	
	public Customer(String username, String password, String name, String number, String address) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.number = number;
		this.address = address;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
