package views;

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

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;

import controllers.*;
import nozamaFiles.Item;
import nozamaFiles.ItemCatalog;
import nozamaFiles.ItemSpecification;
import views.*;

import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NozamaView {

	private JFrame frame;
	private JTextField searchField;
	private JTable table;
	private TableRowSorter<NozamaController> sorter;
	private NozamaController nm = new NozamaController();
	private static Boolean managementState;

	public static void createAndShowGUI(Boolean b) {
		
		managementState = b;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NozamaView window = new NozamaView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public NozamaView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO loadCart is for testing only
		sorter = new TableRowSorter<NozamaController>(nm);
		frame = new JFrame("Home Page");
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(nm.getCustomer().getCartFile()));
					out.write(nm.getCustomer().getCustomerCart().toString());
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
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

		JButton searchButton = new JButton("Search");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = searchField.getText();
				// FIXME Find out how to filter
				RowFilter<NozamaController, Object> rf = null;
			    //If current expression doesn't parse, don't update.
			    try {
			        rf = RowFilter.regexFilter(text, 0);
			    } catch (java.util.regex.PatternSyntaxException f) {
			        return;
			    }
			    sorter.setRowFilter(rf);
			}
		});
		
		JButton btnNewButton = new JButton("Clear Search");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RowFilter<NozamaController, Object> rf = null;
				searchField.setText("");
				try {
					rf = RowFilter.regexFilter("(?s).*" , 0);
				} catch (java.util.regex.PatternSyntaxException f) {
					return;
				}
				sorter.setRowFilter(rf);
			}
		});

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

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.gridwidth = 11;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable(nm);
		table.setRowSorter(sorter);
		table.removeColumn(table.getColumnModel().getColumn(3));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setPreferredSize(new Dimension(450, 110));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JButton cartButton = new JButton("Show Cart");
		cartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayCart();
			}
		});

		GridBagConstraints gbc_cartButton = new GridBagConstraints();
		gbc_cartButton.insets = new Insets(0, 0, 5, 0);
		gbc_cartButton.gridx = 13;
		gbc_cartButton.gridy = 7;
		panel.add(cartButton, gbc_cartButton);
		
		// add wishlist button
		JButton wishlistButton = new JButton("Show Wishlist");
		wishlistButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayWishlist();
				System.out.println("SHOW WISHLIST CLICKED");
			}
		});
		
		GridBagConstraints gbc_wishlistButton = new GridBagConstraints();
		gbc_wishlistButton.insets = new Insets(0, 0, 5, 0);
		gbc_wishlistButton.gridx = 13;
		gbc_wishlistButton.gridy = 8;
		panel.add(wishlistButton, gbc_wishlistButton);
		
		JButton deleteProductDBButton = new JButton("Delete Product from Database");
		cartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//deleteProductFromDatabase();
				System.out.println("DELETE PRODUCT FIRED");
			}
		});

		if(managementState) {
			GridBagConstraints gbc_deleteProductDBButton = new GridBagConstraints();
			gbc_deleteProductDBButton.insets = new Insets(0, 0, 5, 0);
			gbc_deleteProductDBButton.gridx = 1;
			gbc_deleteProductDBButton.gridy = 8;
			panel.add(deleteProductDBButton, gbc_deleteProductDBButton);
			
			
			JButton restockButton = new JButton("Restock Item");
			cartButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//deleteProductFromDatabase();
					System.out.println("RESTOCK FIRED");
				}
			});

			GridBagConstraints gbc_restockButton = new GridBagConstraints();
			gbc_restockButton.insets = new Insets(0, 0, 5, 0);
			gbc_restockButton.gridx = 2;
			gbc_restockButton.gridy = 8;
			panel.add(restockButton, gbc_restockButton);
			
			// add add product button
			JButton addProductButton = new JButton("Add Product");
			addProductButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					final JFrame frame = new JFrame("Add Product");
					JPanel panel = new JPanel(new GridLayout(0, 1));
					
					// create the labels
					JLabel nameLabel = new JLabel("Name: ");
					JLabel descriptionLabel = new JLabel("Description: ");
					JLabel priceLabel = new JLabel("Price: ");
					JLabel itemIDLabel = new JLabel("Item ID: ");
					JLabel quantityLabel = new JLabel("Quantity: ");
					
					// make the text fields
					JTextField nameField = new JTextField();
					JTextField descriptionField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField itemIDField = new JTextField();
					JTextField quantityField = new JTextField();
					
					// set text field size
					nameField.setSize(new Dimension(75, 30));
					descriptionField.setSize(new Dimension(75, 30));
					priceField.setSize(new Dimension(75, 30));
					itemIDField.setSize(new Dimension(75, 30));
					quantityField.setSize(new Dimension(75, 30));
					
					// set the labels and add them
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
					
					frame.add(panel);
					
					UIManager.put("OptionPane.cancelButtonText", "Canel");
					UIManager.put("OptionPane.okButtonText", "Save");
					
					int result = JOptionPane.showConfirmDialog(null, panel, "Edit", JOptionPane.OK_CANCEL_OPTION);
					
					if (result == JOptionPane.OK_OPTION) {
						String name = nameField.getText();
						String description = descriptionField.getText();
						Double price = Double.parseDouble(priceField.getText());
						String itemID = itemIDField.getText();
						Integer quantity = Integer.parseInt(quantityField.getText());
						
						ItemSpecification tempItemSpec = new ItemSpecification(name, description, price, itemID, quantity);
						
						// add the item spec
						List<ItemSpecification> tempItemList = ItemCatalog.getItems();
						tempItemList.add(tempItemSpec);
						ItemCatalog.setItems(tempItemList);
						
						nm.update();
						
						// write to the catalog file
						File file = new File("resources/testCatalog.csv");
						
						FileWriter fwriter;
						try {
							fwriter = new FileWriter(file, true);
							
							BufferedWriter writer = new BufferedWriter(fwriter);
							
							writer.write("\n");
							writer.write(name + "," + description + "," + price + "," + itemID + "," + quantity);
							
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					
					// show the frame
					frame.pack();
					frame.setVisible(true);
					
					System.out.println("ADD PRODUCT CLICKED");
				}
			});
			
			GridBagConstraints gbc_addProductButton = new GridBagConstraints();
			gbc_addProductButton.insets = new Insets(0, 0, 5, 0);
			gbc_addProductButton.gridx = 13;
			gbc_addProductButton.gridy = 9;
			panel.add(addProductButton, gbc_addProductButton);
		
		}

		Action addToCart = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JPanel form2 = new JPanel(new GridLayout(0,1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());

				Item toAdd = new Item((String)nm.getValueAt(modelRow, 3));
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
				if(res == JOptionPane.OK_OPTION) {
					Integer quantity = Integer.parseInt(qTextField.getText());
					
					//validate quantity
					if(quantity <= 0) { 
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity can't be less than or equal to 0!");
					}
					else if(quantity <= available) {
						nm.getCustomer().getCustomerCart().addItemToCart(toAdd, quantity);
					}
					else {
						JOptionPane.showMessageDialog(form2, "ERROR: Maximum for sale is " + available);
					}
				}		
			}
		};
		
		ButtonColumn addToCartButton = new ButtonColumn(table, addToCart, 2);
		
		Action addToWishlist = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JPanel form2 = new JPanel(new GridLayout(0,1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				
				JLabel quantityLbl = new JLabel("Quantity: ");
				JTextField qTextField = new JTextField();
				qTextField.setText("1");
				form2.add(quantityLbl);
				form2.add(qTextField);
				
				int res = JOptionPane.showConfirmDialog(null, form2, "Add Item To Wishlist", JOptionPane.OK_CANCEL_OPTION);
				if(res == JOptionPane.OK_OPTION) {
					Item toAdd = new Item((String)nm.getValueAt(modelRow, 3));
					Integer quantity = Integer.parseInt(qTextField.getText());
					Integer available = ItemCatalog.getItemSpecification(toAdd.getItemID()).getQuantity();
					
					//validate quantity
					if(quantity <= 0) { 
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity can't be less than or equal to 0!");
					}
					else if(quantity <= available) {
						nm.getCustomer().getWishlist().addItemToCart(toAdd, quantity);
					}
					else {
						JOptionPane.showMessageDialog(form2, "ERROR: Maximum for sale is " + available);
					}
				}
				
				System.out.println("ADD ITEM TO WISHLIST CLICKED");
			}
		};
		ButtonColumn addToWishlistButton = new ButtonColumn(table, addToWishlist, 3);
		
	}
	

	public void displayCart() {
		ShoppingCartView.createAndShowGUI(nm.getCustomer());
	}
	
	public void displayWishlist() {
		WishlistView.createAndShowGUI(nm.getCustomer());
	}
}
