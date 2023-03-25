package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FastCash extends JFrame implements ActionListener {

	JButton deposite, fastCash, balance, withdrawl, pinChange, mini;
	JButton back;
	String pin_number;
	
	FastCash(String pin_number) {
		this.pin_number = pin_number;

		ImageIcon back_imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm_back.jpg"));
		Image back_image = back_imageIcon1.getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT);
		ImageIcon back_imageIcon2 = new ImageIcon(back_image);
		JLabel image = new JLabel(back_imageIcon2);
		image.setBounds(0,0,850,800);
		add(image);
		
		JLabel text = new JLabel("Select Withdrawl Amount");
		text.setFont(new Font("serif", Font.BOLD, 25));
		text.setForeground(Color.WHITE);
		text.setBounds(265, 280, 500, 30);
		image.add(text);
		
		deposite = new JButton("Rs. 100");
		deposite.setFont(new Font("serif", Font.BOLD, 20));
		deposite.setBounds(200, 400, 200, 30);
		deposite.addActionListener(this);
		image.add(deposite);
		
		fastCash = new JButton("Rs. 200");
		fastCash.setFont(new Font("serif", Font.BOLD, 20));
		fastCash.setBounds(200, 450, 200, 30);
		fastCash.addActionListener(this);
		image.add(fastCash);
		
		balance = new JButton("Rs. 500");
		balance.setFont(new Font("serif", Font.BOLD, 20));
		balance.setBounds(200, 500, 200, 30);
		balance.addActionListener(this);
		image.add(balance);
		
		withdrawl = new JButton("Rs. 1000");
		withdrawl.setFont(new Font("serif", Font.BOLD, 20));
		withdrawl.setBounds(450, 400, 200, 30);
		withdrawl.addActionListener(this);
		image.add(withdrawl);
		
		pinChange = new JButton("Rs. 5000");
		pinChange.setFont(new Font("serif", Font.BOLD, 20));
		pinChange.setBounds(450, 450, 200, 30);
		pinChange.addActionListener(this);
		image.add(pinChange);
		
		mini = new JButton("Rs. 10000");
		mini.setFont(new Font("serif", Font.BOLD, 20));
		mini.setBounds(450, 500, 200, 30);
		mini.addActionListener(this);
		image.add(mini);
		
		back = new JButton("Back");
		back.setFont(new Font("serif", Font.BOLD, 16));
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(570, 560, 80, 40);
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
		new FastCash("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			setVisible(false);
			new Dashboard(pin_number).setVisible(true);
		} else {
			String amount = ((JButton)e.getSource()).getText().substring(4); // Rs. 5000
			Conn conn = new Conn();
			try {
				ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pin_number+"' ");
				int balance = 0;
				while(rs.next()) {
					if (rs.getString("type").equals("Deposite")) {
						balance += Integer.parseInt(rs.getString("amount"));
					} else {
						balance -= Integer.parseInt(rs.getString("amount"));
					}
				}
				
				if (e.getSource() != back && balance < Integer.parseInt(amount)) {
					JOptionPane.showMessageDialog(null, "Insufficient Balance!");
					return;
				}
				
				Date date = new Date();
				String query = "insert into bank values('"+pin_number+"', '"+date+"', 'Withdrawl' ,'"+amount+"' )";
				conn.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully.");
				
				setVisible(false);
				new Dashboard(pin_number).setVisible(true);
			}catch (Exception exp) {
				// TODO: handle exception
				System.out.println(exp);
			}
			
		}
		
	}

}
