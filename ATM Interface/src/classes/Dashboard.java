package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {
	
	JButton deposite, fastCash, balance, withdrawl, pinChange, mini;
	JButton exit;
	String pin_number;

	Dashboard(String pin_number) {
		this.pin_number = pin_number;

		ImageIcon back_imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm_back.jpg"));
		Image back_image = back_imageIcon1.getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT);
		ImageIcon back_imageIcon2 = new ImageIcon(back_image);
		JLabel image = new JLabel(back_imageIcon2);
		image.setBounds(0,0,850,800);
		add(image);
		
		JLabel text = new JLabel("Please choose your transaction");
		text.setFont(new Font("serif", Font.BOLD, 25));
		text.setForeground(Color.WHITE);
		text.setBounds(265, 280, 500, 30);
		image.add(text);
		
		deposite = new JButton("Deposite");
		deposite.setFont(new Font("serif", Font.BOLD, 20));
		deposite.setBounds(200, 400, 200, 30);
		deposite.addActionListener(this);
		image.add(deposite);
		
		fastCash = new JButton("Fast Cash");
		fastCash.setFont(new Font("serif", Font.BOLD, 20));
		fastCash.setBounds(200, 450, 200, 30);
		fastCash.addActionListener(this);
		image.add(fastCash);
		
		balance = new JButton("Balance Enquiry");
		balance.setFont(new Font("serif", Font.BOLD, 20));
		balance.setBounds(200, 500, 200, 30);
		balance.addActionListener(this);
		image.add(balance);
		
		withdrawl = new JButton("Withdrawl");
		withdrawl.setFont(new Font("serif", Font.BOLD, 20));
		withdrawl.setBounds(450, 400, 200, 30);
		withdrawl.addActionListener(this);
		image.add(withdrawl);
		
		pinChange = new JButton("Pin Change");
		pinChange.setFont(new Font("serif", Font.BOLD, 20));
		pinChange.setBounds(450, 450, 200, 30);
		pinChange.addActionListener(this);
		image.add(pinChange);
		
		mini = new JButton("Mini Statement");
		mini.setFont(new Font("serif", Font.BOLD, 20));
		mini.setBounds(450, 500, 200, 30);
		mini.addActionListener(this);
		image.add(mini);
		
		exit = new JButton("Exit");
		exit.setFont(new Font("serif", Font.BOLD, 18));
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		exit.setBounds(570, 560, 80, 40);
		exit.addActionListener(this);
		image.add(exit);
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Automated Teller Machine");
		setSize(900,700);
		setLayout(null);
		setUndecorated(true);
		setVisible(true);
		setLocation(220,30);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dashboard("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == deposite) {
			setVisible(false);
			new Deposite(pin_number).setVisible(true);
		} else if (e.getSource() == fastCash) {
			setVisible(false);
			new FastCash(pin_number).setVisible(true);
		} else if (e.getSource() == balance) {
			setVisible(false);
			new BalanceEnquiry(pin_number).setVisible(true);
		} else if (e.getSource() == withdrawl) {
			setVisible(false);
			new Withdrawl(pin_number).setVisible(true);
		} else if (e.getSource() == pinChange) {
			setVisible(false);
			new PinChange(pin_number).setVisible(true);
		} else if (e.getSource() == mini) {
			new MiniStatement(pin_number).setVisible(true);
		} else if (e.getSource() == exit) {
			setVisible(false);
		}
	}

}
