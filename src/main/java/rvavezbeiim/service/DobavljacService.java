package rvavezbeiim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvavezbeiim.model.Dobavljac;
import rvavezbeiim.repository.DobavljacRepository;

@Service
public class DobavljacService {
	
	@Autowired
	private DobavljacRepository dobavljacRepository;
	
	public List<Dobavljac> getAll(){
		return dobavljacRepository.findAll();
	}
	
	public Optional<Dobavljac> findById(Integer id) {
		return dobavljacRepository.findById(id);
	}
	
	public List<Dobavljac> findByNazivContainingIgnoreCase(String naziv) {
        return dobavljacRepository.findByAdresaContainingIgnoreCase(naziv);
    }
	
	public Dobavljac save(Dobavljac dobavljac) {
		return dobavljacRepository.save(dobavljac);
	}
	
	public boolean existsById(Integer id) {
		return dobavljacRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		dobavljacRepository.deleteById(id);
	}
}