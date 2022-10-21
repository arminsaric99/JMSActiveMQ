package mq.mess;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserRegistration extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField firstname;
	private static JTextField lastname;
	private static JTextField email;
	private static JTextField username;
	private static JTextField mob;
	private static JPasswordField passwordField;
	private static JButton btnNewButton;
	private static JButton backButton;
	private static JLabel imageLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration frame = new UserRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserRegistration() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 600);
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/register.png"))
				.getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(900, 50, 1000, 450);
		contentPane.add(imageLabel);

		JLabel lblNewUserRegister = new JLabel("New User Register");
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewUserRegister.setForeground(Color.BLUE);
		lblNewUserRegister.setBounds(390, 52, 325, 45);
		contentPane.add(lblNewUserRegister);

		JLabel lblName = new JLabel("First name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblName.setBounds(32, 152, 99, 43);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("Last name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(32, 243, 110, 29);
		contentPane.add(lblNewLabel);

		JLabel lblEmailAddress = new JLabel("Email\r\n address");
		lblEmailAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEmailAddress.setBounds(32, 324, 124, 36);
		contentPane.add(lblEmailAddress);

		firstname = new JTextField();
		firstname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		firstname.setBounds(189, 151, 228, 45);
		contentPane.add(firstname);
		firstname.setColumns(10);

		lastname = new JTextField();
		lastname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lastname.setBounds(189, 235, 228, 45);
		contentPane.add(lastname);
		lastname.setColumns(10);

		email = new JTextField();

		email.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		email.setBounds(189, 320, 228, 45);
		contentPane.add(email);
		email.setColumns(10);

		username = new JTextField();
		username.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		username.setBounds(682, 151, 228, 45);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUsername.setBounds(517, 159, 99, 29);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(517, 245, 99, 24);
		contentPane.add(lblPassword);

		JLabel lblMobileNumber = new JLabel("Mobile number");
		lblMobileNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMobileNumber.setBounds(517, 329, 139, 26);
		contentPane.add(lblMobileNumber);

		mob = new JTextField();
		mob.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mob.setBounds(682, 320, 228, 45);
		contentPane.add(mob);
		mob.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField.setBounds(682, 235, 228, 45);
		contentPane.add(passwordField);

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

		Image img1 = new ImageIcon(this.getClass().getResource("/back.png"))
				.getImage();
		backButton.setIcon(new ImageIcon(img1));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBackground(Color.BLUE);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(50, 20, 110, 45);

		contentPane.add(backButton);

		btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = firstname.getText();
				String lastName = lastname.getText();
				String emailId = email.getText();
				String userName = username.getText();
				String mobileNumber = mob.getText();
				String password = passwordField.getText();

				String msg = "" + firstName;
				msg += " \n";

				if (firstName.equals("") || lastName.equals("")
						|| emailId.equals("") || userName.equals("")
						|| mobileNumber.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null,
							"You need to fill all cells! ", "Prosumer",
							JOptionPane.ERROR_MESSAGE);
				} else {

					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/registration",
								"root", "");

						String query = "INSERT INTO account values('"
								+ firstName + "','" + lastName + "','"
								+ userName + "','" + password + "','" + emailId
								+ "','" + mobileNumber + "')";

						Statement sta = connection.createStatement();
						int x = sta.executeUpdate(query);
						if (x == 0) {
							JOptionPane.showMessageDialog(btnNewButton,
									"This account already exist");
						} else {
							JOptionPane
									.showMessageDialog(
											btnNewButton,
											"Welcome, "
													+ msg
													+ "Your account is sucessfully created");
						}
						connection.close();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(425, 447, 250, 74);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
	}
}
