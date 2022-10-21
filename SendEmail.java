package mq.mess;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	final String senderEmail = "armin.saric99@gmail.com";
	final String senderPassword = "";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String to = null;
	static String subject;
	static String content;

	public SendEmail(String to, String subject, String content) {
		this.to = to;
		this.subject = subject;
		this.content = content;

		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmail);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketfactory.fallback", "false");

		SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(Constants.CONTENT);
			msg.setSubject(Constants.SUBJECT);
			msg.setFrom(new InternetAddress(senderEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					Constants.TO));
			Transport.send(msg);
			System.out.println("Poruka je uspješno poslana!");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmail, senderPassword);
		}
	}

}
