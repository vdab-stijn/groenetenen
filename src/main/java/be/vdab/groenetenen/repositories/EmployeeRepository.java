package be.vdab.groenetenen.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.groenetenen.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Override
	@EntityGraph(Employee.WITH_BRANCH)
	List<Employee> findAll(Sort sort);
	
	@Override
	@EntityGraph(Employee.WITH_BRANCH)
	Page<Employee> findAll(Pageable pageable);
	
	@EntityGraph(Employee.WITH_BRANCH)
	Optional<Employee> findBySocialSecurityNumber(
			final long socialSecurityNumber);
}
