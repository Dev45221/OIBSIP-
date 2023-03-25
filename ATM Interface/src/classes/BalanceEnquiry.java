package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BalanceEnquiry extends JFrame implements ActionListener {
	
	JButton back;
	String pin_number;

	BalanceEnquiry (String pin_number) {
		this.pin_number = pin_number;
		
		ImageIcon back_imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm_back.jpg"));
		Image back_image = back_imageIcon1.getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT);
		ImageIcon back_imageIcon2 = new ImageIcon(back_image);
		JLabel image = new JLabel(back_imageIcon2);
		image.setBounds(0,0,850,800);
		add(image);
		
		JLabel text = new JLabel("Account Balance");
		text.setFont(new Font("serif", Font.BOLD, 25));
		text.setForeground(Color.WHITE);
		text.setBounds(340, 280, 500, 30);
		image.add(text);
		
		Conn conn = new Conn();
		int balance = 0;
		try {
			ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pin_number+"' ");
			while(rs.next()) {
				if (rs.getString("type").equals("Deposite")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}
		}catch (Exception exp) {
			// TODO: handle exception
			System.out.println(exp);
		}
		
		JLabel text1 = new JLabel("Your available balance is : Rs. " + balance );
		text1.setFont(new Font("serif", Font.BOLD, 20));
		text1.setForeground(Color.WHITE);
		text1.setBounds(250, 350, 700, 30);
		image.add(text1);
		
		back = new JButton("Back");
		back.setFont(new Font("serif", Font.BOLD, 20));
		back.setBounds(500, 450, 100, 30);
		back.addActionListener(this);
		image.add(back);
		
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
		new BalanceEnquiry("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
		new Dashboard(pin_number).setVisible(true);
	}

}
