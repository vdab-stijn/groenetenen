package be.vdab.groenetenen.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import be.vdab.groenetenen.entities.Employee;

public interface EmployeeService {

	Page<Employee> findAll(Pageable pageable);
	
	Optional<Employee> findBySocialSecurityNumber(
			final long socialSecurityNumber);
}
