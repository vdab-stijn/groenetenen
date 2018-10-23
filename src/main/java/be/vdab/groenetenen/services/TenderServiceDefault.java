package be.vdab.groenetenen.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Tender;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.messaging.TenderAndTenderURL;
import be.vdab.groenetenen.repositories.TenderRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class TenderServiceDefault implements TenderService {

	private final TenderRepository tenderRepository;
	private final MailSender mailSender;
	
	private final JmsTemplate jmsTemplate;
	private final String newTenderQueue;
	
	public TenderServiceDefault(
			final TenderRepository tenderRepository,
			final MailSender mailSender,
			final JmsTemplate jmsTemplate,
			@Value("${newTenderQueue}") final String newTenderQueue) {
		this.tenderRepository = tenderRepository;
		this.mailSender = mailSender;
		
		this.jmsTemplate = jmsTemplate;
		this.newTenderQueue = newTenderQueue;
	}
	
	@Override
	public Optional<Tender> read(long id) {
		return tenderRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(final Tender tender, final String tenderURL) {
		tenderRepository.save(tender);
		
		final TenderAndTenderURL tenderAndTenderURL
		= new TenderAndTenderURL(tender, tenderURL);
		
		//mailSender.newTender(tender, tenderURL);
		jmsTemplate.convertAndSend(newTenderQueue, tenderAndTenderURL);
	}

	@Override
	// Test every minute
	@Scheduled(cron = "0 0 12 1/1 * * "/* fixedRate=60000*/)
	public void countTendersMail() {
		mailSender.countTendersMail(tenderRepository.count());
	}

}
