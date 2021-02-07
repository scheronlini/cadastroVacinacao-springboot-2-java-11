package com.sfauser.cadastroVacinacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfauser.cadastroVacinacao.entities.Vacina;
import com.sfauser.cadastroVacinacao.repositories.VacinaRepository;

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
}
