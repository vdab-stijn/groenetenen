package be.vdab.groenetenen.services;

import java.util.List;

import be.vdab.groenetenen.entities.Branch;

public interface BranchService {

	List<Branch> findByPostalCode(final int from, final int to);
}
