package rvavezbeiim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvavezbeiim.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer>{
	
	List<Porudzbina> findByPlacenoTrue();

}
