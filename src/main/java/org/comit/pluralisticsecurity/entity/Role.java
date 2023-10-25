package org.comit.pluralisticsecurity.entity;



import java.util.List;

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
@Table(name="role")
public class Role {

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROLE")
	private Integer idRole;
	
	@Column(name="NAME_ROLE")
	private String nameRole;
	
	@OneToMany(mappedBy="role",fetch = FetchType.EAGER)
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
	public Role(int idRole) {
		super();
		this.idRole = idRole;
	}

}
