package datamodel;

public class Customer {
private final String id; 
private String firstName="";
private String lastName="";
private String contact;

Customer(String id,String name,String contact) {
	this.id = id;
	this.contact = contact;
	lastName = name; 
	firstName ="";
}

public String getId() {
	return id;
}
public String getFirstName() {
	if (firstName == null) 
		firstName ="";
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	if (lastName == null) lastName = "";
	return lastName;
}
public void setLastName(String lastName) {

	this.lastName = lastName;
	
}

public String getContact() {
	if(contact == null) contact ="";
	return contact;
}
public void setContact(String contact) {
	this.contact=contact;
}

}
