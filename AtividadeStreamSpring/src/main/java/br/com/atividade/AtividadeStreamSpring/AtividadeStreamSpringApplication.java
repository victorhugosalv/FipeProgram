package br.com.atividade.AtividadeStreamSpring;

import br.com.atividade.AtividadeStreamSpring.principal.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtividadeStreamSpringApplication implements CommandLineRunner {

	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(AtividadeStreamSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.rodarPrograma();
	}
}
