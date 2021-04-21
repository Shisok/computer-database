package com.excilys.cdb.mapper;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.UserDTOPersistance;
import com.excilys.cdb.model.User;

@Component
public class MapperUser {
	public UserDTOPersistance mapFromModelToDTOPersistance(User user) {
		return new UserDTOPersistance(user.getId(), user.getAuthority(), user.getUsername(), user.getPassword(),
				user.isEnabled());
	}
}
