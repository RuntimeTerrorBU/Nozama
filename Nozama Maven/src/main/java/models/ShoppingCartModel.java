package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nozamaFiles.Item;
import nozamaFiles.ItemCatalog;
import nozamaFiles.ItemSpecification;
import nozamaFiles.Pair;
import nozamaFiles.ShoppingCart;

public class ShoppingCartModel extends AbstractTableModel {
	private ShoppingCart cart = new ShoppingCart();
	private String[] columnNames = { "Name", "Cost", "Quantity" };
	private List<Object[]> data;

	public ShoppingCartModel() {
		data = new ArrayList<Object[]>();
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

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
		cartToData();
		fireTableDataChanged();
	}
	
	public Double getSubtotal() {
		double subtotal = 0.0;
		for(Object[] cols:data) {
			subtotal += (double)cols[1] * (int)cols[2];
		}
		
		return subtotal;
	}
	
	public void cartToData() {
		List<Pair<Item, Integer>> contents = cart.getCart();
		List<Object[]> cartData = new ArrayList<Object[]>();
		ItemSpecification is = null;

		//TODO fix later for adding image column, edit, and remove
		
		
		for (Pair<Item, Integer> p : contents) {
			is = ItemCatalog.getItemSpecification(p.first.getItemID());
			Object[] itemData = new Object[3];
			itemData[0] = is.getName();
			itemData[1] = is.getPrice();
			itemData[2] = p.second;
			
			cartData.add(itemData);
		}
		this.data = cartData;
		fireTableDataChanged();
	}
	
	public void loadData(File file) throws IOException {
		cart.loadCart(file);
		cartToData();
		fireTableDataChanged();
	}
	
}
