package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nozamaFiles.*;

/**
 * The NozamaController class controls the store page which is responsible for
 * doing different actions
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class NozamaController extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Name", "Cost", "", "", "" };
	public List<Object[]> data;
	private Customer c;

	/**
	 * Create the NozamaController which will create the store page
	 *
	 * @param c, Customer who the page is created for
	 */
	public NozamaController(Customer c) {
		data = new ArrayList<Object[]>();
		// TODO for test purposes, refactor later to deal with different customers
		// c = new Customer("testCustomer", null, null, 0, new ShoppingCart(), null,
		// true, new ShoppingCart(), new File("textCart.csv"));
		this.c = c;
		loadData();
	}

	@Override
	/**
	 * Get the row count
	 *
	 * @return int representing the row count
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	/**
	 * Get the column count
	 *
	 * @return int representing the column count
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	/**
	 * Get the value at a certain index of the table
	 *
	 * @param rowIndex, int representing the row to look at
	 * @param columnIndex, int representing the column to look at
	 * @return Object data found at that index
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex)[columnIndex];
	}

	/**
	 * Load the data of the store into the store page
	 */
	public void loadData() {

		// FIXME change the file to be the customer's specific cart file
		File file = new File("testCatalog.csv");
		try {
			file.createNewFile();
			ItemCatalog.loadData(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (ItemSpecification i : ItemCatalog.getItems()) {
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

	/**
	 * Update the store page to add new items to cart and wishlist
	 */
	public void update() {
		data = new ArrayList<Object[]>();
		for (ItemSpecification i : ItemCatalog.getItems()) {
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

	/**
	 * Load the cart held by a customer
	 *
	 * @param f, File used to load a customer cart
	 */
	public void loadCart(File f) {
		try {
			c.getCustomerCart().loadCart(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the cart held by a customer
	 *
	 * @return ShoppingCart of the customer
	 */
	public ShoppingCart getCart() {
		return c.getCustomerCart();
	}

	/**
	 * Check if the cell of the table is editable
	 *
	 * @param row, int representing the row of the table
	 * @param col, int representing the column of the table
	 * @return boolean, true if the cell is editable, false otherwise
	 */
	public boolean isCellEditable(int row, int col) {
		return col > 1;
	}

	/**
	 * Get the customer using the Nozama store site
	 * 
	 * @return Customer using the store
	 */
	public Customer getCustomer() {
		return c;
	}
}
