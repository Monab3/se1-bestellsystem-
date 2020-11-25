package application;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.text.html.HTMLDocument.Iterator;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;


/**
 * 
 * Main class to launch the application.
 * 
 * @author svgr64
 *
 */
public class Application_1 {

	private final int printLineWidth = 84;

	public static void main( String[] args ) {

		System.out.println( "SE1-Bestellsystem" );

		Application_1 application = new Application_1();

		/* Customer cEric = new Customer( "C86516", "Eric Schulz-Mueller", "eric2346@gmail.com" );
		Customer cAnne = new Customer( "C64327", "Meyer, Anne", "+4917223524" );
		Customer cNadine = new Customer( "C12396", "Nadine Ulla Blumenfeld", "+4915292454" );
		Customer cWerner = new Customer("C1234","Timo Werner","tw@gmail.com");
		Customer cMueller = new Customer("C123456","Sandra Müller","samue62@gmx.de");
		

		Article aTasse = new Article( "SKU-458362", "Tasse", 299, 2000 );
		Article aBecher = new Article( "SKU-693856", "Becher", 149, 8400 );
		Article aKanne = new Article( "SKU-518957", "Kanne", 2000, 2400 );
		Article aTeller = new Article( "SKU-638035", "Teller", 649, 7000 );
		Article aKaffemaschiene =  new Article ("SKU-123","Kaffemaschiene",2999,500);
		Article aTeeKocher = new Article ("SKU-1234","Tee Kocher",1999,2000);

		// Eric's 1st order
		Order o5234 = new Order( 5234968294L, new Date(), cEric );
		OrderItem oi1 = new OrderItem( aKanne.getDescription(), aKanne, 1 );	// 1x Kanne
		o5234.addItem( oi1 );	// add OrderItem to Order 5234968294L

		// Eric's 2nd order
		Order o8592 = new Order( 8592356245L, new Date(), cEric );
		o8592.addItem(	// add three OrderItems to Order 8592356245L
			new OrderItem( aTeller.getDescription(), aTeller, 4 )		// 4x Teller
		).addItem(
			new OrderItem( aBecher.getDescription(), aBecher, 8 )		// 8x Becher
		).addItem(
			new OrderItem( "passende Tassen", aTasse, 4 )				// 4x passende Tassen
		);
		
		// Werner's first Order
		Order o123 = new Order(1234567890L,new Date(),cWerner);
		o123.addItem(
				new OrderItem(aKaffemaschiene.getDescription(),aKaffemaschiene,1)
				).addItem(new OrderItem(aTasse.getDescription(),aTasse,6));
		
		// Müllers first Order
		Order o1234 = new Order(1234675849L,new Date(),cMueller);
		o1234.addItem(
				new OrderItem(aTeeKocher.getDescription(),aTeeKocher,1)
				).addItem(new OrderItem(aBecher.getDescription(),aBecher,4)
				).addItem(new OrderItem(aTeller.getDescription(),aTeller,4));
		
		

		// Anne's order
		Order o3563 = new Order( 3563561357L, new Date(), cAnne );
		o3563.addItem(
			new OrderItem( aKanne.getDescription() + " aus Porzellan", aKanne, 1 )
		);

		// Nadine's order
		Order o6135 = new Order( 6135735635L, new Date(), cNadine );
		o6135.addItem(													// 12x Teller
			new OrderItem( aTeller.getDescription() + " blau/weiss Keramik", aTeller, 12 )
		);

		List<Order> orders = new ArrayList<Order>( List.of( o5234, o8592, o3563, o6135,o123,o1234 ) );


		application.printOrders( orders );	

	}

	private void printOrders( List<Order> orders ) {
		StringBuffer sbAllOrders = new StringBuffer("-------------" );
		StringBuffer sbLineItem = new StringBuffer();
		long gesamtWertallerBestellungen = 0;
        for(Order ausgabe :orders) {
        	Customer customer = ausgabe.getCustomer();
        	String zeile ="#"+ ausgabe.getId()+" "+customer.getFirstName()+"'s Bestellung: ";
        	ArrayList<OrderItem>items = (ArrayList<OrderItem>) ausgabe.getItems();
        	//item = (ArrayList<OrderItem>) ausgabe.getItems();
        	long preisBestellung = 0;
        	int unit;
        	int laengeArray = items.size()-1;
        		for(OrderItem i: items) {
        		unit = i.getUnitOrdered();
        		zeile= zeile+ unit+"x "+i.getDescribtion();
        		if(laengeArray>0) {
        			zeile= zeile+", ";
        		}
        		laengeArray-=1;
        	    long preisArticle = i.getArticle().getUnitPrice();
        	    preisBestellung += (unit*preisArticle);
        	}
        		gesamtWertallerBestellungen = gesamtWertallerBestellungen+preisBestellung;
        	String fmtPrice = fmtPrice( preisBestellung, "EUR", 14 );
        	//String fmtPriceFinal = fmtPrice(gesamtWertallerBestellungen , "EUR", 14 );
        	sbAllOrders.append( "\n" ).append(fmtLine( zeile,fmtPrice, printLineWidth ) );
        	
        	
        }
        String fmtPriceFinal = fmtPrice(gesamtWertallerBestellungen , "EUR", 14 );
        sbAllOrders.append( "\n" )
		.append( fmtLine( "-------------", "------------- -------------", printLineWidth ) )
		.append( "\n" )
		.append( fmtLine( "Gesamtwert aller Bestellungen:",fmtPriceFinal, printLineWidth ) );

	// print sbAllOrders StringBuffer with all output to System.out
	System.out.println( sbAllOrders.toString() );


	}

	private String fmtPrice( long price, String currency ) {
		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
		return fmtPrice;
	}

	private String fmtPrice( long price, String currency, int width ) {
		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
		return fmtPrice;
	}

	private String fmtPrice( long price, String prefix, String postfix ) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if( prefix != null ) {
			fmtPriceSB.append( prefix );
		}

		fmtPriceSB = fmtPrice( fmtPriceSB, price );

		if( postfix != null ) {
			fmtPriceSB.append( postfix );
		}
		return fmtPriceSB.toString();
	}


	private StringBuffer fmtPrice( StringBuffer sb, long price ) {
		if( sb == null ) {
			sb = new StringBuffer();
		}
		double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
		DecimalFormat df = new DecimalFormat( "#,##0.00",
			new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits

		String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
		sb.append( fmtPrice );
		return sb;
	}

	private String pad( String str, int width, boolean rightAligned ) {
		String fmtter = ( rightAligned? "%" : "%-" ) + width + "s";
		String padded = String.format( fmtter, str );
		return padded;
	}

	private StringBuffer fmtLine( String leftStr, String rightStr, int totalWidth ) {
		StringBuffer sb = new StringBuffer( leftStr );
		int shiftable = 0;		// leading spaces before first digit
		for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
			shiftable++;
		}
		final int tab1 = totalWidth - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max( 0, excess - shiftable );
		if( shift2 > 0 ) {
			rightStr = rightStr.substring( shift2, rightStr.length() );
			excess -= shift2;
		}
		if( excess > 0 ) {
			switch( excess ) {
			case 1:	sb.delete( sbLen - excess, sbLen ); break;
			case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
			default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
			}
		}
		String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
		sb.setLength( 0 );
		sb.append( strLineItem );
		return sb;
	}*/
}
}
