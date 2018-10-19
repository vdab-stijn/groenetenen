package be.vdab.groenetenen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.groenetenen.entities.Tender;

public interface TenderRepository extends JpaRepository<Tender, Long> {

}
