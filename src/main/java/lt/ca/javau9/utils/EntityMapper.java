package lt.ca.javau9.utils;

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
				
		return entity;		
	}
	
	public UserDto toUserDto(UserEntity entity) {
		return new UserDto( entity.getId(), entity.getUsername(), entity.getEmail() );
	}
	
}
