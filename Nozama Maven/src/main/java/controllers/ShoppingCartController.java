package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nozamaFiles.Customer;
import nozamaFiles.Item;
import nozamaFiles.ItemCatalog;
import nozamaFiles.ItemSpecification;
import nozamaFiles.Pair;
import nozamaFiles.ShoppingCart;

public class ShoppingCartController extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Name", "Cost", "Quantity", "", "" };
	private List<Object[]> data;
	private File customerFile;
	private Customer c;

	public ShoppingCartController() {
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

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public ShoppingCart getCart() {
		dataToCart();
		return c.getWishlist();
	}

	public void setCustomer(Customer c) {
		this.c = c;
		cartToData();
		fireTableDataChanged();
	}

	public Customer getCustomer() {
		return this.c;
	}

	public boolean isCellEditable(int row, int col) {
		return col > 1;
	}

	public void setValueAt(Object value, int row, int col) {
		data.get(row)[col] = value;
		dataToCart();
		fireTableCellUpdated(row, col);
	}

	public Double getSubtotal() {
		return c.getCustomerCart().getSubtotal();
	}

	public File getCustomerFile() {
		return customerFile;
	}

	public void setCustomerFile(File f) {
		customerFile = f;
	}

	public void cartToData() {
		List<Pair<Item, Integer>> contents = c.getCustomerCart().getCart();
		List<Object[]> cartData = new ArrayList<Object[]>();
		ItemSpecification is = null;

		// TODO fix later for adding image column, edit, and remove
		for (Pair<Item, Integer> p : contents) {
			is = ItemCatalog.getItemSpecification(p.first.getItemID());
			Object[] itemData = new Object[5];
			itemData[0] = is.getName();
			itemData[1] = is.getPrice();
			itemData[2] = p.second;
			itemData[3] = "Edit";
			itemData[4] = p.first.getItemID();

			cartData.add(itemData);
		}
		this.data = cartData;
		fireTableDataChanged();
	}

	public void dataToCart() {
		ShoppingCart newCart = new ShoppingCart();
		for (Object[] o : data) {
			Item i = new Item((String) o[4]);
			newCart.addItemToCart(i, (Integer) o[2]);
		}

		c.setCustomerCart(newCart);
		fireTableDataChanged();
	}

	public void removeRow(int modelRow) {
		// TODO Auto-generated method stub
		data.remove(modelRow);
		dataToCart();
		fireTableDataChanged();
	}
}
