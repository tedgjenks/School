package edu.jenks.unit3;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import jenks.threads.ComponentUpdater;

public class SlotMachine extends JFrame {

	private static final long serialVersionUID = 5711714059891894156L;
	private JPanel contentPane;
	private JTextField betTextField;
	private JLabel messageLabel;
	private JLabel statusLabel;
	private JLabel number1Label;
	private JLabel number2Label;
	private JLabel number3Label;
	private final Random RANDOM = new Random(System.currentTimeMillis());
	private int number1, number2, number3, money, bet = 1;
	private final short WIN_2 = 2, WIN_ALL = 50;
	private final String STATUS_LABEL_TEXT = "Status: $";
	private JButton playButton;
	private JPanel centerPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlotMachine frame = new SlotMachine();
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
	public SlotMachine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel titleLabel = new JLabel("Slot Machine");
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseMachine(true);
				if(validateBet()) {
					executeSlotEffects();
					number1 = RANDOM.nextInt(10);
					number2 = RANDOM.nextInt(10);
					number3 = RANDOM.nextInt(10);
					number1Label.setText(String.valueOf(number1));
					number2Label.setText(String.valueOf(number2));
					number3Label.setText(String.valueOf(number3));
					executeBet();
				}
				pauseMachine(false);
			}
		});
		westPanel.add(playButton);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		JButton button = new JButton("Quit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		southPanel.add(button);
		
		JPanel messagePanel = new JPanel();
		southPanel.add(messagePanel);
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		
		statusLabel = new JLabel(STATUS_LABEL_TEXT + 0);
		messagePanel.add(statusLabel);
		
		messageLabel = new JLabel("Enter bet (minium $1) and click \"Play\"");
		messagePanel.add(messageLabel);
		
		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		
		JLabel betLabel = new JLabel("Enter Bet");
		eastPanel.add(betLabel);
		
		betTextField = new JTextField();
		betTextField.setText(String.valueOf(bet));
		eastPanel.add(betTextField);
		betTextField.setColumns(10);
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		number1Label = new JLabel("0");
		centerPanel.add(number1Label);
		
		number2Label = new JLabel("0");
		centerPanel.add(number2Label);
		
		number3Label = new JLabel("0");
		centerPanel.add(number3Label);
	}
	
	private boolean validateBet() {
		boolean validBet = true;
		String bet = betTextField.getText();
		try {
			this.bet = Integer.parseInt(bet);
			if(this.bet < 1)
				validBet = false;
		} catch(NumberFormatException e) {
			validBet = false;
		}
		if(!validBet) {
			messageLabel.setText("You must specify an integer greater than or equal to one");
			betTextField.setText("1");
		}
		return validBet;
	}
	
	private void executeBet() {
		if(number1 == number2 || number2 == number3 || number1 == number3) { // winner
			if(number1 == number2 && number2 == number3) { // all match
				money += WIN_ALL;
				messageLabel.setText("All match.  Look at the big winner!");
			} else { // 2 match
				money += WIN_2;
				messageLabel.setText("Two match.  Congratulations!");
			}
		} else { // loser
			money -= bet;
			messageLabel.setText("Better luck next time!");
		}
		statusLabel.setText(STATUS_LABEL_TEXT + money);
	}
	
	private void executeSlotEffects() {
		executeBetAudio();
		executeBetVisual();
		ComponentUpdater cu = new ComponentUpdater(this);
		cu.setDaemon(true);
		cu.start();
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace(System.err);
		}
		number1Label.setIcon(null);
		number2Label.setIcon(null);
		number3Label.setIcon(null);
	}
	
	private void executeBetAudio() {
		String soundName = "audio/handle.wav";
		try {
			AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(soundName));
			/*AudioFormat audioFormat = audioIS.getFormat();
			System.out.println("Format: " + audioFormat);
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
			new PlayThread(sourceDataLine, audioIS, audioFormat);*/
			Clip clip = AudioSystem.getClip();
			clip.open(audioIS);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	private void executeBetVisual() {
		Icon icon = new ImageIcon("images/Reel_spins.gif", "Reel Animation");
		number1Label.setText("");
		number1Label.setIcon(icon);
		number2Label.setText("");
		number2Label.setIcon(icon);
		number3Label.setText("");
		number3Label.setIcon(icon);
	}
	
	private void pauseMachine(boolean pause) {
		playButton.setEnabled(!pause);
	}

}
