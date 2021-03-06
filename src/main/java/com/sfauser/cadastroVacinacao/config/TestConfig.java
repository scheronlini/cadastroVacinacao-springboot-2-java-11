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
import com.sfauser.cadastroVacinacao.entities.AplicacaoVacina;
import com.sfauser.cadastroVacinacao.repositories.PacienteRepository;
import com.sfauser.cadastroVacinacao.repositories.AplicacaoVacinaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private AplicacaoVacinaRepository vacinaRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Paciente joao = new Paciente(null, "Joao", "joao@gmail.com", "075.326.269-03"
				, sdf.parse("20/10/2010"));
		Paciente maria = new Paciente(null, "maria", "maria@gmail.com", "044.877.888-09"
				, sdf.parse("30/10/2010"));
		Paciente ricardo = new Paciente(null, "ricardo", "ricardo@gmail.com", "888.386.999-03"
				, sdf.parse("20/03/2010"));
		pacienteRepository.saveAll(Arrays.asList(joao, maria, ricardo));

		AplicacaoVacina vacinaCorona = new AplicacaoVacina(null, "coronavac"
				, sdf.parse("30/10/2010"), joao);
		AplicacaoVacina vacinaH1N1 = new AplicacaoVacina(null, "H1N1"
				, sdf.parse("30/10/2010"), maria);
		AplicacaoVacina vacinaTetano = new AplicacaoVacina(null, "TETANO"
				, sdf.parse("30/10/2010"), ricardo);
		vacinaRepository.saveAll(Arrays.asList(vacinaCorona, vacinaH1N1, vacinaTetano));
	}
}