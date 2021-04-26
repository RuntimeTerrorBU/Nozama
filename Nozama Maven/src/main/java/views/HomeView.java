package views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

public class HomeView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView window = new HomeView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nozama");
		lblNewLabel.setBounds(195, 6, 60, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton customerButton = new JButton("Customer");
		customerButton.setBounds(163, 130, 125, 29);
		customerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO ADD LOGIN SCREEN BEFORE THIS
				System.out.println("ADD LOGIN SCREEN FOR CUSTOMER");
				NozamaView.createAndShowGUI();
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(customerButton);
		
		JButton companyButton = new JButton("Company");
		companyButton.setBounds(163, 165, 125, 29);
		
		companyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO ADD LOGIN SCREEN BEFORE THIS
				System.out.println("ADD LOGIN SCREEN FOR COMPANY");
				NozamaView.createAndShowGUI();
				frame.setVisible(false);
			}
		});
		
		frame.getContentPane().add(companyButton);
		
		JButton newUserButton = new JButton("Create Login");
		newUserButton.setBounds(163, 200, 125, 29);
		newUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO ADD LOGIN SCREEN BEFORE THIS
				System.out.println("ADD NEW USER");
				NozamaView.createAndShowGUI();
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(newUserButton);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(163, 66, 125, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Form");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(195, 90, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
}
