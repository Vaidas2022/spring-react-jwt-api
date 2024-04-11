package lt.ca.javau9.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lt.ca.javau9.models.UserDto;
import lt.ca.javau9.models.UserEntity;
import lt.ca.javau9.utils.EntityMapper;

@SpringBootTest
public class EntityMapperTests {

	@Autowired
	EntityMapper entityMapper;
	
	@Test 
	void toUserDto_ShouldCorrectlyMapUserEntityToUserDto(){
		//Arrange
		UserEntity userEntity = new UserEntity();
		userEntity.setId( 15L );
		userEntity.setUsername("someUserName");
		userEntity.setEmail("some@email.com");
		
		//Act
		UserDto result = entityMapper.toUserDto(userEntity);
		
		//Assert
		assertThat( result.getId()).isEqualTo(  userEntity.getId());
		assertThat( result.getUsername()).isEqualTo(  userEntity.getUsername());
		assertThat( result.getEmail()).isEqualTo(  userEntity.getEmail());
		
	}
	
	@Test
	void toUserEntity_ShouldCorrectlyMapUserDtoToUserEntity() {
		UserDto userDto = new UserDto( 20L, "testUser", "test@example.com");
		
		UserEntity result = entityMapper.toUserEntity(userDto);
		
		assertThat(result.getId()).isEqualTo(userDto.getId());
		assertThat(result.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(result.getEmail()).isEqualTo(userDto.getEmail());				
	}
	
}
