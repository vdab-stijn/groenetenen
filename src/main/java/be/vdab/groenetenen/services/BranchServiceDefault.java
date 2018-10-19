package be.vdab.groenetenen.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Branch;
import be.vdab.groenetenen.repositories.BranchRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class BranchServiceDefault implements BranchService {

	private final BranchRepository branchRepository;
	
	public BranchServiceDefault(final BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}
	
	@Override
	public List<Branch> findByPostalCode(int from, int to) {
		return branchRepository
			.findByAddressPostalCodeBetweenOrderByAddressPostalCode(from, to);
	}
}
