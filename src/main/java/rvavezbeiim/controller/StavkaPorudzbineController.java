package rvavezbeiim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.model.StavkaPorudzbine;
import rvavezbeiim.service.PorudzbinaService;
import rvavezbeiim.service.StavkaPorudzbineService;

@RestController
public class StavkaPorudzbineController {

	@Autowired
	private StavkaPorudzbineService stavkaPorudzbineService;
	
	@Autowired
	private PorudzbinaService porudzbinaService;
	
	@GetMapping("/stavkaPorudzbine/Cena/{cena}")
	public ResponseEntity<List<StavkaPorudzbine>> getStavkaPorudzbineCena(@PathVariable("cena") double cena) {
		List<StavkaPorudzbine> stavkaPorudzbines = stavkaPorudzbineService.findByCenaLessThanOrderById(cena);
		return new ResponseEntity<>(stavkaPorudzbines, HttpStatus.OK);
	}
	
	@GetMapping("/stavkeZaPorudzbinu/{id}")
	public ResponseEntity<List<StavkaPorudzbine>> getAllForPorudzbina(@PathVariable("id") Integer id) {
		Optional<Porudzbina> porudzbina = porudzbinaService.findById(id);
		if(porudzbina.isPresent()) {
			List<StavkaPorudzbine> stavkaPorudzbines = stavkaPorudzbineService.findByForeignKeyPorudzbina(porudzbina.get());
			return new ResponseEntity<>(stavkaPorudzbines, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
