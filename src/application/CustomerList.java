package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerList {
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private static CustomerList instance;
	
	 
	public static CustomerList getInstance() {
		if (instance == null) {
			instance = new CustomerList();
		}
		return instance;
	}

	
	public ArrayList<Customer> getCustomerList() {
		return customers;
	}
	
	public void loadData() {
		try {
			Scanner scanner = new Scanner(new File("cinemaUsers.txt"));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] tokens = line.split(",");
				Customer customer = new Customer();
				customer.setFirstName(tokens[0]);
				customer.setSurname(tokens[1]);
				customer.setEmail(tokens[2]);
				customer.setUsername(tokens[3]);
				customer.setPassword(tokens[4]);
				customer.setDob(tokens[5]);
				this.customers.add(customer);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
