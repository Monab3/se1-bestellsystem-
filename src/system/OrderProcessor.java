package system;

import java.util.function.Consumer;

import datamodel.Order;
import datamodel.OrderItem;

final class OrderProcessor implements Components.OrderProcessor {
	private InventoryManager inventoryManager;
	OrderProcessor(InventoryManager inventoryManager){
		this.inventoryManager = inventoryManager;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode,
			Consumer<OrderItem> rejectedOrderItemCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) {
		long total; 
		total = (grossValue/100)*19;
		return total;
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		long total;
		switch(rateIndex) {
		case 1: total = (grossValue/100)*16; return total;
		case 2: total = (grossValue/100)*7;  return total;
		default: return 0;
		}
	}

}
