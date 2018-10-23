package be.vdab.groenetenen.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import be.vdab.groenetenen.entities.Tender;
import be.vdab.groenetenen.exceptions.UnableToSendMailException;

@Component
public class MailSenderDefault implements MailSender {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(MailSenderDefault.class);
	
	private final JavaMailSender sender;
	private final String emailAddressWebmaster;
	
	public MailSenderDefault(
			final JavaMailSender sender,
			@Value("${emailAddressWebmaster}")
				final String emailAddressWebmaster) {
		this.sender = sender;
		this.emailAddressWebmaster = emailAddressWebmaster;
	}

	@Override
	@Async
	public void newTender(final Tender tender, final String tenderURL) {
		try {
//			final SimpleMailMessage message = new SimpleMailMessage();
//			
//			message.setTo(tender.getEmailAdress());
//			message.setSubject("Green toes - New Tender");
//			message.setText("Your tender has number " + tender.getId());
//			
//			sender.send(message);
			final MimeMessage message = sender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setTo(tender.getEmailAddress());
			helper.setSubject("New tender from Green Toes");
			
			helper.setText("Your tender has number <strong>" + tender.getId() +
					"</strong>. You can access your tender details " +
					"<a href='" + tenderURL + tender.getId() + "'>here</a>.",
					true);
			
			sender.send(message);
		}
		catch (final MessagingException | MailException ex) {
			final String error = "Can't send new tender mail";
			LOGGER.error(error, ex);
			
			throw new UnableToSendMailException(error, ex);
		}
	}

	@Override
	public void countTendersMail(final long count) {
		try {
			final MimeMessage message = sender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setTo(emailAddressWebmaster);
			helper.setSubject("Tender count");
			
			helper.setText("Number of tenders:<strong>" + count + "</strong",
					true);
			
			sender.send(message);
		}
		catch (final MessagingException | MailException ex) {
			final String error = "Can't send tender count mail";
			LOGGER.error(error, ex);
			
			throw new UnableToSendMailException(error, ex);
		}
	}
}
