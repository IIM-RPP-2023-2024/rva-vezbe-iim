package rvavezbeiim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.repository.PorudzbinaRepository;

@Service
public class PorudzbinaService {

	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	public Optional<Porudzbina> findById(Integer id) {
		return porudzbinaRepository.findById(id);
	}
	
	public List<Porudzbina> findByPlacenoTrue() {
		return porudzbinaRepository.findByPlacenoTrue();
	}
}
