package be.vdab.groenetenen.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Employee;
import be.vdab.groenetenen.repositories.EmployeeRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class EmployeeServiceDefault implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceDefault(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Page<Employee> findAll(final Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
	public Optional<Employee> findBySocialSecurityNumber(
			final long socialSecurityNumber) {
		return employeeRepository.findBySocialSecurityNumber(
				socialSecurityNumber);
				
	}
}
