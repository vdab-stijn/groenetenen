package be.vdab.groenetenen.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Branch;
import be.vdab.groenetenen.exceptions.BranchHasEmployeesException;
import be.vdab.groenetenen.repositories.BranchRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class BranchServiceDefault implements BranchService {

	private final BranchRepository branchRepository;
	
	public BranchServiceDefault(final BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}
	
	@Override
	public List<Branch> findAll() {
		return branchRepository.findAll();
	}
	
	@Override
	public List<Branch> findByPostalCode(int from, int to) {
		return branchRepository
			.findByAddressPostalCodeBetweenOrderByAddressPostalCode(from, to);
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(final Branch branch) {
		branchRepository.save(branch);
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(final Branch branch) {
		branchRepository.save(branch);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void delete(final long id) {
		final Optional<Branch> branch = branchRepository.findById(id);
		
		if (branch.isPresent()) {
			if (!branch.get().getEmployees().isEmpty())
				throw new BranchHasEmployeesException();
		}
		
		branchRepository.deleteById(id);
	}
}
