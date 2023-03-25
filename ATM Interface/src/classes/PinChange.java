package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PinChange extends JFrame implements ActionListener {

	JButton change, back;
	JPasswordField pin, repin;
	String pin_number;
	
	PinChange(String pin_number) {
		this.pin_number = pin_number;
		
		ImageIcon back_imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm_back.jpg"));
		Image back_image = back_imageIcon1.getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT);
		ImageIcon back_imageIcon2 = new ImageIcon(back_image);
		JLabel image = new JLabel(back_imageIcon2);
		image.setBounds(0,0,850,800);
		add(image);
		
		JLabel text = new JLabel("Change Pin");
		text.setFont(new Font("serif", Font.BOLD, 25));
		text.setForeground(Color.WHITE);
		text.setBounds(350, 280, 500, 30);
		image.add(text);
		
		JLabel text1 = new JLabel("Enter New Pin:");
		text1.setFont(new Font("serif", Font.BOLD, 20));
		text1.setForeground(Color.WHITE);
		text1.setBounds(250, 340, 180, 25);
		image.add(text1);
		
		pin = new JPasswordField();
		pin.setFont(new Font("serif", Font.BOLD, 20));
		pin.setBounds(410, 340, 180, 25);
		image.add(pin);
		
		JLabel text2 = new JLabel("Re-Enter New Pin:");
		text2.setFont(new Font("serif", Font.BOLD, 20));
		text2.setForeground(Color.WHITE);
		text2.setBounds(220, 380, 180, 25);
		image.add(text2);
		
		repin = new JPasswordField();
		repin.setFont(new Font("serif", Font.BOLD, 20));
		repin.setBounds(410, 380, 180, 25);
		image.add(repin);
		
		change = new JButton("Change");
		change.setFont(new Font("serif", Font.BOLD, 20));
		change.setBounds(500, 450, 100, 30);
		change.addActionListener(this);
		image.add(change);
		
		back = new JButton("Back");
		back.setFont(new Font("serif", Font.BOLD, 20));
		back.setBounds(500, 490, 100, 30);
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
		new PinChange("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == change) {
			try {
				String npin = pin.getText();
				String rpin = repin.getText();
				
				if (!npin.equals(rpin)) {
					JOptionPane.showMessageDialog(null, "Entered Pin doesn't match!");
					return;
				}
				if (npin.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter new  PIN");
					return;
				}
				if (rpin.equals("")) {
					JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
					return;
				}
				
				Conn c = new Conn();
				String query_login = "update login set pin = '"+rpin+"' where pin = '"+pin_number+"' ";
				String query_bank = "update bank set pin = '"+rpin+"' where pin = '"+pin_number+"' ";
				
				c.s.executeUpdate(query_login);
				c.s.executeUpdate(query_bank);
				
				JOptionPane.showMessageDialog(null, "PIN changed successgullly.");
				setVisible(false);
				new Dashboard(rpin).setVisible(true);
				
			}catch (Exception exp) {
				// TODO: handle exception
				System.out.println(exp);
			}
		} else if (e.getSource() == back) {
			setVisible(false);
			new Dashboard(pin_number).setVisible(true);
		}
	}

}
