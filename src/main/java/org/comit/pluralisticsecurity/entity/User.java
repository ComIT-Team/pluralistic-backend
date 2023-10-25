package org.comit.pluralisticsecurity.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Integer idUser;

	@Column(name = "FIRST_NAME")
	private String firstname;

	@Column(name = "LAST_NAME")
	private String lastname;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "USERNAME")
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "active")
	private boolean active;

	//constructor
	public User() {
		super();
	}

	// constructor
	public User(Integer idUser) {
		this.idUser = idUser;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<UserRole> userRoles;



	@Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
		
	  List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	  
	  for (UserRole temp : userRoles) 
	  { 
		 	  
	 authorities.add(new SimpleGrantedAuthority(temp.getRole().getIdRole().toString()));
		  //authorities.add(new SimpleGrantedAuthority("USER"));
	  }
	  
	  return authorities;
	  }
	
	@Override
	public String getUsername() {
		return email;
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

	

}
