package com.sfauser.cadastroVacinacao.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfauser.cadastroVacinacao.entities.Vacina;
import com.sfauser.cadastroVacinacao.services.VacinaService;


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
}

