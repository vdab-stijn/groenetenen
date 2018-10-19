package be.vdab.groenetenen.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.groenetenen.entities.Branch;
import be.vdab.groenetenen.valueobjects.Address;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BranchRepositoryTest
extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private BranchRepository repository;
	
	private static final String BRANCHES = "filialen";
	
	@Test
	public void create() {
		final Branch branch = new Branch();
		
		branch.setName("test");
		branch.setAddress(
				new Address("street", "number", 1000, "municipality"));
		branch.setBuildingValue(BigDecimal.ZERO);
		branch.setDateCommissioning(LocalDate.now());
		
		final int count = super.countRowsInTable(BRANCHES);
		
		repository.save(branch);
		
		assertEquals(count + 1, super.countRowsInTable(BRANCHES));
		assertNotEquals(0, branch.getId());
		assertEquals(1, 
				super.countRowsInTableWhere(BRANCHES, "id=" + branch.getId()));
	}
}
