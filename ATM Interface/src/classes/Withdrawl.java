package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Withdrawl extends JFrame implements ActionListener {
	
	JTextField amount;
	JButton withdraw, back, saving, current;
	String pin_number;
	JLabel text,type;

	Withdrawl (String pin_number) {
		this.pin_number = pin_number;
		
		ImageIcon back_imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm_back.jpg"));
		Image back_image = back_imageIcon1.getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT);
		ImageIcon back_imageIcon2 = new ImageIcon(back_image);
		JLabel image = new JLabel(back_imageIcon2);
		image.setBounds(0,0,850,800);
		add(image);
		
		type = new JLabel("Select type of account.");
		type.setFont(new Font("serif", Font.BOLD, 25));
		type.setForeground(Color.WHITE);
		type.setBounds(230, 280, 500, 30);
		image.add(type);
		
		text = new JLabel("Enter amount you want to withdraw.");
		text.setFont(new Font("serif", Font.BOLD, 25));
		text.setForeground(Color.WHITE);
		text.setBounds(230, 280, 500, 30);
		text.setVisible(false);
		image.add(text);
		
		amount = new JTextField();
		amount.setFont(new Font("serif", Font.BOLD, 20));
		amount.setBounds(310, 350, 200, 30);
		amount.setVisible(false);
		image.add(amount);
		
		current = new JButton("Current");
		current.setFont(new Font("serif", Font.BOLD, 20));
		current.setBounds(450, 400, 150, 30);
		current.addActionListener(this);
		image.add(current);
		
		saving = new JButton("Saving");
		saving.setFont(new Font("serif", Font.BOLD, 20));
		saving.setBounds(230, 400, 100, 30);
		saving.addActionListener(this);
		image.add(saving);
		
		withdraw = new JButton("Withdraw");
		withdraw.setFont(new Font("serif", Font.BOLD, 20));
		withdraw.setBounds(450, 400, 150, 30);
		withdraw.addActionListener(this);
		image.add(withdraw);
		
		back = new JButton("<- Back");
		back.setFont(new Font("serif", Font.BOLD, 20));
		back.setBounds(230, 400, 100, 30);
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
		new Deposite("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			setVisible(false);
			new Dashboard(pin_number).setVisible(true);
		} else if (e.getSource() == saving) {
			type.setVisible(false);
			current.setVisible(false);
			saving.setVisible(false);
			
			text.setVisible(true);
			amount.setVisible(true);
			back.setVisible(true);
			withdraw.setVisible(true);
		} else if (e.getSource() == current) {
			type.setVisible(false);
			current.setVisible(false);
			saving.setVisible(false);
			
			text.setVisible(true);
			amount.setVisible(true);
			back.setVisible(true);
			withdraw.setVisible(true);
		} else if (e.getSource() == withdraw) {
			String rupee = amount.getText();
			Date date = new Date();
			if (rupee.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter amount.");
			} else {
				try {
					Conn conn = new Conn();
					String query = "insert into bank values('"+pin_number+"', '"+date+"', 'Withdrawl', '"+rupee+"')";
					conn.s.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Rs. "+rupee+" Withdraw Successfully.");
					setVisible(false);
					new Dashboard(pin_number).setVisible(true);
				}catch (Exception exp) {
					// TODO: handle exception
					System.out.println(exp);
				}
			}
		}
	}

}
