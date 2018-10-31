package br.com.thiago.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.thiago.mongodb.domain.User;
import br.com.thiago.mongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		User thamires = new User(null, "Thamires", "thamiresnasc@gmail.com");
		User thiago = new User(null, "Thiago", "thiagoconsultor1@gmail.com");
		User lorena = new User(null, "Lorena", "lolafranceschini@gmail.com");

		repository.saveAll(Arrays.asList(lorena, thiago, thamires));

	}

}
