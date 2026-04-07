package br.com.atividade.AtividadeStreamSpring;

import br.com.atividade.AtividadeStreamSpring.principal.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtividadeStreamSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AtividadeStreamSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
		menu.rodarPrograma();
	}
}
