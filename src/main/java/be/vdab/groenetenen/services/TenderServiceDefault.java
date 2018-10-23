package be.vdab.groenetenen.services;

import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Tender;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.repositories.TenderRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class TenderServiceDefault implements TenderService {

	private final TenderRepository tenderRepository;
	private final MailSender mailSender;
	
	public TenderServiceDefault(
			final TenderRepository tenderRepository,
			final MailSender mailSender) {
		this.tenderRepository = tenderRepository;
		this.mailSender = mailSender;
	}
	
	@Override
	public Optional<Tender> read(long id) {
		return tenderRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(final Tender tender, final String tenderURL) {
		tenderRepository.save(tender);
		
		
		mailSender.newTender(tender, tenderURL);
	}

	@Override
	// Test every minute
	@Scheduled(/*cron = "0 0/1 * 1/1 * ? * "*/ fixedRate=60000)
	public void countTendersMail() {
		mailSender.countTendersMail(tenderRepository.count());
	}

}
