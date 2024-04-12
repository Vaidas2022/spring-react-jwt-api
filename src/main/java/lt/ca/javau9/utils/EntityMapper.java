package lt.ca.javau9.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lt.ca.javau9.models.UserDto;
import lt.ca.javau9.models.UserEntity;

@Component
public class EntityMapper {
	
	public UserEntity toUserEntity(UserDto dto) {
		
		UserEntity entity = new UserEntity();
		entity.setId( dto.getId());
		entity.setUsername( dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());	
		return entity;		
	}
	//Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
	
	public UserDto toUserDto(UserEntity entity) {
		return new UserDto( 
				entity.getId(), 
				entity.getUsername(), 
				entity.getEmail(), 
				entity.getPassword(), 
				entity.getRoles() 
			);
	}
	
}
