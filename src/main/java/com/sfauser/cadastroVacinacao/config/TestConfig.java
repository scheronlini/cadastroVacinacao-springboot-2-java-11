package com.sfauser.cadastroVacinacao.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sfauser.cadastroVacinacao.entities.Usuario;
import com.sfauser.cadastroVacinacao.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner  {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario a = new Usuario(null, "aaaa", "nuldddl", "nueeell", "jjeejj");
		Usuario b = new Usuario(null, "eeeee", "nulffffl", "nuaaall", "jeejjj");
		Usuario c = new Usuario(null, "ffff", "nujjjll", "nuuuull", "jjjaaj");
		
		
		usuarioRepository.saveAll(Arrays.asList(a,b,c));
	}
	
	
	
	

}
