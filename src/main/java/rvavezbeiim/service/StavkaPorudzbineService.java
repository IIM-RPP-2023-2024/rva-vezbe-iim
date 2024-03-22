package rvavezbeiim.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.model.StavkaPorudzbine;
import rvavezbeiim.repository.StavkaPorudzbineRepository;

@Service
public class StavkaPorudzbineService {

	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
    public List<StavkaPorudzbine> getAll() {
        return stavkaPorudzbineRepository.findAll();
    }

    public List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina) {
        return stavkaPorudzbineRepository.findByPorudzbina(porudzbina);
    }

    public Optional<StavkaPorudzbine> findById(Integer id) {
        return stavkaPorudzbineRepository.findById(id);
    }
	
	public List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena) {
		return stavkaPorudzbineRepository.findByCenaLessThanOrderById(cena);
	}
	
	public List<StavkaPorudzbine> findByForeignKeyPorudzbina(Porudzbina porudzbina) {
		return stavkaPorudzbineRepository.findByPorudzbina(porudzbina);
	}
	
    public StavkaPorudzbine save(StavkaPorudzbine stavkaPorudzbine) {
        return stavkaPorudzbineRepository.save(stavkaPorudzbine);
    }

    public boolean existsById(Integer id) {
        return stavkaPorudzbineRepository.existsById(id);
    }

    public void deleteById(Integer id) {
        stavkaPorudzbineRepository.deleteById(id);
    }
}
