package rvavezbeiim.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rvavezbeiim.model.Artikl;
import rvavezbeiim.service.ArtiklService;

@RestController
public class ArtiklController {
	
	@Autowired
	private ArtiklService artiklService;
	
	@GetMapping("/artikl")
	public List<Artikl> getAll() {
		return artiklService.getAll();
	}
	
	@GetMapping("/artikl/{id}")
	public ResponseEntity<Artikl> getOne(@PathVariable("id") Integer id) {
		if(artiklService.existById(id)) {
			Optional<Artikl> artikl = artiklService.findById(id);
			return new ResponseEntity<>(artikl.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/artikl/naziv/{naziv}")
	public ResponseEntity<List<Artikl>> getByNaziv(@PathVariable("naziv") String naziv) {
		List<Artikl> artikls = artiklService.findByNazivContainingIgnoreCase(naziv);
		return new ResponseEntity<>(artikls, HttpStatus.OK);
	}
	
	@PostMapping("/artikl")
	public ResponseEntity<Artikl> addArtikl(@RequestBody Artikl artikl) {
		Artikl savedArtikl = artiklService.save(artikl);
		URI location = URI.create("/artikl/" + savedArtikl.getId());
		return ResponseEntity.created(location).body(savedArtikl);
	}
	
	@PutMapping("/artikl/{id}")
	public ResponseEntity<Artikl> updateArtikl(@RequestBody Artikl artikl, @PathVariable("id") Integer id) {
		if(artiklService.existById(id)) {
			artikl.setId(id);
			Artikl savedArtikl = artiklService.save(artikl);
			return ResponseEntity.ok().body(savedArtikl);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/artikl/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){
		if(artiklService.existById(id)) {
			artiklService.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
}
