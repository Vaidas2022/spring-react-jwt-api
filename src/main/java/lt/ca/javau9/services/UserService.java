package lt.ca.javau9.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau9.models.UserDto;
import lt.ca.javau9.models.UserEntity;
import lt.ca.javau9.repositories.UserRepository;
import lt.ca.javau9.utils.EntityMapper;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final EntityMapper entityMapper;
	
	public UserService(UserRepository userRepository, EntityMapper entityMapper) {
		this.userRepository = userRepository;
		this.entityMapper = entityMapper;
	}
	
	//CRUD - Create, Read, Update, Delete
	
	public UserDto createUser(UserDto userDto) {
		UserEntity userEntityBeforeSave = entityMapper.toUserEntity(userDto);

		UserEntity userEntityAfterSave = userRepository.save(userEntityBeforeSave);		
		
		return entityMapper.toUserDto(userEntityAfterSave);		
	}
	
	public List<UserDto> getAllUsers(){
		List<UserEntity> users = userRepository.findAll();
		
		return users.stream()
				.map(entityMapper::toUserDto)
				.toList();		
	}
	
	public Optional<UserDto> getUserById(Long id) {
		Optional<UserEntity> user = userRepository.findById(id);
		
		return user.map(entityMapper::toUserDto);
	}
	
	public Optional<UserDto> updateUser(Long id, UserDto userDto ){
		
		if( userRepository.existsById(id) ) {
			UserEntity userEntityBeforeSave = entityMapper.toUserEntity(userDto);
			userEntityBeforeSave.setId(id);
			
			UserEntity userEntityAfterSave = userRepository.save(userEntityBeforeSave);
			return Optional.of( entityMapper.toUserDto(userEntityAfterSave));
			
		} else {
			return Optional.empty();
		}
		
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
}
