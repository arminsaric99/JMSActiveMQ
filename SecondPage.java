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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SecondPage extends JFrame {

	private static JLabel imageLabel;
	private static JLabel textLabel;
	private static JLabel textLabel2;
	private static JButton producerButton;
	private static JButton consumerButton;
	private static JButton backButton;
	private static JLabel imageLabel2;
	private static JButton logoutButton;

	public SecondPage() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Prosumer");
		frame.setSize(1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);

		panel.setLayout(null);

		textLabel = new JLabel("Welcome to Prosumer app!");
		textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textLabel.setForeground(Color.BLUE);
		textLabel.setBounds(20, 60, 625, 25);
		panel.add(textLabel);

		textLabel2 = new JLabel(
				"Do you want to produce or to consume message from our queue?");
		textLabel2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textLabel2.setForeground(Color.BLUE);
		textLabel2.setBounds(20, 100, 700, 25);
		panel.add(textLabel2);

		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/activemq2.png"))
				.getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(20, 65, 1000, 450);
		panel.add(imageLabel);

		producerButton = new JButton("Produce");
		producerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					MapMessageProducer2 producer = new MapMessageProducer2();
					MapMessageProducer2.main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		Image img3 = new ImageIcon(this.getClass().getResource("/produce.png"))
				.getImage();
		producerButton.setIcon(new ImageIcon(img3));
		producerButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		producerButton.setBackground(Color.BLUE);
		producerButton.setForeground(Color.WHITE);
		producerButton.setBounds(785, 160, 160, 65);

		panel.add(producerButton);

		consumerButton = new JButton("Consume");
		consumerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					MapMessageConsumer2 producer = new MapMessageConsumer2();
					MapMessageConsumer2.main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		Image img4 = new ImageIcon(this.getClass().getResource("/send3.png"))
				.getImage();
		consumerButton.setIcon(new ImageIcon(img4));
		consumerButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		consumerButton.setBackground(Color.BLUE);
		consumerButton.setForeground(Color.WHITE);
		consumerButton.setBounds(785, 270, 160, 65);

		panel.add(consumerButton);

		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FirstPage back = new FirstPage();
					FirstPage.main(null);

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
		backButton.setBounds(850, 20, 110, 45);

		panel.add(backButton);

		logoutButton = new JButton("Logout");
		logoutButton.setBackground(Color.BLUE);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				JFrame exit = new JFrame("Logout");
				if (JOptionPane.showConfirmDialog(exit,
						"Do you want to logout?", "Message Producer",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					FirstPage logout = new FirstPage();
					FirstPage.main(null);
				}
			}
		});

		Image img5 = new ImageIcon(this.getClass().getResource("/logout.png"))
				.getImage();
		logoutButton.setIcon(new ImageIcon(img5));
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		logoutButton.setBounds(1000, 20, 110, 45);
		panel.add(logoutButton);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new SecondPage();
	}

}
