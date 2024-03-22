package rvavezbeiim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvavezbeiim.model.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer>{
	
	List<Dobavljac> findByAdresaContainingIgnoreCase(String adresa);

}
