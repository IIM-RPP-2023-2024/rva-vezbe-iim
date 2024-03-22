package rvavezbeiim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvavezbeiim.model.Artikl;
import rvavezbeiim.repository.ArtiklRepository;

@Service
public class ArtiklService {
	
	@Autowired
	private ArtiklRepository artiklRepository;
	
	public List<Artikl> getAll() {
		return artiklRepository.findAll();
	}
	
	public Optional<Artikl> findById(Integer id){
		return artiklRepository.findById(id);
	}
	
	public List<Artikl> findByNazivContainingIgnoreCase(String naziv){
		return artiklRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public Artikl save (Artikl artikl) {
		return artiklRepository.save(artikl);
	}
	
	public boolean existById(Integer id) {
		return artiklRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		artiklRepository.deleteById(id);
	}

}
