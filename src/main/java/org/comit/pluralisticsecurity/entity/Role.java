package org.comit.pluralisticsecurity.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROLE")
	Integer idRole;
	
	@Column(name="NAME_ROLE")
	String nameRole;
	
	@OneToMany(mappedBy="role")
	List<UserRole> userRoles;

	public Role(String nameRole) {
		this.nameRole = nameRole;
	}
	
	public Role() {
		super();
	}

	public Role(int idRole) {
		super();
		this.idRole = idRole;
	}

	
	
	

}
