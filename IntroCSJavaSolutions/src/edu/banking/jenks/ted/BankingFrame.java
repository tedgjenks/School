package edu.banking.jenks.ted;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JList;

public class BankingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField pinTextField;
	private JTextField amountTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankingFrame frame = new BankingFrame();
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
		setBounds(100, 100, 541, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		contentPane.add(loginPanel, BorderLayout.NORTH);
		
		JLabel loginLabel = new JLabel("Welcome");
		loginPanel.add(loginLabel);
		
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setText("Name");
		loginPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		pinTextField = new JTextField();
		pinTextField.setEditable(false);
		pinTextField.setText("Pin");
		loginPanel.add(pinTextField);
		pinTextField.setColumns(10);
		
		JTextArea txtrPleaseEnterAn = new JTextArea();
		txtrPleaseEnterAn.setText("Please enter an amount, select an account, and execute a transaction.");
		txtrPleaseEnterAn.setEditable(false);
		contentPane.add(txtrPleaseEnterAn, BorderLayout.CENTER);
		
		JPanel functionPanel = new JPanel();
		contentPane.add(functionPanel, BorderLayout.WEST);
		functionPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JButton withdrawButton = new JButton("Withdraw");
		functionPanel.add(withdrawButton);
		
		JButton depositButton = new JButton("Deposit");
		functionPanel.add(depositButton);
		
		JButton balanceButton = new JButton("Request Balance");
		functionPanel.add(balanceButton);
		
		JButton transferButton = new JButton("Transfer");
		transferButton.setEnabled(false);
		functionPanel.add(transferButton);
		
		JButton logoffButton = new JButton("Logoff");
		functionPanel.add(logoffButton);
		
		JPanel amountPanel = new JPanel();
		contentPane.add(amountPanel, BorderLayout.SOUTH);
		
		JLabel dollarSignLabel = new JLabel("$");
		amountPanel.add(dollarSignLabel);
		
		amountTextField = new JTextField();
		amountTextField.setText("Amount");
		amountPanel.add(amountTextField);
		amountTextField.setColumns(10);
		
		JLabel sourceLabel = new JLabel("Source:");
		sourceLabel.setEnabled(false);
		amountPanel.add(sourceLabel);
		
		JList sourceList = new JList();
		sourceList.setEnabled(false);
		amountPanel.add(sourceList);
		
		JLabel targetLabel = new JLabel("Target:");
		targetLabel.setEnabled(false);
		amountPanel.add(targetLabel);
		
		JList targetList = new JList();
		targetList.setEnabled(false);
		amountPanel.add(targetList);
	}

}
