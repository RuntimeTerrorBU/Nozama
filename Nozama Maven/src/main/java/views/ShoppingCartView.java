package views;

import nozamaFiles.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

import controllers.*;

/**
 * The ShoppingCartView class creates the main store page of Nozama
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class ShoppingCartView extends JPanel implements ActionListener {

	// Instance of the ShoppingCart Model to be used
	private static ShoppingCartController scm = new ShoppingCartController();

	/**
	 * Launch the shopping cart page for the customer
	 * 
	 * @param c Customer to open the shopping cart page for
	 * @throw IOException if the item catalog and/or cart information files cannot be opened successfully
	 */
	public ShoppingCartView(Customer c) {

		// FIXME This try/catch is specifically for testing only
		try {
			// Initialize the files
			File catalogFile = new File("resources/testCatalog.csv");
			ItemCatalog.loadData(catalogFile);
			scm.setCustomer(c);
			scm.setCustomerFile(new File("resources/carts/" + c.getUsername() + "Cart.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Creation of Table to add initial interface instance to
		final JTable table = new JTable(scm);
		table.removeColumn(table.getColumnModel().getColumn(4));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		// Initialization of labels used
		JLabel subtotalLbl = new JLabel();

		// Fetch the sub total of the current cart, used to display
		BigDecimal sub = new BigDecimal(c.getCustomerCart().calculateSub()).setScale(2, RoundingMode.HALF_UP);
		subtotalLbl.setText("Subtotal: $" + sub.toPlainString());

		// Action to perform edit on quantity of item
		Action edit = new AbstractAction() {
			
			/**
			 * Action Perfomed once the user tries to edit the quantity of of the item
			 * 
			 * @param Customer to open the wishlist page for
			 * @throw IOException the customer file cannot be written to
			 */
			public void actionPerformed(ActionEvent e) {

				// Creation of edit prompt panel
				JPanel form2 = new JPanel(new GridLayout(0, 1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());

				// Text label to show user prompt and input field
				JTextField numField = new JTextField(table.getModel().getValueAt(modelRow, 2).toString());
				numField.setSize(new Dimension(75, 30));
				JLabel numLabel = new JLabel("Enter Quantity: ");
				form2.add(numLabel);
				numLabel.setLabelFor(numField);
				form2.add(numField);

				// Change the initial UI to reflect options for Save/Cancel
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Save");

				// Set result based on input option
				int result = JOptionPane.showConfirmDialog(null, form2, "Edit Quantity", JOptionPane.OK_CANCEL_OPTION);

				// If the option is to Save
				if (result == JOptionPane.OK_OPTION) {

					// If quantity changed to 0, remove item
					int res = Integer.parseInt(numField.getText());
					if (res == 0) {
						((ShoppingCartController) table.getModel()).removeRow(modelRow);

					}
					// ERROR if negative quantity
					else if (res < 0) {
						JOptionPane.showMessageDialog(form2, "ERROR: You cannot enter a negative quantity!");

					}
					// Change quantity if valid quantity & write to the customer file
					else if (res <= ItemCatalog.getItemSpecification(scm.getValueAt(modelRow, 4).toString())
							.getQuantity()) {
						scm.setValueAt(res, modelRow, 2);

						try {
							BufferedWriter out = new BufferedWriter(new FileWriter(scm.getCustomerFile()));
							out.write(scm.getCart().toString());
							out.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					// Otherwise quantity is too large for order, ERROR
					else {
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity entered exceeded maximum availiable!");
					}

					// Edit sub total to reflect changes
					BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
					subtotalLbl.setText("Subtotal: $" + sub.toPlainString());

					// Fire changes (safety)
					revalidate();
				}
			}
		};

		// Creation of edit button column (for each item)
		ButtonColumn editButton = new ButtonColumn(table, edit, 3);

		// Add the sub total, or updated sub total interface display
		add(subtotalLbl, BorderLayout.SOUTH);

		// Creation of checkout button
		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(this);
		add(checkoutButton);

		// Fire changes
		revalidate();
	}

	/**
	 * Create the checkout actions once the Checkout button is clicked
	 * 
	 * @param e ActionEvent of the button being pressed
	 * @throws IOException if the Customer's order file and / or the general report file cannot be written to
	 */
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) (e.getSource());

		// If the action fired is the checkout confirmation
		if (source.getText().equals("Checkout")) {
			// Base of Checkout Confirmation

			// Frame and label creation
			JPanel checkoutForm = new JPanel(new GridLayout(0, 1));
			JLabel subtotalLbl = new JLabel();

			// Fire changes
			revalidate();

			// Find the sub total based on the Shopping Cart Model
			BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
			subtotalLbl.setText('\t' + "Subtotal: $" + sub.toPlainString());
			subtotalLbl.setVisible(true);

			// Create Labels / text field for address
			JLabel addyLabel = new JLabel("Enter Address: ");
			JTextField addyField = new JTextField();
			addyField.setSize(new Dimension(75, 30));

			// Create Labels / text field for card
			JLabel cardLabel = new JLabel("Enter Card Number: ");
			JTextField cardField = new JTextField();
			cardField.setSize(new Dimension(75, 30));

			// Create Labels / text field for cvc
			JLabel cvcLabel = new JLabel("Enter CVC Number: ");
			JTextField cvcField = new JTextField();
			cvcField.setSize(new Dimension(75, 30));

			// Add the sub total count to the checkout prompt
			subtotalLbl.setHorizontalAlignment(JLabel.CENTER);
			checkoutForm.add(subtotalLbl, BorderLayout.NORTH);

			// Edit layout for address prompt
			checkoutForm.add(addyLabel, BorderLayout.WEST);
			addyLabel.setLabelFor(addyField);
			checkoutForm.add(addyField);

			// Edit layout for card input prompt
			checkoutForm.add(cardLabel, BorderLayout.WEST);
			cardLabel.setLabelFor(cardField);
			checkoutForm.add(cardField);

			// Edit layout for cvc input prompt
			checkoutForm.add(cvcLabel, BorderLayout.WEST);
			cvcLabel.setLabelFor(cvcField);
			checkoutForm.add(cvcField);

			// Fire changes
			revalidate();

			// Initialize button text
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			UIManager.put("OptionPane.okButtonText", "Checkout");

			// Get the result of the button press to check if continue
			int res = JOptionPane.showConfirmDialog(null, checkoutForm, "Checkout", JOptionPane.OK_CANCEL_OPTION);

			// If OK pressed, continue
			if (res == JOptionPane.OK_OPTION) {

				// Initialize the JPanel for checkout
				JPanel frameCheckout = new JPanel();

				// Initialize the receipt labels
				JLabel thankyou = new JLabel("Thank you for your purchase!");
				JLabel delimeter = new JLabel("===========================");
				JLabel header = new JLabel("Shopping Purchase Details: ");
				JLabel totalPay = new JLabel("Total Purchase Cost: " + sub.toPlainString());
				JLabel sentAddress = new JLabel("Address: " + addyField.getText());
				JLabel cardUsed = new JLabel("Card: " + cardField.getText());

				// Edit the Frame settings
				frameCheckout.setPreferredSize(new Dimension(240, 150));
				frameCheckout.setVisible(true);

				// Add the labels to the checkout frame
				frameCheckout.add(thankyou, BorderLayout.WEST);
				frameCheckout.add(delimeter, BorderLayout.WEST);
				frameCheckout.add(header, BorderLayout.WEST);
				frameCheckout.add(totalPay, BorderLayout.WEST);
				frameCheckout.add(sentAddress, BorderLayout.WEST);
				frameCheckout.add(cardUsed, BorderLayout.WEST);

				// Fire changes
				frameCheckout.revalidate();

				// record purchase & initialize files
				File file = new File("resources/orders/" + scm.getCustomer().getUsername() + "Orders.csv");
				File reportFile = new File("resources/orders/GeneratedReport.csv");
				FileWriter fwriter;
				FileWriter reportWriter;

				try {
					// Set readers if valid
					fwriter = new FileWriter(file, true);
					reportWriter = new FileWriter(reportFile, true);

					// Initialize buffered readers for file writing
					BufferedWriter writer = new BufferedWriter(fwriter);
					BufferedWriter rWriter = new BufferedWriter(reportWriter);
					writer.write("\n");

					// Send the output to the output files for generation
					for (Pair<Item, Integer> p : scm.getCustomer().getCustomerCart().getCart()) {
						writer.write(p.first.getItemID() + "," + p.second + "\n");
						rWriter.write(p.first.getItemID() + "," + p.second + "\n");
					}

					// Close the buffers
					writer.close();
					rWriter.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// Initialize J-Style Objects for shipping confirmation
				JFrame cFrame = new JFrame("Shipping confirmation");
				JPanel cPanel = new JPanel(new GridLayout(0, 1));
				JLabel title = new JLabel("Shipping detals:");
				JTextField itemsField = new JTextField();
				String itemsString = "";

				// Add the title and address to the panel
				cPanel.add(title);
				cPanel.add(sentAddress);

				// Add the individual item fields
				for (Pair<Item, Integer> p : scm.getCustomer().getCustomerCart().getCart()) {
					itemsField = new JTextField();
					itemsString = "Item: " + ItemCatalog.getItemSpecification(p.first.getItemID()).getName()
							+ " Quantity: " + p.second + "\n";
					itemsField.setText(itemsString);
					itemsField.setEditable(false);
					cPanel.add(itemsField);
				}

				// Set frame settings and display
				cFrame.getContentPane().add(cPanel);
				cFrame.pack();
				cFrame.setVisible(true);

				// Set the button names for receipt prompting
				UIManager.put("OptionPane.okButtonText", "Receipt");
				UIManager.put("OptionPane.cancelButtonText", "No Receipt");

				// Determine if receipt is wanted
				int resRec = JOptionPane.showConfirmDialog(null, frameCheckout, "Purchase Confirmation!",
						JOptionPane.OK_CANCEL_OPTION);

				// If receipt needs to be generated
				if (resRec == JOptionPane.OK_OPTION) {

					// Initialize receipt generation variables
					JPanel exportPrompt = new JPanel();
					int selectedOpt;

					// File J-Objects
					JButton fileButton = new JButton("Choose File");
					final JFileChooser fileChosen = new JFileChooser();
					int optChosen = fileChosen.showSaveDialog(null);

					// Chunk to write to file and export
					if (optChosen == JFileChooser.APPROVE_OPTION) {
						try {

							// Initialize files
							File expFile = fileChosen.getSelectedFile();
							FileWriter toWrite = new FileWriter(expFile);

							// Call Model function to return table that is formatted for export
							toWrite.write("Thank you for your purchase!\n===========================\nPurchase Details:\n"
											+ "Total: " + sub.toPlainString() + "\nAddress: " + addyField.getText()
											+ "\nCard: " + cardField.getText());
							toWrite.close();
						} catch (IOException x) {
							x.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * Create and display the ShoppingCart page
	 * 
	 * @param c Customer to open the shopping cart page for
	 */
	public static void createAndShowGUI(Customer c) {

		// Implementation of the Shopping cart interface after checkout
		// Initialize the frame and set initial setting
		JFrame frame = new JFrame("Your Cart");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Initialize the initial shopping char view
		ShoppingCartView newContentPane = new ShoppingCartView(c);
		newContentPane.setOpaque(true);

		// Set frame settings
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
