package com.sfauser.cadastroVacinacao.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import com.sfauser.cadastroVacinacao.entities.AplicacaoVacina;
import com.sfauser.cadastroVacinacao.services.VacinaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/vacinas")
public class VacinaResource {

	@Autowired
	private VacinaService service;

	@GetMapping
	public ResponseEntity<List<AplicacaoVacina>> findAll() {
		List<AplicacaoVacina> list = service.FindAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AplicacaoVacina> findById(@PathVariable Long id) {
		AplicacaoVacina obj = service.FindById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<AplicacaoVacina> insert(@RequestBody AplicacaoVacina obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
		buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AplicacaoVacina> update(@PathVariable Long id, @RequestBody AplicacaoVacina obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}