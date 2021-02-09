package com.sfauser.cadastroVacinacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfauser.cadastroVacinacao.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
