package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;
import system.ComponentFactory;
import system.Components;

public class Application_2 {

	public static void main(String[] args) {
		 System.out.println( "SE1‐Bestellsystem" );
		    ComponentFactory componentFactory = ComponentFactory.getInstance();
		    Components.OutputProcessor outputProcessor = componentFactory.getOutputProcessor();
		    Components.DataFactory dataFactory = componentFactory.getDataFactory();
		    Components.OrderProcessor orderProcessor = componentFactory.getOrderProcessor();
		    /*
		     * Erzeugung der Kunden, Artikel und Bestellungen über die DataFactory...
		     */
		    Customer cEric = dataFactory.createCustomer( "Eric Schulz‐Mueller", "eric2346@gmail.com" );
		    Customer cAnne = dataFactory.createCustomer( "Meyer, Anne", "+4917223524" );
		    
			Customer cNadine = dataFactory.createCustomer("Nadine Ulla Blumenfeld", "+4915292454" );
			Customer cWerner = dataFactory.createCustomer("Timo Werner","tw@gmail.com");
			Customer cMueller = dataFactory.createCustomer("Sandra Müller","samue62@gmx.de");
		    
		    Article aTasse = dataFactory.createArticle( "Tasse", 299, 2000 );
		    Article aBecher = dataFactory.createArticle( "Becher", 149, 8400 );
		    Article aKanne = dataFactory.createArticle(  "Kanne", 2000, 2400 );
			Article aTeller = dataFactory.createArticle("Teller", 649, 7000 );
			Article aKaffemaschiene =  dataFactory.createArticle("Kaffemaschiene",2999,500);
			Article aTeeKocher =dataFactory.createArticle("Tee Kocher",1999,2000);
		    
		    // Eric's 1st order
		    Order o5234 = dataFactory.createOrder( cEric );
		    OrderItem oi1 = dataFactory.createOrderItem(aKanne.getDescription(), aKanne, 1 ); // 1x Kanne
		    o5234.addItem( oi1 );
		    
		    // Eric's 2nd order
			Order o8592 = dataFactory.createOrder( cEric );
			OrderItem oi2 = dataFactory.createOrderItem(aTeller.getDescription(), aTeller, 4);
			OrderItem oi3 = dataFactory.createOrderItem(aBecher.getDescription(), aBecher, 8 );
			OrderItem oi4 = dataFactory.createOrderItem("passende Tassen", aTasse, 4);
			o8592.addItem( oi2 );
			o8592.addItem( oi3 );
			o8592.addItem( oi4 );
			
			// Werner's first Order
			Order o123 = dataFactory.createOrder( cWerner);
			OrderItem oi5 = dataFactory.createOrderItem(aKaffemaschiene.getDescription(),aKaffemaschiene,1);
			OrderItem oi6 = dataFactory.createOrderItem(aTasse.getDescription(),aTasse,6);
			o123.addItem( oi5);
			o123.addItem( oi6);
			
			// Müllers first Order
			Order o1234 = dataFactory.createOrder(cMueller);
			OrderItem oi7 = dataFactory.createOrderItem(aTeeKocher.getDescription(),aTeeKocher,1);
			OrderItem oi8 = dataFactory.createOrderItem(aBecher.getDescription(),aBecher,4);
			OrderItem oi9 = dataFactory.createOrderItem(aTeller.getDescription(),aTeller,4);
			o1234.addItem(oi7);
			o1234.addItem(oi8);
			o1234.addItem(oi9);

			// Anne's order
			Order o3563 = dataFactory.createOrder(cAnne );
			OrderItem oi10 = dataFactory.createOrderItem( aKanne.getDescription() + " aus Porzellan", aKanne, 1 );
			o3563.addItem(oi10);

			// Nadine's order
			Order o6135 = dataFactory.createOrder(cNadine);
			OrderItem oi11 = dataFactory.createOrderItem(aTeller.getDescription() + " blau/weiss Keramik", aTeller, 12);
			o6135.addItem(oi11);
			
			
		    
		    List<Order> orders = new ArrayList<Order>( List.of( o5234, o8592, o3563, o6135,o123,o1234));
		    outputProcessor.printOrders( orders,true); // Ausgabe aller Bestellungen
	}

}
