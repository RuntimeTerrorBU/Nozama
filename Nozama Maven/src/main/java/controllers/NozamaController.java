package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nozamaFiles.*;

public class NozamaController extends AbstractTableModel {
	private String[] columnNames = {"Name", "Cost", "", "", ""};
	private List<Object[]> data;
	private Customer c;
	
	public NozamaController() {
		data = new ArrayList<Object[]>();
		//TODO for test purposes, refactor later to deal with different customers
		c = new Customer("testCustomer", null, null, 0, null);
		loadData();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex)[columnIndex];
	}
	
	
	public void loadData() {
		//FIXME change the file to be the customer's specific cart file
		File file = new File("resources/testCatalog.csv");
		try {
			ItemCatalog.loadData(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ItemSpecification i : ItemCatalog.getItems()) {
			Object[] colData = new Object[columnNames.length];
			colData[0] = i.getName();
			colData[1] = i.getPrice();
			colData[2] = "Add To Cart";
			colData[3] = i.getItemID();
			colData[4] = "Add To Wishlist";
			data.add(colData);
		}
		fireTableDataChanged();
		
	}
	
	public void loadCart(File f) {
		try {
			c.getCustomerCart().loadCart(f);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public ShoppingCart getCart() {
		return c.getCustomerCart();
	}
	
	public boolean isCellEditable(int row, int col) {
		return col > 1;
	}
	
	public Customer getCustomer() {
		return c;
	}
	
	
}
