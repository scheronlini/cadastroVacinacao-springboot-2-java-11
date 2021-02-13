package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sfauser.cadastroVacinacao.entities.Paciente;
import com.sfauser.cadastroVacinacao.entities.AplicacaoVacina;
import com.sfauser.cadastroVacinacao.repositories.PacienteRepository;
import com.sfauser.cadastroVacinacao.services.exceptions.DatabaseException;
import com.sfauser.cadastroVacinacao.services.exceptions.ResourceNotFoundException;

import util.ValidaCPF;
import util.ValidaEmail;
import util.ValidaNome;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;

	public List<Paciente> FindAll() {
		return repository.findAll();
	}

	public Paciente FindById(Long id) {
		Optional<Paciente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Paciente insert(Paciente userObj1) {
		try {
			insertData(userObj1);
			return repository.save(userObj1);

		} catch (DataIntegrityViolationException o) {
			throw new DatabaseException("E-mail ou CPF já foram cadastrados para outro paciente");
		}
	}


	private void insertData(Paciente userObj1) {

		if (userObj1.getNome() == null || userObj1.getEmail() == null || userObj1.getCpf() == null
				|| userObj1.getDataNascimento() == null) {
			throw new DatabaseException("Não foram preenchidos todos os campos do cadastro");
		} else {
			if (ValidaCPF.isCPF(userObj1.getCpf()) == false) {
				throw new DatabaseException("CPF Invalido");
			} else {
				if (ValidaEmail.isValidEmailAddressRegex(userObj1.getEmail()) == false) {
					throw new DatabaseException("E-mail Invalido");
				} else {
					if (ValidaNome.isNome(userObj1.getNome()) == false) {
						throw new DatabaseException("Nome Invalido");
					}
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

	public Paciente update(Long id, Paciente userObj) {
		try {
			Paciente databaseObj = repository.getOne(id);
			updateData(databaseObj, userObj);
			return repository.save(databaseObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Paciente databaseObj, Paciente userObj) {

		if (userObj.getNome() != null && ValidaNome.isNome(userObj.getNome()) != false) {
			databaseObj.setNome(userObj.getNome());
		}
		if (userObj.getNome() != null && ValidaNome.isNome(userObj.getNome()) == false) {
			throw new DatabaseException("Nome invalido");
		}
		if (userObj.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(userObj.getEmail()) != false) {
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
