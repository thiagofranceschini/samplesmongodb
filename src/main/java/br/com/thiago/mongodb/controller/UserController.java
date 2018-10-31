package br.com.thiago.mongodb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
