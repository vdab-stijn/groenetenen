package be.vdab.groenetenen.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import be.vdab.groenetenen.mail.MailSender;

@Component
public class NewTenderListener {

	private final MailSender mailSender;
	
	public NewTenderListener(final MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@JmsListener(destination = "${newTenderQueue}")
	public void receiveMessage(final TenderAndTenderURL tenderAndTenderURL) {
		mailSender.newTender(
				tenderAndTenderURL.getTender(),
				tenderAndTenderURL.getTenderURL());
	}
}
