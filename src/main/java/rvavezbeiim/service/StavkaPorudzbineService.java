package rvavezbeiim.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.model.StavkaPorudzbine;
import rvavezbeiim.repository.StavkaPorudzbineRepository;

@Service
public class StavkaPorudzbineService {

	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
	public List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena) {
		return stavkaPorudzbineRepository.findByCenaLessThanOrderById(cena);
	}
	
	public List<StavkaPorudzbine> findByForeignKeyPorudzbina(Porudzbina porudzbina) {
		return stavkaPorudzbineRepository.findByPorudzbina(porudzbina);
	}
}
