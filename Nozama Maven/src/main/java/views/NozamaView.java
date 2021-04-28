 package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;

import controllers.*;
import nozamaFiles.Customer;
import nozamaFiles.Item;
import nozamaFiles.ItemCatalog;
import nozamaFiles.ItemSpecification;
import views.*;

import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * The NozamaView class creates the main store page of Nozama
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class NozamaView {

	// Global J-Objects for Nomzama View
	private JFrame frame;
	private JTextField searchField;
	private JTable table;
	private TableRowSorter<NozamaController> sorter;
	private static NozamaController nm = null;
	private static Boolean managementState;

	/**
	 * Create and display the Nozama page
	 * 
	 * @param true if management state is available, false otherwise
	 * @param Customer to open the Nozama store page for
	 * @throws Exception if the NozamaView cannot be opened
	 * @return void
	 */
	public static void createAndShowGUI(Boolean b, Customer c) {

		// Passed management state and customer to determine interface showing
		// constraints
		nm = new NozamaController(c);
		managementState = b;

		// Add the runner to show the GUI for Nomzama View
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					// Initialize Nozama Window
					NozamaView window = new NozamaView(c);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Launch the main page for the customer
	 * 
	 * @param Customer to open the Nozama store page for
	 */
	public NozamaView(Customer c) {
		// Constructor for initializing and showing the Nozama View interface
		initialize(c);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException if the window cannot be closed correctly
	 */
	private void initialize(Customer c) {

		// Initialize the sorter and frames to their respects, and settings
		sorter = new TableRowSorter<NozamaController>(nm);
		frame = new JFrame("Home Page");
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Window closing event to guarantee safe data
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(nm.getCustomer().getCartFile()));
					out.write(nm.getCustomer().getCustomerCart().toString());
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Initialize table set up
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 16, 27, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Initialize search button
		JButton searchButton = new JButton("Search");

		// Add listener for search to filter table based on text search
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Initialize the text to input and the row filter for condensing
				String text = searchField.getText();
				RowFilter<NozamaController, Object> rf = null;

				try {
					// Set the condensed row filter
					rf = RowFilter.regexFilter(text, 0);
				} catch (java.util.regex.PatternSyntaxException f) {
					return;
				}
				// Set the row filters to the table
				sorter.setRowFilter(rf);
			}
		});

		// Initialize the layout for clear search, and initialize action button
		JButton btnNewButton = new JButton("Clear Search");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 1;

		// Add the button to the panel
		panel.add(btnNewButton, gbc_btnNewButton);

		// Add listener for the Clean
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Initialize the text to empty and the row filter for condensing
				RowFilter<NozamaController, Object> rf = null;
				searchField.setText("");

				try {
					// REset row filter
					rf = RowFilter.regexFilter("(?s).*", 0);
				} catch (java.util.regex.PatternSyntaxException f) {
					return;
				}

				// Set the row filters to the table
				sorter.setRowFilter(rf);
			}
		});

		// Initialize search field and the layout
		searchField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 11;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panel.add(searchField, gbc_textField);
		searchField.setColumns(10);
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchButton.gridx = 13;
		gbc_searchButton.gridy = 0;
		panel.add(searchButton, gbc_searchButton);

		// Initialize the scroll pane for the table and position table to be properly
		// sized and fit
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.gridwidth = 11;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		// Set the table and scroll pane settings, as well as position/size
		table = new JTable(nm);
		table.setRowSorter(sorter);
		table.removeColumn(table.getColumnModel().getColumn(3));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setPreferredSize(new Dimension(450, 110));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Create cart button to fire cart display
		JButton cartButton = new JButton("Show Cart");

		// Listener for cart display
		cartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Fire display cart
				displayCart();
			}
		});

		// Set grid constraints for cart button (positioning)
		GridBagConstraints gbc_cartButton = new GridBagConstraints();
		gbc_cartButton.insets = new Insets(0, 0, 5, 0);
		gbc_cartButton.gridx = 13;
		gbc_cartButton.gridy = 7;
		panel.add(cartButton, gbc_cartButton);

		// Create wish list button
		JButton wishlistButton = new JButton("Show Wishlist");

		// Listener for wish list button
		wishlistButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Fire display wish list
				displayWishlist();
			}
		});

		// Set grid constraints for wish list button (positioning)
		GridBagConstraints gbc_wishlistButton = new GridBagConstraints();
		gbc_wishlistButton.insets = new Insets(0, 0, 5, 0);
		gbc_wishlistButton.gridx = 13;
		gbc_wishlistButton.gridy = 8;
		panel.add(wishlistButton, gbc_wishlistButton);

		// ONLY IF COMPANY (restricted access shown for interface)
		if (managementState) {

			// Create report button
			JButton reportButton = new JButton("Generate Report");

			// Listener for report button
			reportButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// Initialize file for generated report
					File generateFile = new File("resources/orders/GeneratedReport.csv");

					try {
						// Initialize scanner and file to be written to
						Scanner scanner = new Scanner(generateFile);
						final JFileChooser fileChosen = new JFileChooser();
						int optChosen = fileChosen.showSaveDialog(null);

						// If option was approved
						if (optChosen == JFileChooser.APPROVE_OPTION) {

							try {
								// Initialize files
								File expFile = fileChosen.getSelectedFile();
								FileWriter toWrite = new FileWriter(expFile);

								// Write the already generated file to the output file
								while (scanner.hasNext()) {
									toWrite.write(scanner.next());
									toWrite.append('\n');
								}

								// Close writer
								toWrite.close();
							} catch (IOException x) {
								x.printStackTrace();
							}
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});

			// Add the report button by formatting
			GridBagConstraints gbc_reportButton = new GridBagConstraints();
			gbc_reportButton.insets = new Insets(0, 0, 5, 0);
			gbc_reportButton.gridx = 13;
			gbc_reportButton.gridy = 3;
			panel.add(reportButton, gbc_reportButton);

			// Create delete product button
			JButton deleteProductDBButton = new JButton("Delete Product DB");

			// Listener for delete product button
			deleteProductDBButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// Initialize and remove from instance
					List<ItemSpecification> tempItemList = ItemCatalog.getItems();
					tempItemList.remove(table.getSelectedRow());
					ItemCatalog.setItems(tempItemList);

					// Remove row from data base
					nm.data.remove(table.getSelectedRow());

					// Fire changes
					nm.fireTableDataChanged();
					frame.setVisible(true);
				}
			});

			// Add the delete product button by formatting
			GridBagConstraints gbc_deleteProductDBButton = new GridBagConstraints();
			gbc_deleteProductDBButton.insets = new Insets(0, 0, 5, 0);
			gbc_deleteProductDBButton.gridx = 2;
			gbc_deleteProductDBButton.gridy = 8;
			panel.add(deleteProductDBButton, gbc_deleteProductDBButton);

			// Create button for stocking
			JButton restockButton = new JButton("Restock Item");

			// Listener for stock
			restockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// Initialize frame and labels for stocking
					JFrame quanFrame = new JFrame("Add Quantity");
					JPanel quanForm = new JPanel(new GridLayout(0, 1));
					JLabel quanLabel = new JLabel("Enter Quantity to Add: ");
					JTextField quanField = new JTextField();
					quanField.setSize(new Dimension(75, 30));

					// Edit layout for card input prompt
					quanForm.add(quanLabel, BorderLayout.WEST);
					quanLabel.setLabelFor(quanField);
					quanForm.add(quanField);
					// Add the entire form to the frame
					quanFrame.add(quanForm);

					// Fire changes
					quanFrame.revalidate();
					quanForm.revalidate();

					// Set buttons for Adding quantity check
					UIManager.put("OptionPane.cancelButtonText", "Cancel");
					UIManager.put("OptionPane.okButtonText", "Add");

					// Generate if add is pressed
					int res = JOptionPane.showConfirmDialog(null, quanForm, "Company Login",
							JOptionPane.OK_CANCEL_OPTION);

					// If valid quantity is pressed and add is pressed
					if (res == JOptionPane.OK_OPTION) {

						// Initialize helpers for stock changing
						int inputQuan = Integer.parseInt(quanField.getText());
						List<ItemSpecification> tempItemList = ItemCatalog.getItems();

						tempItemList.get(table.getSelectedRow())
								.setQuantity(tempItemList.get(table.getSelectedRow()).getQuantity() + inputQuan);
						ItemCatalog.setItems(tempItemList);

						// Update the table changes and re-initialize
						nm.fireTableDataChanged();
						frame.setVisible(true);
					}
				}
			});

			// Add the stock product button by formatting
			GridBagConstraints gbc_restockButton = new GridBagConstraints();
			gbc_restockButton.insets = new Insets(0, 0, 5, 0);
			gbc_restockButton.gridx = 3;
			gbc_restockButton.gridy = 8;
			panel.add(restockButton, gbc_restockButton);

			// Create add product button
			JButton addProductButton = new JButton("Add Product DB");

			// Listener for add product button
			addProductButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// Initialize add product frame
					final JFrame frame = new JFrame("Add Product");
					JPanel panel = new JPanel(new GridLayout(0, 1));

					// Create the labels
					JLabel nameLabel = new JLabel("Name: ");
					JLabel descriptionLabel = new JLabel("Description: ");
					JLabel priceLabel = new JLabel("Price: ");
					JLabel itemIDLabel = new JLabel("Item ID: ");
					JLabel quantityLabel = new JLabel("Quantity: ");

					// Create the text fields
					JTextField nameField = new JTextField();
					JTextField descriptionField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField itemIDField = new JTextField();
					JTextField quantityField = new JTextField();

					// Set text field size
					nameField.setSize(new Dimension(75, 30));
					descriptionField.setSize(new Dimension(75, 30));
					priceField.setSize(new Dimension(75, 30));
					itemIDField.setSize(new Dimension(75, 30));
					quantityField.setSize(new Dimension(75, 30));

					// Set the labels and add them
					panel.add(nameLabel);
					nameLabel.setLabelFor(nameField);
					panel.add(nameField);
					panel.add(descriptionLabel);
					descriptionLabel.setLabelFor(descriptionField);
					panel.add(descriptionField);
					panel.add(priceLabel);
					priceLabel.setLabelFor(priceField);
					panel.add(priceField);
					panel.add(itemIDLabel);
					itemIDLabel.setLabelFor(itemIDField);
					panel.add(itemIDField);
					panel.add(quantityLabel);
					quantityLabel.setLabelFor(quantityField);
					panel.add(quantityField);

					// Add all labels in panel to frame
					frame.add(panel);

					// Set buttons to hold correct naming
					UIManager.put("OptionPane.cancelButtonText", "Cancel");
					UIManager.put("OptionPane.okButtonText", "Save");

					// Generate the result of the button press
					int result = JOptionPane.showConfirmDialog(null, panel, "Edit", JOptionPane.OK_CANCEL_OPTION);

					// If save pressed
					if (result == JOptionPane.OK_OPTION) {

						// Initialize holders for file writing
						String name = nameField.getText();
						String description = descriptionField.getText();
						Double price = Double.parseDouble(priceField.getText());
						String itemID = itemIDField.getText();
						Integer quantity = Integer.parseInt(quantityField.getText());
						ItemSpecification tempItemSpec = new ItemSpecification(name, description, price, itemID,
								quantity);

						// Add the item spec
						List<ItemSpecification> tempItemList = ItemCatalog.getItems();
						tempItemList.add(tempItemSpec);
						ItemCatalog.setItems(tempItemList);

						// Update nm after changes
						nm.update();

						// Open files for catalog
						File file = new File("resources/testCatalog.csv");
						FileWriter fwriter;

						try {
							// Initialize writers
							fwriter = new FileWriter(file, true);
							BufferedWriter writer = new BufferedWriter(fwriter);

							// Write item to writer
							writer.write("\n");
							writer.write(name + "," + description + "," + price + "," + itemID + "," + quantity);

							// Close writer upon completion
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

					// Show the frame
					frame.pack();
					frame.setVisible(true);
				}
			});

			// Add the add product button by formatting
			GridBagConstraints gbc_addProductButton = new GridBagConstraints();
			gbc_addProductButton.insets = new Insets(0, 0, 5, 0);
			gbc_addProductButton.gridx = 1;
			gbc_addProductButton.gridy = 8;
			panel.add(addProductButton, gbc_addProductButton);
		}

		Action addToCart = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JPanel form2 = new JPanel(new GridLayout(0, 1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());

				Item toAdd = new Item((String) nm.getValueAt(modelRow, 3));
				Integer available = ItemCatalog.getItemSpecification(toAdd.getItemID()).getQuantity();

				JLabel quantityLbl = new JLabel("Quantity Desired:");
				JTextField qTextField = new JTextField();
				JLabel availabiltyLbl = new JLabel("Only " + available + " available");
				qTextField.setText("1");
				form2.add(quantityLbl);
				form2.add(qTextField);
				form2.add(availabiltyLbl);

				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Add Item");

				int res = JOptionPane.showConfirmDialog(null, form2, "Add Item To Cart", JOptionPane.OK_CANCEL_OPTION);
				if (res == JOptionPane.OK_OPTION) {
					Integer quantity = Integer.parseInt(qTextField.getText());

					// validate quantity
					if (quantity <= 0) {
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity can't be less than or equal to 0!");
					} else if (quantity <= available) {
						nm.getCustomer().getCustomerCart().addItemToCart(toAdd, quantity);
						BufferedWriter out;
						try {
							out = new BufferedWriter(new FileWriter("resources/carts/" + c.getUsername() + "Cart.csv"));
							out.write(c.getWishlist().toString());
							out.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(form2, "ERROR: Maximum for sale is " + available);
					}
				}
			}
		};

		// Add the actual instance of the add to cart button
		ButtonColumn addToCartButton = new ButtonColumn(table, addToCart, 2);

		// Added listener for add to cart button press
		Action addToWishlist = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				// Initialize J-Style objects for displaying add to cart interface
				JPanel form2 = new JPanel(new GridLayout(0, 1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JLabel quantityLbl = new JLabel("Quantity: ");
				JTextField qTextField = new JTextField();

				// Set initial quantity
				qTextField.setText("1");

				// Add the quantity label and field
				form2.add(quantityLbl);
				form2.add(qTextField);

				// Generate button text for option decide
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Add Item");

				// Determine which option is chosen
				int res = JOptionPane.showConfirmDialog(null, form2, "Add Item To Wishlist",
						JOptionPane.OK_CANCEL_OPTION);

				// If add item chosen
				if (res == JOptionPane.OK_OPTION) {

					// Initialize variables for adding to wish list
					Item toAdd = new Item((String) nm.getValueAt(modelRow, 3));
					Integer quantity = Integer.parseInt(qTextField.getText());
					Integer available = ItemCatalog.getItemSpecification(toAdd.getItemID()).getQuantity();

					// Validate quantity (Negative quantity)
					if (quantity <= 0) {
						// Generate Error
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity can't be less than or equal to 0!");
					}
					// VALID vase
					else if (quantity <= available) {

						// Add item to cart based on nm
						nm.getCustomer().getWishlist().addItemToCart(toAdd, quantity);

						// Declare out buffered reader
						BufferedWriter out;

						try {
							// Initialize the buffered reader
							out = new BufferedWriter(
									new FileWriter("resources/wishlists/" + c.getUsername() + "Wishlist.csv"));

							// Set the buffer out to the wish list
							out.write(c.getWishlist().toString());

							// Close the buffered out
							out.close();

						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} else {
						// Display error if improper quantity
						JOptionPane.showMessageDialog(form2, "ERROR: Maximum for sale is " + available);
					}
				}
			}
		};

		// Add the actual instance of the wish list button to the table
		ButtonColumn addToWishlistButton = new ButtonColumn(table, addToWishlist, 3);
	}

	/**
	 * Display the information of the customer's shopping cart in a new window
	 * 
	 * @return void
	 */
	public void displayCart() {
		// Called to display the cart
		ShoppingCartView.createAndShowGUI(nm.getCustomer());

	}

	/**
	 * Display the information of the customer's wishlist in a new window
	 * 
	 * @return void
	 */
	public void displayWishlist() {
		// Called to display the wish list
		WishlistView.createAndShowGUI(nm.getCustomer());
	}
}
