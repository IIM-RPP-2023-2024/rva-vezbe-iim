package rvavezbeiim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.service.PorudzbinaService;

@RestController
public class PorudzbinaController {

	@Autowired
	private PorudzbinaService porudzbinaService;
	
	@GetMapping("/placenePorudzbine")
	public ResponseEntity<List<Porudzbina>> placenePorudzbine() {
		List<Porudzbina> porudzbinas = porudzbinaService.findByPlacenoTrue();
		return new ResponseEntity<>(porudzbinas, HttpStatus.OK);
	}
}
