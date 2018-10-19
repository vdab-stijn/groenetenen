package be.vdab.groenetenen.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Tender;
import be.vdab.groenetenen.repositories.TenderRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class TenderServiceDefault implements TenderService {

	private final TenderRepository tenderRepository;
	
	public TenderServiceDefault(final TenderRepository tenderRepository) {
		this.tenderRepository = tenderRepository;
	}
	
	@Override
	public Optional<Tender> read(long id) {
		return tenderRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Tender tender) {
		tenderRepository.save(tender);
	}

}
