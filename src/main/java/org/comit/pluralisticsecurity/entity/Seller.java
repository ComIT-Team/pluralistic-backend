package org.comit.pluralisticsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="SELLER")
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_SELLER")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="ID_USER")
	private User user;
	
	@Column(name="SELLER_NAME")
	private String sellername;
	
	@Column(name="INTERAC_ID")
	private String interacID;
	
}
