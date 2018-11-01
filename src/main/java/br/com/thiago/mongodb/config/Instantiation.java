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
import br.com.thiago.mongodb.dto.CommentDTO;
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

		Post post1 = new Post(null, dateFormat.parse("21/03/2018"), "Partiu Canadá", "Primeira visita!!!",
				new AuthorDTO(thiago));
		Post post2 = new Post(null, dateFormat.parse("07/10/2018"), "Surpresa Amor", "Susto no amor",
				new AuthorDTO(thamires));

		CommentDTO comment1 = new CommentDTO("Boa Viagem!", dateFormat.parse("21/03/2018"), new AuthorDTO(lorena));
		CommentDTO comment2 = new CommentDTO("traga charope de maple!", dateFormat.parse("22/03/2018"), new AuthorDTO(lorena));
		CommentDTO comment3 = new CommentDTO("voce me mata do coração", dateFormat.parse("07/10/2018"), new AuthorDTO(thiago));
		CommentDTO comment4 = new CommentDTO("O bolo estava bom!", dateFormat.parse("08/10/2018"), new AuthorDTO(lorena));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3, comment4));
		
		postRepository.saveAll(Arrays.asList(post1, post2));

		thamires.getPosts().addAll(Arrays.asList(post2));
		thiago.getPosts().addAll(Arrays.asList(post1));
		repository.save(thamires);
		repository.save(thiago);
	}

}
