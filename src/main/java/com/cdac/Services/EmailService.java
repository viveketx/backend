package com.cdac.Services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmail(String subject, String message, String to) {
		final String username = "gymmanagement175@gmail.com";
		final String password = "baqcritplrhlwcsl";

		Properties properties = System.getProperties();
		//System.out.println("PROPERTIES" + properties);

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage m = new MimeMessage(session);
			m.setFrom(new InternetAddress("Team"));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			Transport.send(m);
			//System.out.println("Sent Success.....");
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
