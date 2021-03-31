package views;

import models.*;
import nozamaFiles.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class ShoppingCartView extends JPanel implements ActionListener {
	private ShoppingCartModel scm = new ShoppingCartModel();

	public ShoppingCartView() {
		// FOR TESTING ONLY

		try {
			File catalogFile = new File("resources/testCatalog.csv");
			ItemCatalog.loadData(catalogFile);
			File cartFile = new File("resources/testCart.csv");
			scm.loadData(cartFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final JTable table = new JTable(scm);
		table.removeColumn(table.getColumnModel().getColumn(4));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
		JLabel subtotalLbl = new JLabel();
		subtotalLbl.setText("Subtotal: $" + sub.toPlainString());

		Action edit = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JPanel form2 = new JPanel(new GridLayout(0, 1));
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());

				JTextField numField = new JTextField(table.getModel().getValueAt(modelRow, 2).toString());
				numField.setSize(new Dimension(75, 30));
				JLabel numLabel = new JLabel("Enter Quantity: ");
				form2.add(numLabel);
				numLabel.setLabelFor(numField);
				form2.add(numField);

				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Save");
				int result = JOptionPane.showConfirmDialog(null, form2, "Edit Quantity", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					int res = Integer.parseInt(numField.getText());
					if (res == 0) {
						((ShoppingCartModel) table.getModel()).removeRow(modelRow);

					} 
					else if(res < 0) {
						JOptionPane.showMessageDialog(form2, "Error: You cannot enter a negative quantity!");
						
					}
					else if (res <= ItemCatalog.getItemSpecification(scm.getValueAt(modelRow, 4).toString())
							.getQuantity()) {
						scm.setValueAt(res, modelRow, 2);
					}
					else {
						JOptionPane.showMessageDialog(form2, "Error: Quantity entered exceeded maximum availiable!");
						
					}
					

					BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
					subtotalLbl.setText("Subtotal: $" + sub.toPlainString());
				}
			}
		};
		ButtonColumn editButton = new ButtonColumn(table, edit, 3);

		add(subtotalLbl, BorderLayout.SOUTH);

		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(this);
		add(checkoutButton);
		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) (e.getSource());

		if (source.getText().equals("Checkout")) {
			System.out.println("Please add a checkout page :)");
		}

	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Your Cart");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ShoppingCartView newContentPane = new ShoppingCartView();

		newContentPane.setOpaque(true);

		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) throws FileNotFoundException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
