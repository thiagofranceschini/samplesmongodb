package br.com.thiago.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.thiago.mongodb.domain.User;
import br.com.thiago.mongodb.dto.UserDTO;
import br.com.thiago.mongodb.repositories.UserRepository;
import br.com.thiago.mongodb.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> optional = repository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

	public User insert(UserDTO dto) {
		User savedUser = repository.save(fromDto(dto));
		return savedUser;
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User usuario) {
		Optional<User> optional = repository.findById(usuario.getId());
		User newUser = optional.get();
		updateData(newUser, usuario);
		return repository.save(newUser);
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}

	public User fromDto(UserDTO dto) {
		User user = new User();
		if (!StringUtils.isEmpty(dto.getName())) {
			user.setName(dto.getName());
		}
		if (!StringUtils.isEmpty(dto.getEmail())) {
			user.setEmail(dto.getEmail());
		}
		return user;
	}
	
	
}
