package rvavezbeiim.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rvavezbeiim.model.Porudzbina;
import rvavezbeiim.service.PorudzbinaService;

@RestController
public class PorudzbinaController {
	
    @Autowired
    private PorudzbinaService porudzbinaService;


    @GetMapping("porudzbina")
    public ResponseEntity<List<Porudzbina>> getAll() {
        List<Porudzbina> porudzbinas = porudzbinaService.getAll();
        return new ResponseEntity<>(porudzbinas, HttpStatus.OK);
    }
    
   @GetMapping("porudzbina/{id}")
    public ResponseEntity<Porudzbina> getOne(@PathVariable("id") Integer id) {
        if (porudzbinaService.findById(id).isPresent()) {
            Optional<Porudzbina> porudzbinaOpt = porudzbinaService.findById(id);
            return new ResponseEntity<>(porudzbinaOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("placenePorudzbine")
    public ResponseEntity<List<Porudzbina>> placenePorudzbine() {
        List<Porudzbina> porudzbinas = porudzbinaService.findByPlacenoTrue();
        return new ResponseEntity<>(porudzbinas, HttpStatus.OK);
    }

   @PostMapping("porudzbina")
    public ResponseEntity<Porudzbina> addPorudzbina(@RequestBody Porudzbina porudzbina) {
        Porudzbina savedPorudzbina = porudzbinaService.save(porudzbina);
        URI location = URI.create("/porudzbina/" + savedPorudzbina.getId());
        return ResponseEntity.created(location).body(savedPorudzbina);
    }

   @PutMapping(value = "porudzbina/{id}")
    public ResponseEntity<Porudzbina> updatePorudzbina(@RequestBody Porudzbina porudzbina,
            @PathVariable("id") Integer id) {
        if (porudzbinaService.existsById(id)) {
            porudzbina.setId(id);
            Porudzbina savedPorudzbina = porudzbinaService.save(porudzbina);
            return ResponseEntity.ok().body(savedPorudzbina);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("porudzbina/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {

        if (porudzbinaService.existsById(id)) {
            porudzbinaService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }



}