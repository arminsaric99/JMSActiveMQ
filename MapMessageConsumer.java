package mq.mess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MapMessageConsumer2 implements ActionListener {

	private static JLabel imageLabel;
	private static JLabel textLabel;
	private static JLabel textLabel2;
	private static JButton sendButton;
	private static JButton exitButton;
	private static JButton backButton;
	private static JButton logoutButton;

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL : tcp://localhost:61616"
	private static String jmsQueue = "new_QUEUE";

	public static void main(String[] args) throws JMSException {
		MapMessageConsumer2 consume = new MapMessageConsumer2();
		consume.consumeMessage();
	}

	public void consumeMessage() throws JMSException {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Message Consumer");
		frame.setSize(1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);

		panel.setLayout(null);

		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					SecondPage back = new SecondPage();
					SecondPage.main(null);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		Image img6 = new ImageIcon(this.getClass().getResource("/back.png"))
				.getImage();
		backButton.setIcon(new ImageIcon(img6));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBackground(Color.BLUE);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(50, 20, 110, 45);

		panel.add(backButton);

		textLabel = new JLabel("Welcome to Message Consumer!");
		textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textLabel.setForeground(Color.BLUE);
		textLabel.setBounds(20, 110, 625, 25);
		panel.add(textLabel);

		textLabel2 = new JLabel(
				"Do you want to send an email from data which Message Producer has sent to the queue?");
		textLabel2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textLabel2.setForeground(Color.BLUE);
		textLabel2.setBounds(20, 150, 800, 25);
		panel.add(textLabel2);

		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/activemq2.png"))
				.getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(20, 110, 1000, 450);
		panel.add(imageLabel);

		sendButton = new JButton("Send");
		Image img2 = new ImageIcon(this.getClass().getResource("/send3.png"))
				.getImage();
		sendButton.setIcon(new ImageIcon(img2));
		sendButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		sendButton.setBounds(750, 300, 100, 45);
		sendButton.setBackground(Color.BLUE);
		sendButton.setForeground(Color.WHITE);
		sendButton.addActionListener(this);
		panel.add(sendButton);

		exitButton = new JButton("Exit");
		exitButton.setBackground(Color.BLUE);
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				JFrame exit = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(exit,
						"Do you wanto to exit?", "Message Consumer",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/exit.png"))
				.getImage();
		exitButton.setIcon(new ImageIcon(img4));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		exitButton.setBounds(900, 300, 100, 45);
		panel.add(exitButton);

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

	public void sendEmail() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					url);
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(jmsQueue);
			MessageConsumer consumer = session.createConsumer(destination);

			MapMessage mapMessage = (MapMessage) consumer.receive();
			String from = mapMessage.getString(Constants.FROM);
			String to = mapMessage.getString(Constants.TO);
			String subject = mapMessage.getString(Constants.SUBJECT);
			String content = mapMessage.getString(Constants.CONTENT);

			SimpleEmailSender sender = new SimpleEmailSender();
			sender.sendSimpleEmail(from, to, subject, content);

			connection.close();

		} catch (JMSException e1) {
			e1.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

		sendEmail();

		JOptionPane.showMessageDialog(null,
				"You have successfuly send an e-mail! ", "Message Consumer",
				JOptionPane.INFORMATION_MESSAGE);

	}
}

