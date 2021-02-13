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
import com.sfauser.cadastroVacinacao.entities.AplicacaoVacina;
import com.sfauser.cadastroVacinacao.repositories.AplicacaoVacinaRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class VacinaService {

	@Autowired
	private AplicacaoVacinaRepository repository;

	public List<AplicacaoVacina> FindAll() {
		return repository.findAll();
	}

	public AplicacaoVacina FindById(Long id) {
		Optional<AplicacaoVacina> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public AplicacaoVacina insert(AplicacaoVacina userObj) {
		try {
			return repository.save(userObj);
		} catch (DataIntegrityViolationException o) {
			throw new DatabaseException("E-mail ja foi cadastrado para aplicação outra vacina");
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

	public AplicacaoVacina update(Long id, AplicacaoVacina userObj) {
		try {
			AplicacaoVacina dataBaseObj = repository.getOne(id);
			updateData(dataBaseObj, userObj);
			return repository.save(dataBaseObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(AplicacaoVacina databaseObj, AplicacaoVacina userObj) {
		
	}
}
