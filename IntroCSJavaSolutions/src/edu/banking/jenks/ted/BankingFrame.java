package edu.banking.jenks.ted;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.regex.*;

import javax.swing.*;
import javax.swing.border.*;

import edu.jenks.dist.banking.*;
import edu.jenks.util.*;

public class BankingFrame extends JFrame {

	private static final long serialVersionUID = -7289755259408225266L;
	
	private static final Pattern AMOUNT_PATTERN = Pattern.compile("\\d*(\\.\\d{0,2})?");
	private static final double DOLLAR_DELTA = 0.001;
	private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
	
	private Customer customer = new CustomerGen("Mr. Jenks");
	private JPanel contentPane;
	private JTextField amountTextField;
	private JTextArea messageTextArea;
	private JButton withdrawButton;
	private JButton depositButton;
	private JButton balanceButton;
	private JButton logoffButton;
	
	// depends on customer state
	private JLabel loginLabel;
	private JTextField nameTextField;
	private JTextField pinTextField;
	private JPanel amountPanel;
	private JPanel functionPanel;
	private JButton logonButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankingFrame frame = new BankingFrame();
					frame.customer.addCheckingAccount(new CheckingAccountGen(100));
					frame.initState();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		contentPane.add(loginPanel, BorderLayout.NORTH);
		
		loginLabel = new JLabel();
		loginPanel.add(loginLabel);
		
		nameTextField = new JTextField();
		nameTextField.setEnabled(true);
		loginPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		pinTextField = new JTextField();
		pinTextField.setEditable(true);
		loginPanel.add(pinTextField);
		pinTextField.setColumns(10);
		
		logonButton = new JButton("Logon");
		loginPanel.add(logonButton);
		
		messageTextArea = new JTextArea();
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setEditable(false);
		//messageTextArea.set
		contentPane.add(messageTextArea, BorderLayout.CENTER);
		
		functionPanel = new JPanel();
		contentPane.add(functionPanel, BorderLayout.WEST);
		functionPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amountText = amountTextField.getText();
				if(validateAmount(amountText)) {
					CheckingAccount ca = customer.getCheckingAccount();
					double amount = Double.parseDouble(amountText);
					double amountWithdrawn = ca.withdraw(amount);
					if(MathUtil.equals(amount, amountWithdrawn, DOLLAR_DELTA))
						messageTextArea.setText(CURRENCY_FORMATTER.format(amountWithdrawn) + " was withdrawn.");
					else
						messageTextArea.setText("The transaction could not be completed - low funds.");
				}
			}
		});
		functionPanel.add(withdrawButton);
		
		depositButton = new JButton("Deposit");
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amountText = amountTextField.getText();
				if(validateAmount(amountText)) {
					CheckingAccount ca = customer.getCheckingAccount();
					double amount = Double.parseDouble(amountText);
					double deposit = ca.deposit(amount);
					messageTextArea.setText(CURRENCY_FORMATTER.format(deposit) + " was deposited.");
				}
			}
		});
		functionPanel.add(depositButton);
		
		balanceButton = new JButton("Request Balance");
		balanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckingAccount ca = customer.getCheckingAccount();
				double balance = ca.getBalance();
				messageTextArea.setText("Your available balance is " + CURRENCY_FORMATTER.format(balance));
			}
		});
		functionPanel.add(balanceButton);
		
		logoffButton = new JButton("Logoff");
		logoffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customer = null;
				initState();
			}
		});
		functionPanel.add(logoffButton);
		
		amountPanel = new JPanel();
		contentPane.add(amountPanel, BorderLayout.SOUTH);
		
		JLabel dollarSignLabel = new JLabel("$");
		amountPanel.add(dollarSignLabel);
		
		amountTextField = new JTextField();
		amountTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				amountTextField.setBackground(Color.WHITE);
				amountTextField.setText("");
			}
		});
		amountTextField.setText("Amount");
		amountPanel.add(amountTextField);
		amountTextField.setColumns(10);
	}
	
	private boolean validateAmount(String amountText) {
		boolean valid = AMOUNT_PATTERN.matcher(amountText).matches();
		if(!valid) {
			messageTextArea.setText(amountText + " is not a valid amount!  Enter a whole number followed by a maximum of two decimal places.");
			amountTextField.setBackground(Color.YELLOW);
		}
		return valid;
	}
	
	private void initState() {
		loginLabel.setText(customer == null ? "Logon" : "Welcome");
		nameTextField.setText(customer == null ? "Name" : customer.getName());
		nameTextField.setEditable(customer == null);
		pinTextField.setEnabled(customer == null);
		pinTextField.setText(customer == null ? "Pin" : "");
		pinTextField.setVisible(customer == null);
		logonButton.setVisible(customer == null);
		messageTextArea.setText(customer == null ? "Please logon." : "Please enter an amount, select an account, and execute a transaction.");
		amountPanel.setVisible(customer != null);
		functionPanel.setVisible(customer != null);
	}
}
