package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import com.sfauser.cadastroVacinacao.services.exceptions.DatabaseException;
import com.sfauser.cadastroVacinacao.services.exceptions.ResourceNotFoundException;

import util.ValidaEmail;
import util.ValidaNome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sfauser.cadastroVacinacao.entities.Paciente;
import com.sfauser.cadastroVacinacao.entities.Vacina;
import com.sfauser.cadastroVacinacao.repositories.VacinaRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class VacinaService {

	@Autowired
	private VacinaRepository repository;

	public List<Vacina> FindAll() {
		return repository.findAll();
	}

	public Vacina FindById(Long id) {
		Optional<Vacina> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
		
	public Vacina insert(Vacina userObj) {
		try {
			insertData(userObj);
			return repository.save(userObj);
		} catch (DataIntegrityViolationException o) {
			throw new DatabaseException(o.getMessage());
		}
	}

	private void insertData(Vacina userObj) {
		if (ValidaEmail.isValidEmailAddressRegex(userObj.getEmail()) == false) {
			throw new DatabaseException("E-mail Invalido");
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

	public Vacina update(Long id, Vacina vac) {
		try {
			Vacina entity = repository.getOne(id);
			updateData(entity, vac);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Vacina databasevac, Vacina userobj) {
		if (userobj.getNomeVacina() != null ){
			databasevac.setNomeVacina(userobj.getNomeVacina());
		}
		if (userobj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userobj.getEmail()) != false) {
			databasevac.setEmail(userobj.getEmail());
		}
		if (userobj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userobj.getEmail()) == false) {
			throw new DatabaseException("E-mail invalido");
		}
	}
}
