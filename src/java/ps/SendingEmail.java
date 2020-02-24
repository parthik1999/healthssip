/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Parthik Shah
 */
public class SendingEmail {
    public static boolean sendMail(String email, String subject, String content, String fromEmail,
			String fromPassword) {

		java.util.Properties properties = new java.util.Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		javax.mail.Session mailSession = javax.mail.Session.getInstance(properties);
                boolean val = false;
		try {
			MimeMessage message = new MimeMessage(mailSession);

			message.setContent(content, "text/html");
			message.setSubject(subject);

			InternetAddress sender = new InternetAddress(fromEmail, "BISAG");
			InternetAddress receiver = new InternetAddress(email);
			message.setFrom(sender);
			message.setRecipient(Message.RecipientType.TO, receiver);
			message.saveChanges();

			javax.mail.Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", 587, fromEmail, fromPassword);
                        val = transport.isConnected();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
                        return val;

		} catch (Exception e) {
			e.printStackTrace();
                        return val;
		}
                          
	}


    
}
