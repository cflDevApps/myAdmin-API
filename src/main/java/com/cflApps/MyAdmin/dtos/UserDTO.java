package com.cflApps.MyAdmin.dtos;

import java.util.List;

import com.cflApps.MyAdmin.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {
	
	private Long id;
	private String username;
	private String password;	
	private List<String> roles;

	@Setter
	private String token;
	
	public UserDTO(User entity) {
		this.id = entity.getId();
		this.username = entity.getUsername();	
		this.roles = new Gson().fromJson(entity.getJson_roles(), new TypeToken<List<String>>() {}.getType());
	}
	
}
