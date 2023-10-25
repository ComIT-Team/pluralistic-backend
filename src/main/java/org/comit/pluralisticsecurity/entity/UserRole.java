

package org.comit.pluralisticsecurity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="USER_ROLE")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER_ROLE")
	private Integer idUserRole;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_USER")
	@JsonBackReference
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_ROLE")
	private Role role;
	
}
