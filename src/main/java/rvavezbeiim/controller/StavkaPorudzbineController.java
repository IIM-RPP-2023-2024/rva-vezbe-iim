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
	
    @GetMapping("stavkaPorudzbine")
    public ResponseEntity<List<StavkaPorudzbine>> getAll() {
        List<StavkaPorudzbine> stavkaPorudzbines = stavkaPorudzbineService.getAll();
        return new ResponseEntity<>(stavkaPorudzbines, HttpStatus.OK);
    }
    
    @GetMapping("stavkaPorudzbine/{id}")
    public ResponseEntity<StavkaPorudzbine> getOne(@PathVariable("id") Integer id) {
        if (stavkaPorudzbineService.findById(id).isPresent()) {
            Optional<StavkaPorudzbine> stavkaPorudzbineOpt = stavkaPorudzbineService.findById(id);
            return new ResponseEntity<>(stavkaPorudzbineOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
	
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
	
    @PostMapping("stavkaPorudzbine")
    public ResponseEntity<StavkaPorudzbine> addOne(@RequestBody StavkaPorudzbine stavkaPorudzbine) {
        if (!porudzbinaService.existsById(stavkaPorudzbine.getPorudzbina().getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        StavkaPorudzbine savedStavkaPorudzbine = stavkaPorudzbineService.save(stavkaPorudzbine);
        URI location = URI.create("/stavkaPorudzbine/" + savedStavkaPorudzbine.getId());
        return ResponseEntity.created(location).body(savedStavkaPorudzbine);
    }

   @PutMapping("stavkaPorudzbine/{id}")
    public ResponseEntity<StavkaPorudzbine> updateOne(@RequestBody StavkaPorudzbine stavkaPorudzbine,
            @PathVariable("id") Integer id) {
        if (!stavkaPorudzbineService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stavkaPorudzbine.setId(id);
        StavkaPorudzbine savedStavkaPorudzbine = stavkaPorudzbineService.save(stavkaPorudzbine);
        return ResponseEntity.ok().body(savedStavkaPorudzbine);
    }

   @DeleteMapping("stavkaPorudzbine/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {


        if (stavkaPorudzbineService.existsById(id)) {
            stavkaPorudzbineService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

}
