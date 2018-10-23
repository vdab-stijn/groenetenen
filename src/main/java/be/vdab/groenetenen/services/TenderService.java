package be.vdab.groenetenen.services;

import java.util.Optional;

import be.vdab.groenetenen.entities.Tender;

public interface TenderService {

	Optional<Tender> read(final long id);
	void create(final Tender tender, final String tenderURL);
	void countTendersMail();
}
