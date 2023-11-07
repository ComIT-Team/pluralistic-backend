package org.comit.pluralisticsecurity.entity;



import java.util.List;

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
@Table(name="ROLES")
public class Role {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer idRole;
	
	@Column(name="NAME")
	private String nameRole;
	
	@OneToMany( mappedBy = "role",cascade = CascadeType.ALL, fetch = FetchType.EAGER) //mappedBy="role",fetch = FetchType.EAGER)
	private List<UserRole> userRoles;

	//constructor
	public Role(String nameRole) {
		this.nameRole = nameRole;
	}
	
	//constructor
	public Role() {
		super();
	}
	
	//constructor
	public Role(Integer idRole) {
		super();
		this.idRole = idRole;
	}

	

}
