package br.com.thiago.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.thiago.mongodb.domain.Post;
import br.com.thiago.mongodb.domain.User;
import br.com.thiago.mongodb.dto.AuthorDTO;
import br.com.thiago.mongodb.repositories.PostRepository;
import br.com.thiago.mongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		repository.deleteAll();
		postRepository.deleteAll();

		User thamires = new User(null, "Thamires", "thamiresnasc@gmail.com");
		User thiago = new User(null, "Thiago", "thiagoconsultor1@gmail.com");
		User lorena = new User(null, "Lorena", "lolafranceschini@gmail.com");

		repository.saveAll(Arrays.asList(lorena, thiago, thamires));

		Post post1 = new Post(null, dateFormat.parse("21/03/2019"), "Partiu Canadá", "Primeira visita!!!", new AuthorDTO(thiago));
		Post post2 = new Post(null, dateFormat.parse("07/10/2019"), "Surpresa Amor", "Susto no amor", new AuthorDTO(thamires));

		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
