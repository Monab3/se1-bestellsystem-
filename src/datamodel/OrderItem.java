package datamodel;

public class OrderItem {
private String describtion;
private final Article article;
private int unitOrdered;
OrderItem(String desc,Article article, int units) {
	this.describtion = desc;
	this.article = article;
	this.unitOrdered = units;
}
public String getDescribtion() {
	return describtion; 
}
public void setDescribtion(String desc) {
	describtion = desc;
}
public Article getArticle() {
	return article;
}
public int getUnitOrdered() {
	return unitOrdered;
}
public void setUnitOrdered(int number) {
	unitOrdered = number;
}
}
