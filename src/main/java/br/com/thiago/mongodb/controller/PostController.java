package br.com.thiago.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.mongodb.controller.utils.UrlUtils;
import br.com.thiago.mongodb.domain.Post;
import br.com.thiago.mongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			Post post = service.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(post);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador inválido: " + id);
		}
	}

	@GetMapping("/titlesearch")
	public ResponseEntity<?> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = UrlUtils.decodedParam(text);
		List<Post> list = service.searchByTitle(text);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
