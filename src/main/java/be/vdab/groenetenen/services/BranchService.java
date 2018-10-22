package be.vdab.groenetenen.services;

import java.util.List;

import be.vdab.groenetenen.entities.Branch;

public interface BranchService {

	List<Branch> findAll();
	List<Branch> findByPostalCode(final int from, final int to);
	
	void create(final Branch branch);
	void update(final Branch branch);
	void delete(final long id);
}
