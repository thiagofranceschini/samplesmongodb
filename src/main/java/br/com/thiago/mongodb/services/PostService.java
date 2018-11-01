package br.com.thiago.mongodb.services;

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
}
