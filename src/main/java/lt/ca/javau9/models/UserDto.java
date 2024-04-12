package lt.ca.javau9.models;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto implements UserDetails {

	private static final long serialVersionUID = -1L;
	
	private Long id;
	private String username;
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@JsonIgnore
	private String password; //One way road 
	
	public UserDto() {}

	public UserDto(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}
	
	public UserDto(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	public UserDto(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDto user = (UserDto) o;
	    return Objects.equals(id, user.id);
	  }
}
