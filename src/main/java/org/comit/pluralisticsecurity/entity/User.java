package org.comit.pluralisticsecurity.entity;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users") // Using plural table name
@Data
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // Use 'id' as the primary key column name
	private Integer id;

	@Column(name = "first_name") // Use underscores for multi-word column names
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<UserRole> userRoles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		//return List.of(new SimpleGrantedAuthority(userRoles.iterator()));
		return null;
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
	
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}


/*	public Collection<? extends GrantedAuthority> getAuthorities(
			  Collection<Role> roles) {
			    List<GrantedAuthority> authorities
			      = new ArrayList<>();
			    for (Role role: roles) {
			        authorities.add(new SimpleGrantedAuthority(role.getNameRole()));
			        role.getPrivileges().stream()
			         .map(p -> new SimpleGrantedAuthority(p.getName()))
			         .forEach(authorities::add);
			    }
			    
			    return authorities;
			}*/



	

}

