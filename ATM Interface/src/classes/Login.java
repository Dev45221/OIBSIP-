package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

/**
 * @author DEV
 *
 */
public class Login extends JFrame implements ActionListener {
	
	JButton login, clear, cancel;
	JTextField cardText;
	JPasswordField pinText;

	Login() {
		// TODO Auto-generated constructor stub

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel label1 = new JLabel(i3);
		label1.setBounds(100,30,100,100);
		add(label1);
		
		JLabel text = new JLabel("Welcome to ATM");
		text.setFont(new Font("serif", Font.BOLD, 35));
		text.setBounds(250, 70, 500, 40);
		add(text);
		
		JLabel cardNo = new JLabel("Card Number :- ");
		cardNo.setFont(new Font("serif", Font.BOLD, 20));
		cardNo.setBounds(150, 180, 200, 40);
		add(cardNo);
		
		cardText = new JTextField();
		cardText.setFont(new Font("serif", Font.BOLD, 18));
		cardText.setBounds(300, 190, 200, 25);
		add(cardText);

		JLabel pin = new JLabel("Pin :- ");
		pin.setFont(new Font("serif", Font.BOLD, 20));
		pin.setBounds(235, 220, 100, 40);
		add(pin);
		
		pinText = new JPasswordField();
		pinText.setFont(new Font("serif", Font.BOLD, 18));
		pinText.setBounds(300, 230, 100, 25);
		add(pinText);
		
		login = new JButton("Login");
		login.setFont(new Font("serif", Font.BOLD, 20));
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		login.setBounds(180, 290, 100, 40);
		login.addActionListener(this);
		add(login);
		
		clear = new JButton("Clear");
		clear.setFont(new Font("serif", Font.BOLD, 20));
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		clear.setBounds(300, 290, 100, 40);
		clear.addActionListener(this);
		add(clear);
		
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("serif", Font.BOLD, 20));
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(180, 340, 220, 40);
		cancel.addActionListener(this);
		add(cancel);
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Login");
		setSize(700,500);
		setUndecorated(true);
		setLayout(null);
		setVisible(true);
		setLocation(300,120);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == login) {
				Conn conn = new Conn();
				String card = cardText.getText();
				String pin = pinText.getText();
				
				String query = "select * from login where card_number = '"+card+"' and pin = '"+pin+"' ";
				try {
					ResultSet rs = conn.s.executeQuery(query);
					if (rs.next()) {
						setVisible(false);
						new Dashboard(pin).setVisible(true);					
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect Pin or Card Number");
					}
				}catch (Exception exp) {
					// TODO: handle exception
					System.out.println(exp);
				}
		}else if (e.getSource() == clear) {
			cardText.setText("");
			pinText.setText("");
		}else if (e.getSource() == cancel) {
			setVisible(false);
		}
	}

}
