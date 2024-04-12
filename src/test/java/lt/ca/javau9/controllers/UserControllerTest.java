package lt.ca.javau9.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import lt.ca.javau9.models.UserDto;
import lt.ca.javau9.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private UserService userService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        UserDto userDto = new UserDto("Alice", "aa@a.com", "1234"); 
        given(userService.createUser(userDto)).willReturn(userDto);

        mockMvc.perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());
    }
	
	 @Test
	    void getAllUsers_ShouldReturnAllUsers() throws Exception {
	        List<UserDto> users = Arrays.asList(new UserDto(), new UserDto()); // Nustatykite reikiamus laukus
	        given(userService.getAllUsers()).willReturn(users);

	        mockMvc.perform(get("/api/users/")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(users)));
	    }

	 @Test
	    void getUserById_WhenUserExists_ShouldReturnUser() throws Exception {
	        Long userId = 1L;
	        UserDto userDto = new UserDto(); 
	        given(userService.getUserById(userId)).willReturn(Optional.of(userDto));

	        mockMvc.perform(get("/api/users/{id}", userId)
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
	    }
	 
	

}
