package rvavezbeiim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.model.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer> {

	// strani kljuc
	List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina);
	List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena);
	
}
