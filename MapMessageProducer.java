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
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MapMessageProducer2 implements ActionListener {

	private static JLabel imageLabel;
	private static JLabel textLabel;
	private static JLabel textLabel2;
	private static JLabel fromLabel;
	private static JTextField fromText;
	private static JLabel toLabel;
	private static JTextField toText;
	private static JLabel subjectLabel;
	private static JTextField subjectText;
	private static JLabel contentLabel;
	private static JTextField contentText;
	private static JButton sendButton;
	private static JButton resetButton;
	private static JButton exitButton;
	private static JButton backButton;
	private static JButton logoutButton;

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL : tcp://localhost:61616"
	private static String jmsQueue = "new_QUEUE";

	public static void main(String[] args) throws JMSException {
		MapMessageProducer2 produce = new MapMessageProducer2();
		produce.displayGUI();
	}

	public void displayGUI() throws JMSException {

		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Message Producer");
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

		textLabel = new JLabel("Welcome to Message Producer!");
		textLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textLabel.setForeground(Color.BLUE);
		textLabel.setBounds(20, 150, 625, 25);
		panel.add(textLabel);

		textLabel2 = new JLabel(
				"Please insert data which Message Producer will send to the queue!");
		textLabel2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textLabel2.setForeground(Color.BLUE);
		textLabel2.setBounds(20, 180, 625, 25);
		panel.add(textLabel2);

		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/activemq2.png"))
				.getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(20, 150, 1000, 450);
		panel.add(imageLabel);

		fromLabel = new JLabel("From:");
		fromLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		fromLabel.setBounds(750, 20, 80, 25);
		panel.add(fromLabel);

		fromText = new JTextField(20);
		fromText.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		fromText.setBounds(900, 20, 230, 30);
		panel.add(fromText);

		toLabel = new JLabel("To:");
		toLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		toLabel.setBounds(750, 60, 80, 25);
		panel.add(toLabel);

		toText = new JTextField(20);
		toText.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		toText.setBounds(900, 60, 230, 30);
		panel.add(toText);

		subjectLabel = new JLabel("Subject:");
		subjectLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		subjectLabel.setBounds(750, 100, 80, 25);
		panel.add(subjectLabel);

		subjectText = new JTextField(20);
		subjectText.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		subjectText.setBounds(900, 100, 230, 30);
		panel.add(subjectText);

		contentLabel = new JLabel("Content:");
		contentLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentLabel.setBounds(750, 150, 80, 25);
		panel.add(contentLabel);

		contentText = new JTextField(20);
		contentText.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		contentText.setForeground(Color.BLACK);
		contentText.setBounds(900, 150, 230, 100);
		panel.add(contentText);

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

		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fromText.setText(null);
				toText.setText(null);
				subjectText.setText(null);
				contentText.setText(null);
			}
		});

		Image img3 = new ImageIcon(this.getClass().getResource("/clear.png"))
				.getImage();
		resetButton.setIcon(new ImageIcon(img3));
		resetButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		resetButton.setBackground(Color.BLUE);
		resetButton.setForeground(Color.WHITE);
		resetButton.setBounds(900, 300, 100, 45);

		panel.add(resetButton);

		exitButton = new JButton("Exit");
		exitButton.setBackground(Color.BLUE);
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				JFrame exit = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(exit, "Do you want to exit?",
						"Message Producer", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});

		Image img4 = new ImageIcon(this.getClass().getResource("/exit.png"))
				.getImage();
		exitButton.setIcon(new ImageIcon(img4));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		exitButton.setBounds(1050, 300, 100, 45);
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
		logoutButton.setBounds(210, 20, 110, 45);
		panel.add(logoutButton);

		frame.setVisible(true);

	}

	public void sendMessage(String from, String to, String subject,
			String content) {

		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					url);

			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(jmsQueue);

			MessageProducer producer = session.createProducer(destination);
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setString(Constants.FROM, from);
			mapMessage.setString(Constants.TO, to);
			mapMessage.setString(Constants.SUBJECT, subject);
			mapMessage.setString(Constants.CONTENT, content);

			producer.send(mapMessage);
			connection.close();

		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

		String from = fromText.getText();
		String to = toText.getText();
		String subject = subjectText.getText();
		String content = contentText.getText();

		if (from.equals("") || to.equals("") || subject.equals("")
				|| content.equals("")) {
			JOptionPane.showMessageDialog(null,
					"You need to fill all fields! ", "Message Producer",
					JOptionPane.ERROR_MESSAGE);
		}

		else {

			sendMessage(from, to, subject, content);

			fromText.setText(null);
			toText.setText(null);
			subjectText.setText(null);
			contentText.setText(null);

			JOptionPane
					.showMessageDialog(
							null,
							"Message Producer has successfuly sent an email to the queue ",
							"Message Producer", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
