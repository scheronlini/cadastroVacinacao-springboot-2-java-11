package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfauser.cadastroVacinacao.entities.Usuario;
import com.sfauser.cadastroVacinacao.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> FindAll() {
		return repository.findAll();
	}
	
	public Usuario FindById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.get();
	}
}
