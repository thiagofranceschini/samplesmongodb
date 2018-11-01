package br.com.thiago.mongodb.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thiago.mongodb.domain.User;
import br.com.thiago.mongodb.dto.UserDTO;
import br.com.thiago.mongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDto = users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			User user = service.findById(id);
			UserDTO dto = new UserDTO(user);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador invalido:" + id);
		}
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody UserDTO dto) {
		if (!ObjectUtils.isEmpty(dto)) {
			User user = service.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
					.toUri();
			return ResponseEntity.created(uri).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetro Usuário Invalido");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserDTO dto, @PathVariable String id) {
		if (!ObjectUtils.isEmpty(dto)) {
			User user = service.fromDto(dto);
			user.setId(id);
			user = service.update(user);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetro Usuário Invalido");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		if (!ObjectUtils.isEmpty(id)) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametro Usuário Invalido");
		}
	}

	@GetMapping("/{id}/posts")
	public ResponseEntity<?> findPosts(@PathVariable String id) {
		if (!StringUtils.isEmpty(id)) {
			User user = service.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(user.getPosts());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador invalido:" + id);
		}
	}

}
