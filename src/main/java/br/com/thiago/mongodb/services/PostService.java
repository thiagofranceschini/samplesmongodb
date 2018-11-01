package br.com.thiago.mongodb.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiago.mongodb.domain.Post;
import br.com.thiago.mongodb.repositories.PostRepository;
import br.com.thiago.mongodb.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> optional = repository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

	public List<Post> searchByTitle(String title) {
		return repository.findByTitleContainingIgnoreCase(title);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) throws ParseException {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
	
}
