package be.vdab.groenetenen.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import be.vdab.groenetenen.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	List<Branch> findByAddressPostalCodeBetweenOrderByAddressPostalCode(
			final int from, final int to);
	
	List<Branch> findWithHighestBuildingValue();
	
	BigDecimal findAverageBuildingValueInMunicipality(
			@Param("municipality") final String municipality);
}
