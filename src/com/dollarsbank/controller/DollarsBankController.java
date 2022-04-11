package com.dollarsbank.controller;

import java.util.ArrayList;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;

public class DollarsBankController {
	private Account account;
	private Customer customer;
	
	public DollarsBankController() {
		
	}
	
	public boolean login(String username, String Password) {
		return false;
	}
	
	public boolean createUser() {
		return false;
	}
	
	public ArrayList<Transaction> getFiveTransactions(){
		return null;
	}
	
	public String getCustomerName() {
		return customer.getName();
	}

}
