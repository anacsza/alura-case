package br.com.alura.sender;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	private static final Logger LOGGER = getLogger(EmailSender.class);

	public void send(String recipientEmail, String subject, String body) {
		LOGGER.info("Simulating sending email to [%s]:".formatted(recipientEmail));
		LOGGER.info("Subject: %s Body: %s".formatted(subject, body));
	}
}
