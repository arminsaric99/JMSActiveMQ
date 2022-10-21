package mq.mess;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JLabel label;
	private JPanel contentPane;
	private JButton backButton;
	private JLabel imageLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 600);

		setResizable(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login2.png"))
				.getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(850, 50, 1000, 450);
		contentPane.add(imageLabel);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(485, 13, 273, 93);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(481, 170, 281, 45);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(481, 286, 281, 45);
		contentPane.add(passwordField);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBackground(Color.BLACK);
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblUsername.setBounds(310, 162, 193, 52);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBackground(Color.CYAN);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPassword.setBounds(310, 282, 193, 52);
		contentPane.add(lblPassword);

		btnNewButton = new JButton("Login");

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(485, 392, 162, 73);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				String password = passwordField.getText();
				try {

					Connection connection = (Connection) DriverManager
							.getConnection(
									"jdbc:mysql://localhost:3306/registration",
									"root", "");

					PreparedStatement st = (PreparedStatement) connection
							.prepareStatement("Select user_name, password from account where user_name=? and password=?");

					st.setString(1, userName);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						dispose();
						SecondPage home = new SecondPage();

						JOptionPane.showMessageDialog(btnNewButton,
								"You have successfully logged in");
					} else {
						JOptionPane.showMessageDialog(btnNewButton,
								"Wrong Username or Password");
					}
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		});

		contentPane.add(btnNewButton);

		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					FirstPage back = new FirstPage();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		Image img2 = new ImageIcon(this.getClass().getResource("/back.png"))
				.getImage();
		backButton.setIcon(new ImageIcon(img2));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBackground(Color.BLUE);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(50, 20, 110, 45);

		contentPane.add(backButton);

		label = new JLabel("");
		label.setBounds(0, 0, 1008, 562);
		contentPane.add(label);
	}
}
