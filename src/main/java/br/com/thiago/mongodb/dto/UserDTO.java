package br.com.thiago.mongodb.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.thiago.mongodb.domain.User;

@Component
public class UserDTO {

	private String id;
	private String name;
	private String email;

	public UserDTO() {
		super();
	}

	public UserDTO(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(User user) {

		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public List<UserDTO> listUserDtoFromUsers(List<User> users) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (User user : users) {
			UserDTO dto = new UserDTO(user);
			list.add(dto);
		}
		return list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
