package datamodel;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Order {
private final long id; 
private final Date date;
private final Customer customer;
private final List<OrderItem> items;

Order(long id, Date date2,Customer customer) {
this.id = id;
this.date= date2;
this.customer = customer;
items = new ArrayList<>();
}

public long getId() {
	return id;
}
public Date getDate() {
	return date;
}
public Customer getCustomer() {
	return customer;
}
public Iterable<OrderItem> getItems(){
	return items;
}
/*public int count() {
	return items.size();
}*/
public Order addItem(OrderItem item) {
	items.add(item);
	return this;
}
/*public Order removeItem(OrderItem item) {
	items.remove(item);
	return this;
}
public Order clearItems() {
	items.clear();
	return this;
}*/

}
