package org.comit.pluralisticsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="USER_ROLE")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER_ROLE")
	Integer idUserRole;
	
	@ManyToOne
	@JoinColumn(name="ID_USER")
	User user;

	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	Role role;
	
	

	
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
