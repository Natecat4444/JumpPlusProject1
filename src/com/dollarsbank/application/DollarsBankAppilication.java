package com.dollarsbank.application;

import com.dollarsbank.utility.database;
import com.dollarsbank.utility.dollarbankCLI;

public class DollarsBankAppilication {
	public static void main(String[] args) {
		database.makeConnection();
		dollarbankCLI  cli = new dollarbankCLI();
		cli.landing();
	}
}
