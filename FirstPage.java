package mq.mess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstPage extends JFrame {

	private static JLabel textLabel;
	private static JLabel textLabel2;
	private static JButton loginButton;
	private static JButton registrationButton;
	private static JButton backButton;
	private static JLabel textLabel3;
	private static JLabel imageLabel;

	public FirstPage() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Prosumer");
		frame.setSize(1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);

		panel.setLayout(null);

		textLabel = new JLabel("Welcome to Prosumer app!");
		textLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		textLabel.setForeground(Color.BLUE);
		textLabel.setBounds(330, 20, 625, 50);
		panel.add(textLabel);

		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/home.png"))
				.getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(100, 50, 1000, 450);
		panel.add(imageLabel);

		textLabel2 = new JLabel("Do you want to Login or Register?");
		textLabel2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		textLabel2.setForeground(Color.BLUE);
		textLabel2.setBounds(330, 70, 625, 50);
		panel.add(textLabel2);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					UserLogin login = new UserLogin();
					UserLogin.main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		loginButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		loginButton.setBackground(Color.BLUE);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(550, 200, 200, 75);

		panel.add(loginButton);

		registrationButton = new JButton("Register");
		registrationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					UserRegistration register = new UserRegistration();
					UserRegistration.main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		registrationButton.setBackground(Color.BLUE);
		registrationButton.setForeground(Color.WHITE);
		registrationButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		registrationButton.setBounds(550, 300, 200, 75);
		panel.add(registrationButton);

		textLabel = new JLabel("If you don't have an account!");
		textLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		textLabel.setForeground(Color.BLACK);
		textLabel.setBounds(550, 370, 625, 50);
		panel.add(textLabel);

		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new FirstPage();
	}

}
