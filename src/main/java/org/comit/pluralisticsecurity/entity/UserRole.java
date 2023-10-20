package org.comit.pluralisticsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_roles") // Using plural table name
@Data
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idUserRole;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	

	
/*	public Role getRole() {
		return role;
	}*/
	public void setRole(Role role) {
		this.role = role;
		
	}

	/*public User getUser() {
		return user;
	}*/

	public void setUser(User user) {
		this.user = user;
		
	}


	/*@Override
	public String toString() {
		return "UserRole [idUserRole=" + idUserRole + ", user=" + user + ", role=" + role + "]";
	}*/
	
	

}
