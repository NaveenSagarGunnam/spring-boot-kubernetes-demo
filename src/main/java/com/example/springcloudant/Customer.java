package com.example.springcloudant;

public class Customer {

	private String _id;
	private String customerName;
	private String customerId;
	private String department;	
	private String location;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	  
	public Customer() {}

	public Customer(String customerName, String customerId, String department) {

		this.customerName = customerName;
		this.customerId = customerId;
		this.department = department;
	}
}
