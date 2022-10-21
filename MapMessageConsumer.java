package mq.mess;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.swing.JOptionPane;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MapMessageConsumer {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL : tcp://localhost:61616"
	private static String jmsQueue = "new_QUEUE";

	public static void main(String[] args) throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
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
		JOptionPane.showMessageDialog(null,
				"Message Consumer je poslao emial ", "Message Consumer",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
