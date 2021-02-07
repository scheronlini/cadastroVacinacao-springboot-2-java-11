package com.sfauser.cadastroVacinacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfauser.cadastroVacinacao.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
