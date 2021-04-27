package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controllers.ButtonColumn;
import nozamaFiles.Customer;
import nozamaFiles.ItemCatalog;
import controllers.WishlistController;

public class WishlistView extends JPanel {

	// Instance of the Wish list Controller to be used
	private static WishlistController wc = new WishlistController();

	public WishlistView(Customer c) {
		try {
			// Set up files based on the set catalog
			File catalogFile = new File("resources/testCatalog.csv");
			ItemCatalog.loadData(catalogFile);
			wc.setCustomer(c);
			File w = new File("resources/wishlists/" + c.getUsername() + "Wishlist.csv");
			wc.setCustomerFile(w);
			BufferedWriter out = new BufferedWriter(new FileWriter(wc.getCustomerFile()));

			// Write the cart to the out
			out.write(wc.getCart().toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Creation of Table to add interface instance to
		final JTable table = new JTable(wc);
		table.removeColumn(table.getColumnModel().getColumn(4));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		// Action to perform edit on quantity of item
		Action edit = new AbstractAction() {
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
						((WishlistController) table.getModel()).removeRow(modelRow);

					}
					// ERROR if negative quantity
					else if (res < 0) {
						JOptionPane.showMessageDialog(form2, "ERROR: You cannot enter a negative quantity!");

					}
					// Change quantity if valid quantity & write to the customer file
					else if (res <= ItemCatalog.getItemSpecification(wc.getValueAt(modelRow, 4).toString())
							.getQuantity()) {
						wc.setValueAt(res, modelRow, 2);

						try {
							// Initialize the Buffered Reader Output
							BufferedWriter out = new BufferedWriter(new FileWriter(wc.getCustomerFile()));

							// Write the cart to the out
							out.write(wc.getCart().toString());
							out.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} else {
						// Otherwise quantity is too large for order, ERROR
						JOptionPane.showMessageDialog(form2, "ERROR: Quantity entered exceeded maximum availiable!");
					}

					// Fire changes (safety)
					revalidate();
				}
			}
		};

		// Column creation of edit button column (for each item)
		ButtonColumn editButton = new ButtonColumn(table, edit, 3);

		// Fire changes
		revalidate();
	}

	public static void createAndShowGUI(Customer c) {

		// Implementation of the Shopping cart interface after checkout
		// Initializing the frame for wish list
		JFrame frame = new JFrame("Your Wishlist");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Initialize the wish list view (new)
		WishlistView newContentPane = new WishlistView(c);
		newContentPane.setOpaque(true);

		// Set frame settings for display
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
