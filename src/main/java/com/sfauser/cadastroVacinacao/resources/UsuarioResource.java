package com.sfauser.cadastroVacinacao.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sfauser.cadastroVacinacao.entities.Usuario;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@GetMapping
	public ResponseEntity<Usuario> findAll() {
	Usuario u = new Usuario(32232L, "ndsffdull", "null", "null", null);
	return ResponseEntity.ok().body(u);
	
	}
}

