package com.sfauser.cadastroVacinacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfauser.cadastroVacinacao.entities.AplicacaoVacina;

public interface AplicacaoVacinaRepository extends JpaRepository<AplicacaoVacina, Long> {
}
