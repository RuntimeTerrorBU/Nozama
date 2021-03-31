package views;

import nozamaFiles.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.*;
import nozamaFiles.*;

public class ShoppingCartView extends JPanel {
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
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		JPanel subtotalForm = new JPanel();

		BigDecimal sub = new BigDecimal(scm.getSubtotal()).setScale(2, RoundingMode.HALF_UP);
		JLabel subtotalLbl = new JLabel("Subtotal: $" + sub.toPlainString());
		subtotalForm.add(subtotalLbl);
		add(subtotalForm, BorderLayout.SOUTH);
		
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
