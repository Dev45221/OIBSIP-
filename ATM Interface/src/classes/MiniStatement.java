package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniStatement extends JFrame implements ActionListener {

	JButton back;
	String pin_number;
	
	MiniStatement(String pin_number) {
		this.pin_number = pin_number;
		
		JLabel state = new JLabel();
		state.setBounds(20,140,400,200);
		add(state);
		
		JLabel bank = new JLabel("Bank Name");
		bank.setBounds(150, 20, 100, 20);
		add(bank);
		
		JLabel card = new JLabel();
		card.setBounds(20, 80, 300, 20);
		add(card);
		
		JLabel text1 = new JLabel("Your available balance." );
		text1.setFont(new Font("serif", Font.BOLD, 16));
		text1.setBounds(20, 370, 300, 20);
		add(text1);
		
		JLabel text2 = new JLabel();
		text2.setFont(new Font("serif", Font.BOLD, 16));
		text2.setBounds(20, 400, 300, 20);
		add(text2);
		
		back = new JButton("Back");
		back.setFont(new Font("serif", Font.BOLD, 20));
		back.setBounds(140, 450, 100, 30);
		back.addActionListener(this);
		add(back);

		try {
			Conn conn = new Conn();
			ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pin_number+"' ");
			while(rs.next()) {
				card.setText("Card Number: " + rs.getString("card_number").substring(0,4) + "XXXXXXXX" + rs.getString("card_number").substring(12));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		try {
			Conn conn = new Conn();
			ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pin_number+"' ");
			int balance = 0;
			while(rs.next()) {
				state.setText(state.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br></html> " );
				if (rs.getString("type").equals("Deposite")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}	
			}
			text2.setText("Rs. " + balance);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Automated Teller Machine");
		setSize(400,600);
		setLayout(null);
		setVisible(true);
		setLocation(480,110);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MiniStatement("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
