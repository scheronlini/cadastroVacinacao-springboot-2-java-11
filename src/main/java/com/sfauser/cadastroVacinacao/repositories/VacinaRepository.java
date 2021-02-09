package com.sfauser.cadastroVacinacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfauser.cadastroVacinacao.entities.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}
