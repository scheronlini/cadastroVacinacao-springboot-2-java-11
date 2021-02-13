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
public class AplicacaoVacinaService {

	@Autowired
	private AplicacaoVacinaRepository repository;

	public AplicacaoVacina insert(AplicacaoVacina userObj) {
		try {
		return repository.save(userObj);
		} catch (DataIntegrityViolationException o) {
			throw new DatabaseException("E-mail ja foi cadastrado para outra vacina");
		}
	}

	}