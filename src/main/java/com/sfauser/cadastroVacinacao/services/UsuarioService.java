package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sfauser.cadastroVacinacao.entities.Usuario;
import com.sfauser.cadastroVacinacao.repositories.UsuarioRepository;
import com.sfauser.cadastroVacinacao.services.exceptions.DatabaseException;
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

	public Usuario insert(Usuario userObj1) {
		updateData1(userObj1);
		return repository.save(userObj1);
	}

	private void updateData1(Usuario userObj1) {

		if (userObj1.getNome() == null || userObj1.getEmail() == null || userObj1.getCpf() == null
				|| userObj1.getDataNascimento() == null) {
			throw new DatabaseException("Não foram preenchidos todos os campos do cadastro");
		} else {
			if (ValidaCPF.isCPF(userObj1.getCpf()) == false) {
				throw new DatabaseException("CPF Invalido");
			}else {
				if (ValidaEmail.isValidEmailAddressRegex(userObj1.getEmail()) == false) {
					throw new DatabaseException("E-mail Invalido");
		}
	}
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Usuario update(Long id, Usuario userObj) {
		try {
			Usuario databaseObj = repository.getOne(id);
			updateData(databaseObj, userObj);
			return repository.save(databaseObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Usuario databaseObj, Usuario userObj) {

		if (userObj.getNome() != null) {
			databaseObj.setNome(userObj.getNome());
		}
		if (userObj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userObj.getEmail())!= false) {
			databaseObj.setEmail(userObj.getEmail());
		}
		if (userObj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userObj.getEmail()) == false) {
			throw new DatabaseException("E-mail invalido");
		}
		if (userObj.getCpf() != null && ValidaCPF.isCPF(userObj.getCpf()) != false) {
			databaseObj.setCpf(userObj.getCpf());
		}
		if (userObj.getCpf() != null && ValidaCPF.isCPF(userObj.getCpf()) == false) {
			throw new DatabaseException("CPF invalido");
		}
		
		if (userObj.getDataNascimento() != null) {
			databaseObj.setDataNascimento(userObj.getDataNascimento());
		}
	}
}
