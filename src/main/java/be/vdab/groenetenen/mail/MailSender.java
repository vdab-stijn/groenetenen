package be.vdab.groenetenen.mail;

import be.vdab.groenetenen.entities.Tender;

public interface MailSender {

	void newTender(final Tender tender, final String tenderURL);
}
