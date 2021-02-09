package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import com.sfauser.cadastroVacinacao.services.exceptions.DatabaseException;
import com.sfauser.cadastroVacinacao.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
		return obj.get();
	}

	public Vacina insert(Vacina vac) {
		return repository.save(vac);
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

	private void updateData(Vacina databasevac, Vacina vac) {
		if (vac.getNomeVacina() != null && ValidaNome.isNome(vac.getNomeVacina()) != false) {
			databasevac.setNomeVacina(vac.getNomeVacina());
		}
		if (vac.getNomeVacina() != null && ValidaNome.isNome(vac.getNomeVacina()) == false) {
			throw new DatabaseException("Nome invalido");
		}
		if (vac.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(vac.getEmail()) != false) {
			databasevac.setEmail(vac.getEmail());
		}
		if (vac.getEmail() != null && ValidaEmail.isValidEmailAddressRegex(vac.getEmail()) == false) {
			throw new DatabaseException("E-mail invalido");
		}
	}
}
