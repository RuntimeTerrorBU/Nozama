package views;
import java.awt.EventQueue;
import java.io.*;  
import java.util.Scanner;  

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
				
				JFrame loginFrame = new JFrame("Customer Login");
				JPanel loginForm = new JPanel(new GridLayout(0, 1));
				//loginFrame.setVisible(true);
				//loginForm.setVisible(true);
				
				//Frame altered to be configured as a checkout screen
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				loginFrame.setPreferredSize(new Dimension(240, 150));
				loginFrame.pack();
				loginFrame.setLocationRelativeTo(null);
				
				//Create Labels and Text fields for entering card info and cvc numbers
				JLabel userLabel = new JLabel("Enter Username: ");
				JTextField userField = new JTextField();
				userField.setSize(new Dimension(75, 30));
				JLabel passLabel = new JLabel("Enter Password: ");
				JTextField passField = new JTextField();
				passField.setSize(new Dimension(75, 30));
				
				//Edit layout for card input prompt
				loginForm.add(userLabel, BorderLayout.WEST);
				userLabel.setLabelFor(userField);
				loginForm.add(userField);
				
				//Edit layout for cvc input prompt
				loginForm.add(passLabel, BorderLayout.WEST);
				passLabel.setLabelFor(passField);
				loginForm.add(passField);
				
				//Add the entire form to the frame
				loginFrame.add(loginForm);
				
				//Fire changes
				loginFrame.revalidate();
				loginForm.revalidate();
				
				int res = JOptionPane.showConfirmDialog(null, loginForm, "Customer Login", JOptionPane.OK_CANCEL_OPTION);
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Login");
				
				if(res == JOptionPane.OK_OPTION) {
					String username = userField.getText();
					String password = passField.getText();
					Boolean loginComplete = false;
					
					try {
						File file = new File("usersData.txt");   
						FileInputStream fis = new FileInputStream(file);
						Scanner scanner = new Scanner(file);

						int r = 0;
						String inputLine;
						
						while(scanner.hasNextLine() && !loginComplete) {
							
							inputLine = scanner.nextLine();
							
							String[] checkPerson;
							checkPerson = inputLine.split(",");
							
							if(checkPerson[0].equals(username) && checkPerson[1].equals(password)) {
								loginComplete = true;
							}
						}
					}
					catch(Exception ex){  
						ex.printStackTrace();
					}
					
					if(loginComplete) {
						NozamaView.createAndShowGUI(false);
						frame.setVisible(false);
					}
					else {
						System.out.println("DISPLAY ERROR FOR INVALID LOGIN");
					}
				}
				
				//Validation check in command line
				System.out.println("Login CLICKED");
			}
		});
		frame.getContentPane().add(customerButton);
		
		JButton companyButton = new JButton("Company");
		companyButton.setBounds(163, 165, 125, 29);
		
		companyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame loginFrame = new JFrame("Customer Login");
				JPanel loginForm = new JPanel(new GridLayout(0, 1));
				//loginFrame.setVisible(true);
				//loginForm.setVisible(true);
				
				//Frame altered to be configured as a checkout screen
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				loginFrame.setPreferredSize(new Dimension(240, 150));
				loginFrame.pack();
				loginFrame.setLocationRelativeTo(null);
				
				//Create Labels and Text fields for entering card info and cvc numbers
				JLabel userLabel = new JLabel("Enter Username: ");
				JTextField userField = new JTextField();
				userField.setSize(new Dimension(75, 30));
				JLabel passLabel = new JLabel("Enter Password: ");
				JTextField passField = new JTextField();
				passField.setSize(new Dimension(75, 30));
				
				//Edit layout for card input prompt
				loginForm.add(userLabel, BorderLayout.WEST);
				userLabel.setLabelFor(userField);
				loginForm.add(userField);
				
				//Edit layout for cvc input prompt
				loginForm.add(passLabel, BorderLayout.WEST);
				passLabel.setLabelFor(passField);
				loginForm.add(passField);
				
				//Add the entire form to the frame
				loginFrame.add(loginForm);
				
				//Fire changes
				loginFrame.revalidate();
				loginForm.revalidate();
				
				int res = JOptionPane.showConfirmDialog(null, loginForm, "Customer Login", JOptionPane.OK_CANCEL_OPTION);
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Login");
				
				if(res == JOptionPane.OK_OPTION) {
					String username = userField.getText();
					String password = passField.getText();
					Boolean loginComplete = false;
					
					try {
						File file = new File("usersData.txt");   
						FileInputStream fis = new FileInputStream(file);
						Scanner scanner = new Scanner(file);

						int r = 0;
						String inputLine;
						
						while(scanner.hasNextLine() && !loginComplete) {
							
							inputLine = scanner.nextLine();
							
							String[] checkPerson;
							checkPerson = inputLine.split(",");
							
							if(checkPerson[0].equals(username) && checkPerson[1].equals(password) && checkPerson[2].equals("true")) {
								loginComplete = true;
							}
						}
					}
					catch(Exception ex){  
						ex.printStackTrace();
					}
					
					if(loginComplete) {
						NozamaView.createAndShowGUI(true);
						frame.setVisible(false);
					}
					else {
						System.out.println("DISPLAY ERROR FOR INVALID LOGIN");
					}
				}
			
				//Validation check in command line
				System.out.println("Login CLICKED");
			}
		});
		
		frame.getContentPane().add(companyButton);
		
		JButton newUserButton = new JButton("Create Login");
		newUserButton.setBounds(163, 200, 125, 29);
		newUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO ADD LOGIN SCREEN BEFORE THIS
				JFrame loginFrame = new JFrame("Create Login");
				JPanel loginForm = new JPanel(new GridLayout(0, 1));
				boolean isCompany = false;
				//loginFrame.setVisible(true);
				//loginForm.setVisible(true);
				
				//Frame altered to be configured as a checkout screen
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				loginFrame.setPreferredSize(new Dimension(240, 150));
				loginFrame.pack();
				loginFrame.setLocationRelativeTo(null);
				
				//Create Labels and Text fields for entering card info and cvc numbers
				JLabel userLabel = new JLabel("Enter Username: ");
				JTextField userField = new JTextField();
				userField.setSize(new Dimension(75, 30));
				JLabel passLabel = new JLabel("Enter Password: ");
				JTextField passField = new JTextField();
				passField.setSize(new Dimension(75, 30));
				
				//Edit layout for card input prompt
				loginForm.add(userLabel, BorderLayout.WEST);
				userLabel.setLabelFor(userField);
				loginForm.add(userField);
				
				//Edit layout for cvc input prompt
				loginForm.add(passLabel, BorderLayout.WEST);
				passLabel.setLabelFor(passField);
				loginForm.add(passField);
				
				
				JCheckBox customerCB = new JCheckBox("Customer");
				customerCB.setSelected(true);
				
				JCheckBox companyCB = new JCheckBox("Company");
				customerCB.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == 1) {
							companyCB.setSelected(false);
						}
					}
					
				});
				companyCB.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == 1) {
							customerCB.setSelected(false);
						}
					}
					
				});
				loginForm.add(customerCB);
				loginForm.add(companyCB);
				
				//Add the entire form to the frame
				loginFrame.add(loginForm);
				
				//Fire changes
				loginFrame.revalidate();
				loginForm.revalidate();
				
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Create Login");
				int res = JOptionPane.showConfirmDialog(null, loginForm, "Create New Login", JOptionPane.OK_CANCEL_OPTION);
				
				
				
				System.out.println("ADD NEW USER");
				NozamaView.createAndShowGUI(false);
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
