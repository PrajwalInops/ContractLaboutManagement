package com.inops.visitorpass.mail;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	private final JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmailWithAttachmentAndLink(String to, String subject, String body, byte[] attachment,
			String attachmentFilename, String linkUrl, String linkText) throws MessagingException, IOException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("vamshi@inops.tech");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body + "\n\n" + linkText + ": " + linkUrl, true);
				
		helper.addAttachment(attachmentFilename, new ByteArrayResource(attachment));

		javaMailSender.send(message);
	}
}
