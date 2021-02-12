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
			throw new DatabaseException("E-mail ja foi cadastrado para outra vacina");
		}
	}

	private void insertData(Vacina userObj) {
		if (ValidaEmail.isValidEmailAddressRegex(userObj.getEmail()) == false) {
			throw new DatabaseException("E-mail Invalido");
		}
		if (userObj.getNomeVacina() == null || userObj.getEmail() == null || userObj.getDataVacina() == null) {
			throw new DatabaseException("Não foram preenchidos todos os campos do cadastro");
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

	public Vacina update(Long id, Vacina userObj) {
		try {
			Vacina dataBaseObj = repository.getOne(id);
			updateData(dataBaseObj, userObj);
			return repository.save(dataBaseObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Vacina databaseObj, Vacina userObj) {
		if (userObj.getNomeVacina() != null) {
			databaseObj.setNomeVacina(userObj.getNomeVacina());
		}
		if (userObj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userObj.getEmail()) != false) {
			databaseObj.setEmail(userObj.getEmail());
		}
		if (userObj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userObj.getEmail()) == false) {
			throw new DatabaseException("E-mail invalido");
		}
		if (userObj.getDataVacina() != null) {
			databaseObj.setDataVacina(userObj.getDataVacina());
		}
	}
}
