package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfauser.cadastroVacinacao.entities.Usuario;
import com.sfauser.cadastroVacinacao.repositories.UsuarioRepository;
import com.sfauser.cadastroVacinacao.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> FindAll() {
		return repository.findAll();
	}
	
	public Usuario FindById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario insert(Usuario obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Usuario update(Long id, Usuario userObj) {
		Usuario databaseObj = repository.getOne(id);
		updateData(databaseObj, userObj);
		return repository.save(databaseObj);
	}

	private void updateData(Usuario databaseObj, Usuario userObj) {
		
		if (userObj.getNome() != null) {
			databaseObj.setNome(userObj.getNome());
		}
		if (userObj.getEmail() != null) {
			databaseObj.setEmail(userObj.getEmail());
		}
		if (userObj.getCpf() != null) {
			databaseObj.setCpf(userObj.getCpf());
		}
		if (userObj.getDataNascimento() != null) {
			databaseObj.setDataNascimento(userObj.getDataNascimento());
		}
	}
}
