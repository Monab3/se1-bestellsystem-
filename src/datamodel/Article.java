package datamodel;


public class Article {
private final String id;
private String describtion; 
private long unitPrice;
private int unitsInStore;
Article(String id, String descr, long price, int unit) {
	this.id = id;
	describtion= descr;
	unitPrice = price;
	unitsInStore = unit;
}
public String getId() {
	return id;
}
public String getDescription() {
	return describtion;
}
public void setDescribtion(String desc) {
	describtion= desc;
}
public long getUnitPrice() {
		return unitPrice;
}
public void setUnitPrice(long price) {
	unitPrice = price;
}
public int getUnitsInStore() {
	return unitsInStore;
}
public void setUnitsInStore(int number) {
	unitsInStore=number;
}
	











}
