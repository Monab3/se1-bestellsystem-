package datamodel;

public class OrderItem {
private String describtion;
private final Article article;
private int unitOrdered;
OrderItem(String desc,Article article, int units) {
	setDescribtion(desc);
	this.article = article;
	setUnitOrdered(units);
}
public String getDescribtion() {
	
	return describtion; 
}
public void setDescribtion(String desc) {
	if (desc == null)
		describtion = "";
	else
	describtion= desc;
}
public Article getArticle() {
	return article;
}
public int getUnitOrdered() {
	return unitOrdered;
}
public void setUnitOrdered(int number) {
	if(number < 0 )
		unitOrdered = 0;
	else
	unitOrdered = number;
}
}
