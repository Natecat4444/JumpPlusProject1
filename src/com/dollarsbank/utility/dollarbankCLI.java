package com.dollarsbank.utility;

import java.util.Scanner;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.utility.database;

public class dollarbankCLI {
	private DollarsBankController controller;
	private Scanner scanner;
	
	public dollarbankCLI() {
		controller = new DollarsBankController();
		scanner = new Scanner(System.in);
	}

	public void landing() {
		while(true) {
			System.out.println("Welcome to Dollar Bank");
			System.out.println("1. Create new account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Enter Selection: ");
			int input = scanner.nextInt();
			
			switch (input) {
			case 1: createUser();
				break;
			case 2: login();
				break;
			case 3: System.out.println("Goodbye");
				database.closeConn();
				System.exit(1);
				break;
			default: System.out.println("Invalid Selection");
				break;
			}
		}
	}
	
	public void login() {
		System.out.println("Login");
		System.out.print("Enter Username: ");
		String username = scanner.next();
		System.out.print("Enter Password: ");
		String password = scanner.next();
		boolean success = controller.login(username, password);
		if(success) {
			Customer();
		} else {
			System.out.println("Invalid Username/Password");
		}
		
	}
	
	public void createUser() {
		System.out.println("Create Account");
		System.out.println("Enter Username: ");
		String username = scanner.next();
		System.out.println("Enter Full Name (no spaces): ");
		String name = scanner.next();
		System.out.println("Enter Phone Number: ");
		String number = scanner.next();
		System.out.println("Enter Address (no spaces): ");
		String address = scanner.next();
		System.out.println("Enter Password: ");
		String password = scanner.next();
		
		boolean success = controller.createUser(name, username, password, number, address);
		
		if(success) {
			System.out.println("User Created");
			login();
		} else {
			System.out.println("Error creating user");
		}
	}
	
	public void Customer() {
		boolean signedIn = true;
		while(signedIn) {
			System.out.println("Welcome "+controller.getCustomerName());
			System.out.println("1. Deposit funds");
			System.out.println("2. Withdraw funds");
			System.out.println("3. Transfer funds");
			System.out.println("4. View 5 recent transactions");
			System.out.println("5. Display customer information");
			System.out.println("6. Sign out");
			System.out.print("Enter Selection: ");
			int selection = scanner.nextInt();
			
			switch (selection) {
			case 1: DepositFunds();
				break;
			case 2: WithDrawFunds();
				break;
			case 3: TransferFunds();
				break;
			case 4: ViewTransactions();
				break;
			case 5: DisplayInfo();
				break;
			case 6: signedIn=false;
				System.out.println("Goodbye "+controller.getCustomerName());
				break;
			default:
				break;
			}
		}
	}
	
	public void DepositFunds() {
		//TODO
	}
	
	public void WithDrawFunds() {
		//TODO
	}
	
	public void TransferFunds() {
		//TODO
	}
	
	public void ViewTransactions() {
		//TODO
	}
	
	public void DisplayInfo() {
		//TODO
	}
}
