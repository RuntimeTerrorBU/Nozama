package views;

import java.awt.EventQueue;
import java.io.*;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import nozamaFiles.Customer;
import nozamaFiles.ShoppingCart;

/**
 * The HomeView class creates the login screen that is displayed to the user once launching Nozama
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class HomeView {

	private JFrame frame;

	/**
	 * Launch the application.
	 * 
	 * @param String represeting the arguments passed
	 * @throws Exception if the HomeView cannot be opened
	 * @return void
	 */
	public static void main(String[] args) {

		// Starts instance of the home view main page
		EventQueue.invokeLater(new Runnable() {

			// To run
			public void run() {
				try {
					// General home view displayed by invoking instance of HomeView object
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
	 * 
	 * @throws Exception if the user data file cannot be parsed
	 * @throws IOException if the wishlist file cannot be created
	 */
	private void initialize() {

		// Initial frame set up
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Set home view name of Nozama application
		JLabel lblNewLabel = new JLabel("Nozama");
		lblNewLabel.setBounds(195, 6, 60, 16);
		frame.getContentPane().add(lblNewLabel);

		// Customer button for customer input
		JButton customerButton = new JButton("Customer");
		customerButton.setBounds(163, 130, 125, 29);
		customerButton.addMouseListener(new MouseAdapter() {
			@Override
			// If the customer login button is pressed
			public void mouseClicked(MouseEvent e) {

				// The frame and panel set for a login prompt
				JFrame loginFrame = new JFrame("Customer Login");
				JPanel loginForm = new JPanel(new GridLayout(0, 1));

				// Frame altered to be configured as a checkout screen
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				loginFrame.setPreferredSize(new Dimension(240, 150));
				loginFrame.pack();
				loginFrame.setLocationRelativeTo(null);

				// Create label/field for entering user name
				JLabel userLabel = new JLabel("Enter Username: ");
				JTextField userField = new JTextField();
				userField.setSize(new Dimension(75, 30));

				// Create label/field for entering password
				JLabel passLabel = new JLabel("Enter Password: ");
				// JTextField passField = new JTextField();
				JPasswordField passField = new JPasswordField();
				passField.setSize(new Dimension(75, 30));

				// Add label for user name to layout
				loginForm.add(userLabel, BorderLayout.WEST);
				userLabel.setLabelFor(userField);
				loginForm.add(userField);

				// Add label for password
				loginForm.add(passLabel, BorderLayout.WEST);
				passLabel.setLabelFor(passField);
				loginForm.add(passField);

				// Add the form containing the user name and password login
				loginFrame.add(loginForm);

				// Fire changes
				loginFrame.revalidate();
				loginForm.revalidate();

				// Set the buttons to be used for user name and password confirmation
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Login");

				// Set the result of the user chosen option button to track if user tries to
				// login
				int res = JOptionPane.showConfirmDialog(null, loginForm, "Customer Login",
						JOptionPane.OK_CANCEL_OPTION);

				// If the user input a user and password and pressed OK
				if (res == JOptionPane.OK_OPTION) {

					// Initialize the confirm option variables
					String username = userField.getText();
					// String password = passField.getText();
					String password = new String(passField.getPassword());
					Boolean loginComplete = false;

					try {

						// Initialize the files to parse current users from
						File file = new File("usersData.txt");
						FileInputStream fis = new FileInputStream(file);
						Scanner scanner = new Scanner(file);
						int r = 0;
						String inputLine;

						// While there are more people in the database to check vs the login info
						while (scanner.hasNextLine() && !loginComplete) {

							// Get next person
							inputLine = scanner.nextLine();

							// Divide the person data from the file to compare
							String[] checkPerson;
							checkPerson = inputLine.split(",");

							// If the login info is found and is valid, flag such true
							if (checkPerson[0].equals(username) && checkPerson[1].equals(password)) {
								loginComplete = true;
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// If a valid login was found while parsing
					if (loginComplete) {

						// Set a persons cart & wish list
						File f = new File("resources/carts/" + username + "Cart.csv");
						String wishlistName = "resources/wishlists/" + username + "Wishlist.csv";
						File myWishList = new File(wishlistName);

						try {
							// Create the actual wish list
							myWishList.createNewFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// Initialize instance of customer to be added to have a Nozama View displayed
						Customer c = new Customer(username, null, null, 0, new ShoppingCart(), null, false,
								new ShoppingCart(), f);

						// Set the Nozama view and the frame to be displayed (CUSTOMER)
						NozamaView.createAndShowGUI(false, c);
						frame.setVisible(false);
					}
					// If the login was invalid, display such
					else {
						// Initialize the frame and invalid login message
						JFrame invalidLogin = new JFrame("Login Failed");
						JLabel invalidUserOrPassword = new JLabel("   Invalid Username or Password: Try again!");

						// Frame altered to be configured as a checkout screen
						invalidLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						invalidLogin.setPreferredSize(new Dimension(280, 70));
						invalidLogin.pack();
						invalidLogin.setLocationRelativeTo(null);

						// Add the invalid login and set it to show
						invalidLogin.add(invalidUserOrPassword);
						invalidLogin.setVisible(true);
					}
				}
			}
		});

		// Add the actual instance of the customer button to the home view frame
		frame.getContentPane().add(customerButton);

		// Company button for company input
		JButton companyButton = new JButton("Company");
		companyButton.setBounds(163, 165, 125, 29);
		companyButton.addMouseListener(new MouseAdapter() {
			@Override

			// If the company login button is pressed
			public void mouseClicked(MouseEvent e) {

				// The frame and panel set for a login prompt
				JFrame loginFrame = new JFrame("Company Login");
				JPanel loginForm = new JPanel(new GridLayout(0, 1));

				// Frame altered to be configured as a checkout screen
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				loginFrame.setPreferredSize(new Dimension(240, 150));
				loginFrame.pack();
				loginFrame.setLocationRelativeTo(null);

				// Create label/field for entering user name
				JLabel userLabel = new JLabel("Enter Username: ");
				JTextField userField = new JTextField();
				userField.setSize(new Dimension(75, 30));

				// Create label/field for entering password
				JLabel passLabel = new JLabel("Enter Password: ");
				// JTextField passField = new JTextField();
				JPasswordField passField = new JPasswordField();
				passField.setSize(new Dimension(75, 30));

				// Add label for user name to layout
				loginForm.add(userLabel, BorderLayout.WEST);
				userLabel.setLabelFor(userField);
				loginForm.add(userField);

				// Add label for password
				loginForm.add(passLabel, BorderLayout.WEST);
				passLabel.setLabelFor(passField);
				loginForm.add(passField);

				// Add the form containing the user name and password login
				loginFrame.add(loginForm);

				// Fire changes
				loginFrame.revalidate();
				loginForm.revalidate();

				// Set the buttons to be used for user name and password confirmation
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Login");

				// Set the result of the user chosen option button to track if user tries to
				// login
				int res = JOptionPane.showConfirmDialog(null, loginForm, "Company Login", JOptionPane.OK_CANCEL_OPTION);

				// If the user input a user and password and pressed OK
				if (res == JOptionPane.OK_OPTION) {

					// Initialize the confirm option variables
					String username = userField.getText();
					// String password = passField.getText();
					String password = new String(passField.getPassword());
					Boolean loginComplete = false;

					try {
						// Initialize the files to parse current users from
						File file = new File("usersData.txt");
						FileInputStream fis = new FileInputStream(file);
						Scanner scanner = new Scanner(file);
						int r = 0;
						String inputLine;

						// While there are more companies in the database to check vs the login info
						while (scanner.hasNextLine() && !loginComplete) {

							// Get next person
							inputLine = scanner.nextLine();

							// Divide the person data from the file to compare
							String[] checkPerson;
							checkPerson = inputLine.split(",");

							// If the COMPANY login info is found and is valid, flag such true
							if (checkPerson[0].equals(username) && checkPerson[1].equals(password)
									&& checkPerson[2].equals("true")) {
								loginComplete = true;
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// If a valid COMPANY login was found while parsing
					if (loginComplete) {

						// Set a cart & wish list
						File f = new File("resources/carts/" + username + "Cart.csv");
						Customer c = new Customer(username, null, null, 0, new ShoppingCart(), null, true,
								new ShoppingCart(), f);

						// Set the Nozama view and the frame to be displayed (COMPANY)
						NozamaView.createAndShowGUI(true, c);
						frame.setVisible(false);
					}
					// If the login was invalid, display such
					else {
						// Initialize the frame and invalid login message
						JFrame invalidLogin = new JFrame("Login Failed");
						JLabel invalidUserOrPassword = new JLabel("   Invalid Username or Password: Try again!");

						// Frame altered to be configured as a checkout screen
						invalidLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						invalidLogin.setPreferredSize(new Dimension(280, 70));
						invalidLogin.pack();
						invalidLogin.setLocationRelativeTo(null);

						// Add the invalid login and set it to show
						invalidLogin.add(invalidUserOrPassword);
						invalidLogin.setVisible(true);
					}
				}
			}
		});

		// Add the actual instance of the company button to the home view frame
		frame.getContentPane().add(companyButton);

		// Create button to create a user if not already existed in system
		JButton newUserButton = new JButton("Create Login");
		newUserButton.setBounds(163, 200, 125, 29);
		newUserButton.addMouseListener(new MouseAdapter() {
			@Override

			// If the create login button is pressed
			public void mouseClicked(MouseEvent e) {

				// Create frame and panel to add create login components
				JFrame loginFrame = new JFrame("Create Login");
				JPanel loginForm = new JPanel(new GridLayout(0, 1));
				Boolean isCompany = null;

				// Frame altered to be configured as a checkout screen
				loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				loginFrame.setPreferredSize(new Dimension(240, 150));
				loginFrame.pack();
				loginFrame.setLocationRelativeTo(null);

				// Create Label/field for user name
				JLabel userLabel = new JLabel("Enter Username: ");
				JTextField userField = new JTextField();
				userField.setSize(new Dimension(75, 30));

				// Create Label/field for password
				JLabel passLabel = new JLabel("Enter Password: ");
				// JTextField passField = new JTextField();
				JPasswordField passField = new JPasswordField();
				passField.setSize(new Dimension(75, 30));

				// Create Label/field for re-enter password
				JLabel validateLabel = new JLabel("Re-Enter Password: ");
				// JTextField validateField = new JTextField();
				JPasswordField validateField = new JPasswordField();
				validateField.setSize(new Dimension(75, 30));

				// Edit layout for user name input prompt
				loginForm.add(userLabel, BorderLayout.WEST);
				userLabel.setLabelFor(userField);
				loginForm.add(userField);

				// Edit layout for password input prompt
				loginForm.add(passLabel, BorderLayout.WEST);
				passLabel.setLabelFor(passField);
				loginForm.add(passField);

				// Edit layout for password validate input prompt
				loginForm.add(validateLabel, BorderLayout.WEST);
				validateLabel.setLabelFor(validateField);
				loginForm.add(validateField);

				// Create check box option for customer
				JCheckBox customerCB = new JCheckBox("Customer");
				customerCB.setSelected(true);

				// Create check box option for company
				JCheckBox companyCB = new JCheckBox("Company");

				// Listener for Customer
				customerCB.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {

						// Based on the chosen state, set the account to that type of account
						if (e.getStateChange() == 1) {
							companyCB.setSelected(false);
						} else if (e.getStateChange() == 2) {
							companyCB.setSelected(true);
						}
					}

				});

				// Listener for Company
				companyCB.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {

						// Based on the chosen state, set the account to that type of account
						if (e.getStateChange() == 1) {
							customerCB.setSelected(false);
						} else if (e.getStateChange() == 2) {
							customerCB.setSelected(true);
						}
					}

				});

				// Add the customer and company checkboxs
				loginForm.add(customerCB);
				loginForm.add(companyCB);

				// Add the entire form to the frame
				loginFrame.add(loginForm);

				// Fire changes
				loginFrame.revalidate();
				loginForm.revalidate();

				// Set the buttons to be used for user name and password confirmation
				UIManager.put("OptionPane.cancelButtonText", "Cancel");
				UIManager.put("OptionPane.okButtonText", "Create Login");

				// Set the result of the user chosen option button to track if user tries to
				// login
				int res = JOptionPane.showConfirmDialog(null, loginForm, "Create New Login",
						JOptionPane.OK_CANCEL_OPTION);

				// If the OK Option is pressed
				if (res == JOptionPane.OK_OPTION) {

					// Set company state to open valid Nozama view
					if (customerCB.isSelected()) {
						isCompany = false;
					} else {
						isCompany = true;
					}

					String pass = new String(passField.getPassword());
					String validation = new String(validateField.getPassword());

					// If both passwords matched correctly, set login to finished
					if (pass.equals(validation)) {

						// Create string to be added to valid list of logins
						String str = userField.getText() + "," + passField.getText() + ",";

						// Set comapny state string to add
						if (isCompany) {
							str += "true";
						} else {
							str += "false";
						}
						str += '\n';

						try {

							// Initialize file variables & parse variables
							File file = new File("usersData.txt");
							BufferedReader inputData = new BufferedReader(new FileReader(file));
							FileOutputStream outputData = new FileOutputStream(file, true);
							String line;
							String userName = userField.getText();
							String reason = "";
							boolean isValid = true;

							if (userField.getText().equals("")) {
								isValid = false;
								reason = "Username field is empty";
							} else if (pass.equals("")) {
								isValid = false;
								reason = "Password field is empty";
							}

							// Make sure new login doesn't already exist
							while (((line = inputData.readLine()) != null) && isValid) {
								String[] split = line.split(",");
								if (split[0].equals(userName)) {
									isValid = false;
									reason = "Username is taken";
								}
							}

							// Close file after parse
							inputData.close();

							if (isValid) {
								// Write the login info to the file
								outputData.write(str.getBytes());

								// Set cart
								String cartName = "resources/carts/" + userName + "Cart.csv";
								File myCart = new File(cartName);
								myCart.createNewFile();

								// Set wish list
								String wishlistName = "resources/wishlists/" + userName + "Wishlist.csv";
								File myWishList = new File(wishlistName);
								myWishList.createNewFile();

							} else {
								UIManager.put("OptionPane.okButtonText", "Ok");
								JOptionPane.showMessageDialog(loginFrame, reason);
							}

							// Close the same file with the saved login
							outputData.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						UIManager.put("OptionPane.okButtonText", "Ok");
						JOptionPane.showMessageDialog(loginFrame, "Passwords don't match");
					}
				}
			}
		});

		// Add the actual instance of the create login button to the frame
		frame.getContentPane().add(newUserButton);

		// Add Text Login to the frame for readability
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(163, 66, 125, 29);
		frame.getContentPane().add(lblNewLabel_1);

		// Add Text Form to the frame for readability
		JLabel lblNewLabel_2 = new JLabel("Form");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(195, 90, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
