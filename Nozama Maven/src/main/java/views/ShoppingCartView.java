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

public class ShoppingCartView extends JPanel implements ActionListener {
	
	//Instance of the ShoppingCart Model to be used
	private static ShoppingCartController scm = new ShoppingCartController();

	public ShoppingCartView(Customer c) {
		// FIXME This try/catch is specifically for testing only
		try {
			File catalogFile = new File("resources/testCatalog.csv");
			ItemCatalog.loadData(catalogFile);
			//scm.setCart(sc);
			scm.setCustomer(c);
			scm.setCustomerFile(new File("resources/testCart.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Creation of Table to add initial interface instance to
		final JTable table = new JTable(scm);
		table.removeColumn(table.getColumnModel().getColumn(4));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		//Initialization of labels used
		JLabel subtotalLbl = new JLabel();
		
		//Fetch the sub total of the current cart, used to display
		BigDecimal sub = new BigDecimal(c.getCustomerCart().calculateSub()).setScale(2, RoundingMode.HALF_UP);
		subtotalLbl.setText("Subtotal: $" + sub.toPlainString());

		//Action to perform edit on quantity of item
		Action edit = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				//Creation of edit prompt panel
				JPanel form2 = new JPanel(new GridLayout(0, 1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());

				//Text label to show user prompt and input field
				JTextField numField = new JTextField(table.getModel().getValueAt(modelRow, 2).toString());
				numField.setSize(new Dimension(75, 30));
				JLabel numLabel = new JLabel("Enter Quantity: ");
				form2.add(numLabel);
				numLabel.setLabelFor(numField);
				form2.add(numField);

				//Change the initial UI to reflect options for Save/Cancel
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Save");
				
				//Set result based on input option
				int result = JOptionPane.showConfirmDialog(null, form2, "Edit Quantity", JOptionPane.OK_CANCEL_OPTION);

				//If the option is to Save
				if (result == JOptionPane.OK_OPTION) {
					
					//If quantity changed to 0, remove item
					int res = Integer.parseInt(numField.getText());
					if (res == 0) {
						((ShoppingCartController) table.getModel()).removeRow(modelRow);

					} 
					//ERROR if negative quantity
					else if(res < 0) {
						JOptionPane.showMessageDialog(form2, "ERROR: You cannot enter a negative quantity!");
						
					}
					//Change quantity if valid quantity & write to the customer file
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
					//Otherwise quantity is too large for order, ERROR
					else {
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity entered exceeded maximum availiable!");
					}
					
					//Edit sub total to reflect changes
					BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
					subtotalLbl.setText("Subtotal: $" + sub.toPlainString());
					
					//Fire changes (safety)
					revalidate();
				}
			}
		};
		
		//Creation of edit button column (for each item)
		ButtonColumn editButton = new ButtonColumn(table, edit, 3);

		//Add the sub total, or updated sub total interface display
		add(subtotalLbl, BorderLayout.SOUTH);

		//Creation of checkout button
		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(this);
		add(checkoutButton);
		
		//Fire changes
		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) (e.getSource());

		//If the action fired is the checkout confirmation
		if (source.getText().equals("Checkout")) {
			//Base of Checkout Confirmation
			
			//Frame and label creation
			JFrame frameCheckout = new JFrame("Checkout Confirmation");
			JPanel checkoutForm = new JPanel(new GridLayout(0, 1));
			JLabel subtotalLbl = new JLabel();
			
			//Frame altered to be configured as a checkout screen
			frameCheckout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameCheckout.setPreferredSize(new Dimension(240, 150));
			frameCheckout.pack();
			frameCheckout.setLocationRelativeTo(null);
			frameCheckout.setVisible(true);
			
			//Fire changes
			revalidate();
			
			//Find the sub total based on the Shopping Cart Model		
			BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
			subtotalLbl.setText('\t' + "Subtotal: $" + sub.toPlainString());
			//JPanel checkoutForm = new JPanel(new GridLayout(0, 1));
			//add(checkoutForm);
			//setVisible(checkoutForm);	
			
			
			//Add the sub total count to the checkout prompt
			subtotalLbl.setHorizontalAlignment(JLabel.CENTER);
			frameCheckout.add(subtotalLbl, BorderLayout.NORTH);
			
			
			//Create Labels and Text fields for entering card info and cvc numbers
			JLabel cardLabel = new JLabel("Enter Card Number: ");
			JTextField cardField = new JTextField();
			cardField.setSize(new Dimension(75, 30));
			JLabel cvcLabel = new JLabel("Enter CVC Number: ");
			JTextField cvcField = new JTextField();
			cvcField.setSize(new Dimension(75, 30));
			
			//Edit layout for card input prompt
			checkoutForm.add(cardLabel, BorderLayout.WEST);
			cardLabel.setLabelFor(cardField);
			checkoutForm.add(cardField);
			
			//Edit layout for cvc input prompt
			checkoutForm.add(cvcLabel, BorderLayout.WEST);
			cvcLabel.setLabelFor(cvcField);
			checkoutForm.add(cvcField);
			
			//Add the entire form to the frame
			frameCheckout.add(checkoutForm);
			
			//Fire changes
			revalidate();
			
			//Validation check in command line
			System.out.println("CHECKOUT CLICKED");
			
		}

	}

	public static void createAndShowGUI(Customer c) {
		//Implementation of the Shopping cart interface after checkout
		JFrame frame = new JFrame("Your Cart");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		ShoppingCartView newContentPane = new ShoppingCartView(c);

		newContentPane.setOpaque(true);

		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
		
	}
}
