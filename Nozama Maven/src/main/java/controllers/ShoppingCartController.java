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

/**
 * The ShoppingCartController controls the shopping cart which is responsible for
 * doing different actions
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class ShoppingCartController extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Name", "Cost", "Quantity", "", "" };
	private List<Object[]> data;
	private File customerFile;
	private Customer c;

	/**
	 * Create the ShoppingCartController which will create the shopping cart page
	 */
	public ShoppingCartController() {
		data = new ArrayList<Object[]>();
	}

	@Override
	/**
	 * Get the row count
	 *
	 * @return integer representing the row count
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	/**
	 * Get the column count
	 *
	 * @return integer representing the column count
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	/**
	 * Get the name of the column
	 *
	 * @param integer representing the column index
	 * @return string representing the name of the column
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	/**
	 * Get the value at a certain index of the table
	 *
	 * @param integer representing the row to look at
	 * @param integer representing the column to look at
	 * @return the object data found at that index
	 */
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

	/**
	 * Get the Class found at the column passed
	 *
	 * @param integer representing the column to use to find the class
	 * @return the Class found 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/**
	 * Get the cart held by a customer
	 *
	 * @return the ShoppingCart of the customer
	 */
	public ShoppingCart getCart() {
		dataToCart();
		return c.getWishlist();
	}

	/**
	 * Set the customer for their cart
	 *
	 * @return customer to set is cart to
	 */
	public void setCustomer(Customer c) {
		this.c = c;
		cartToData();
		fireTableDataChanged();
	}

	/**
	 * Get the customer using the Nozama store site
	 * 
	 * @return Customer using the store
	 */
	public Customer getCustomer() {
		return this.c;
	}

	/**
	 * Check if the cell of the table is editable
	 *
	 * @param integer representing the row of the table
	 * @param integer representing the column of the table
	 * @return true if the cell is editable, false otherwise
	 */
	public boolean isCellEditable(int row, int col) {
		return col > 1;
	}

	/**
	 * Set the value at the specific row and column asked for by the user
	 *
	 * @param value to put into the data table
	 * @param row to set the value into
	 * @param column to set the value
	 * @return void
	 */
	public void setValueAt(Object value, int row, int col) {
		data.get(row)[col] = value;
		dataToCart();
		fireTableCellUpdated(row, col);
	}

	/**
	 * Get the subtotal of the items in the cart
	 * 
	 * @return double representing the subtotal of the cart
	 */
	public Double getSubtotal() {
		return c.getCustomerCart().getSubtotal();
	}

	/**
	 * Get the file of the Customer
	 * 
	 * @return File of the customer's information
	 */
	public File getCustomerFile() {
		return customerFile;
	}

	/**
	 * Set the information of the Customer into a customer file
	 * 
	 * @param file filled with customer information
	 */
	public void setCustomerFile(File f) {
		customerFile = f;
	}

	/**
	 * Move the data from the cart into the ShoppingCart data
	 */
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

	/**
	 * Move the data from the data cart into the Customer's cart
	 */
	public void dataToCart() {
		ShoppingCart newCart = new ShoppingCart();
		for (Object[] o : data) {
			Item i = new Item((String) o[4]);
			newCart.addItemToCart(i, (Integer) o[2]);
		}

		c.setCustomerCart(newCart);
		fireTableDataChanged();
	}

	/**
	 * Remove a row from the data table
	 * 
	 * @param row number to remove
	 */
	public void removeRow(int modelRow) {
		// TODO Auto-generated method stub
		data.remove(modelRow);
		dataToCart();
		fireTableDataChanged();
	}
}
