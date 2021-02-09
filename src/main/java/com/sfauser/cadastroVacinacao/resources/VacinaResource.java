package com.sfauser.cadastroVacinacao.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import com.sfauser.cadastroVacinacao.entities.Vacina;
import com.sfauser.cadastroVacinacao.services.VacinaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/vacinas")
public class VacinaResource {

	@Autowired
	private VacinaService service;

	@GetMapping
	public ResponseEntity<List<Vacina>> findAll() {
		List<Vacina> list = service.FindAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vacina> findById(@PathVariable Long id) {
		Vacina obj = service.FindById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Vacina> insert(@RequestBody Vacina vac) {
		vac = service.insert(vac);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vac.getId()).toUri();
		return ResponseEntity.created(uri).body(vac);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Vacina> update(@PathVariable Long id, @RequestBody Vacina vac) {
		vac = service.update(id, vac);
		return ResponseEntity.ok().body(vac);
	}
}