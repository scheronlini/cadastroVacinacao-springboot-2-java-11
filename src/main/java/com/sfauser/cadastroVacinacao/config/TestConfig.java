package com.sfauser.cadastroVacinacao.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sfauser.cadastroVacinacao.entities.Paciente;
import com.sfauser.cadastroVacinacao.entities.Vacina;
import com.sfauser.cadastroVacinacao.repositories.PacienteRepository;
import com.sfauser.cadastroVacinacao.repositories.VacinaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private PacienteRepository usuarioRepository;

	@Autowired
	private VacinaRepository vacinaRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Paciente a = new Paciente(null, "Joao", "joao@gmail.com", "075.326.269-03", sdf.parse("20/10/2010"));
		Paciente b = new Paciente(null, "maria", "maria@gmail.com", "044.877.888-09", sdf.parse("30/10/2010"));
		Paciente c = new Paciente(null, "ricardo", "ricardo@gmail.com", "888.386.999-03", sdf.parse("20/03/2010"));

		Vacina o = new Vacina(null, "coronavac", "aa@gggg",sdf.parse("30/10/2010"));
		Vacina s = new Vacina(null, "coronavac", "aa@gggg", sdf.parse("30/10/2010"));
		Vacina w = new Vacina(null, "coronavac", "aa@gggg",sdf.parse("30/10/2010"));

		usuarioRepository.saveAll(Arrays.asList(a, b, c));
		vacinaRepository.saveAll(Arrays.asList(o, s, w));
		a.getVacinas().add(o);
		b.getVacinas().add(o);
	}

}
