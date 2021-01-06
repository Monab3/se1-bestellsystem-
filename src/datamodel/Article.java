package datamodel;


public class Article {
private final String id;
private String describtion; 
private long unitPrice;
private int unitsInStore;
Article(String id, String descr, long price, int unit) {
	this.id = id;
	setDescribtion(descr);
	setUnitPrice(price);
	setUnitsInStore(unit);
}
public String getId() {
	return id;
}
public String getDescription() {
	return describtion;
}
public void setDescribtion(String desc) {
	if (desc == null)
		describtion = "";
	else
	describtion= desc;
}
public long getUnitPrice() {
		return unitPrice;
}
public void setUnitPrice(long price) {
	if(price < 0 || price == Long.MAX_VALUE)
		unitPrice = 0;
	else
		unitPrice = price;
}
public int getUnitsInStore() {
	return unitsInStore;
}
public void setUnitsInStore(int number) {
	if(number < 0 || number ==Integer.MAX_VALUE)
		unitsInStore = 0;
	else
	unitsInStore=number;
}
	











}
