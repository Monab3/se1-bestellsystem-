package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;

final class OutputProcessor implements Components.OutputProcessor {
	private InventoryManager inventoryManager;
	private OrderProcessor orderProcessor;

	OutputProcessor(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		this.inventoryManager = inventoryManager;
		this.orderProcessor = orderProcessor;
	}

	@Override
	// FRAGE: soll ich printLineWidth einfach übernehmen ?!
	public void printOrders(List<Order> orders, boolean printVAT) {

		int printLineWidth = 94;
		StringBuffer sbAllOrders = new StringBuffer("-------------");
		StringBuffer sbLineItem = new StringBuffer();
		long gesamtWertallerBestellungen = 0;
		long vat=0;
		for (Order ausgabe : orders) {
			Customer customer = ausgabe.getCustomer();
			String customerName = splitName(customer, customer.getFirstName() + " " + customer.getLastName());
			String zeile = "#" + ausgabe.getId() + " " + customerName + "'s Bestellung: ";
			ArrayList<OrderItem> items = (ArrayList<OrderItem>) ausgabe.getItems();
			// item = (ArrayList<OrderItem>) ausgabe.getItems();
			long preisBestellung = 0;
			int unit;
			int laengeArray = items.size() - 1;
			for (OrderItem i : items) {
				unit = i.getUnitOrdered();
				zeile = zeile + unit + "x " + i.getDescribtion();
				if (laengeArray > 0) {
					zeile = zeile + ", ";
				}
				laengeArray -= 1;
				long preisArticle = i.getArticle().getUnitPrice();
				preisBestellung += (unit * preisArticle);
				
			}
			if(printVAT) {
				vat = vat +orderProcessor.vat(preisBestellung, 1);
				long v = orderProcessor.vat(preisBestellung, 1);
				System.out.println(v+" "+preisBestellung+" "+preisBestellung);
			}
			gesamtWertallerBestellungen = gesamtWertallerBestellungen + preisBestellung;
			System.out.println("gesamt: "+gesamtWertallerBestellungen);
			String fmtPrice = fmtPrice(preisBestellung, "EUR", 14);
			// String fmtPriceFinal = fmtPrice(gesamtWertallerBestellungen , "EUR", 14 );
			sbAllOrders.append("\n").append(fmtLine(zeile, fmtPrice, printLineWidth));

		}
		String fmtPriceFinal = fmtPrice(gesamtWertallerBestellungen, "EUR", 14);
		sbAllOrders.append("\n").append(fmtLine("-------------", "-------------", printLineWidth)).append("\n")
				.append(fmtLine("Gesamtwert aller Bestellungen:", fmtPriceFinal, printLineWidth));
	if (printVAT) {
			String vatString = fmtPrice(vat, "EUR", 14);
			sbAllOrders.append("\n").append(fmtLine("",vatString, printLineWidth));
			
		}
		// print sbAllOrders StringBuffer with all output to System.out
		System.out.println(sbAllOrders.toString());
	}

	@Override
	public void printInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public String fmtPrice(long price, String currency) {

		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer(leftStr);
		int shiftable = 0; // leading spaces before first digit
		for (int i = 1; rightStr.charAt(i) == ' ' && i < rightStr.length(); i++) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1; // - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max(0, excess - shiftable);
		if (shift2 > 0) {
			rightStr = rightStr.substring(shift2, rightStr.length());
			excess -= shift2;
		}
		if (excess > 0) {
			switch (excess) {
			case 1:
				sb.delete(sbLen - excess, sbLen);
				break;
			case 2:
				sb.delete(sbLen - excess - 2, sbLen);
				sb.append("..");
				break;
			default:
				sb.delete(sbLen - excess - 3, sbLen);
				sb.append("...");
				break;
			}
		}
		String strLineItem = String.format("%-" + (tab1 - 1) + "s%s", sb.toString(), rightStr);
		sb.setLength(0);
		sb.append(strLineItem);
		return sb;
	}

	@Override
	public String splitName(Customer customer, String name) {
		String[] nameArrayKomma = name.split(",");
		String[] nameArrayleer = name.split(" ");
		String lastName = "";
		String firstName = "";
		if (nameArrayKomma.length >= 2) {
			lastName = nameArrayKomma[0];
			for (int i = nameArrayKomma.length - 1; i > 0; i--) {
				firstName = firstName + " " + nameArrayKomma[i];
			}
		} else if (nameArrayleer.length >= 2) {
			lastName = nameArrayleer[nameArrayleer.length - 1];
			for (int i = 0; i < nameArrayleer.length - 1; i++) {
				firstName = firstName + " " + nameArrayleer[i];
			}
		}
		firstName = firstName.trim();
		lastName = lastName.trim();
		return (lastName + "," + firstName);
	}

	@Override
	public String singleName(Customer customer) {
		return (customer.getFirstName() + "" + customer.getLastName());
	}

	// extra methoden ?! soll ich die Übernehmen ??

	private String fmtPrice(long price, String prefix, String postfix) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if (prefix != null) {
			fmtPriceSB.append(prefix);
		}

		fmtPriceSB = fmtPrice(fmtPriceSB, price);

		if (postfix != null) {
			fmtPriceSB.append(postfix);
		}
		return fmtPriceSB.toString();
	}

	private StringBuffer fmtPrice(StringBuffer sb, long price) {
		if (sb == null) {
			sb = new StringBuffer();
		}
		double dblPrice = ((double) price) / 100.0; // convert cent to Euro
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("de"))); // rounds double
																										// to 2 digits

		String fmtPrice = df.format(dblPrice); // convert result to String in DecimalFormat
		sb.append(fmtPrice);
		return sb;
	}

	private String pad(String str, int width, boolean rightAligned) {
		String fmtter = (rightAligned ? "%" : "%-") + width + "s";
		String padded = String.format(fmtter, str);
		return padded;
	}

}
