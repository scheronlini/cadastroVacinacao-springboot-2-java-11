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
}
