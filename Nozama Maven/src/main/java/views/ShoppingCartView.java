package views;

import nozamaFiles.*;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.*;
import nozamaFiles.*;

public class ShoppingCartView extends JPanel {

	public ShoppingCartView() {
		// FOR TESTING ONLY
		File myFile = new File("resources/testCatalog.csv");
		try {
			ItemCatalog.loadData(myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShoppingCart cart = new ShoppingCart();

		cart.addItemToCart(new Item("1"), 1);
		cart.addItemToCart(new Item("2"), 2);
		cart.addItemToCart(new Item("3"), 3);
		cart.addItemToCart(new Item("4"), 4);
		cart.addItemToCart(new Item("5"), 5);

		ShoppingCartModel scm = new ShoppingCartModel(cart);

		final JTable table = new JTable(scm);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

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
