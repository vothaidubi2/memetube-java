package com.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet", catalog = "memetube")
public class Wallet {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idwallet", unique = true, nullable = false)
	private int idwallet;
	@ManyToOne
	@JoinColumn(name = "iduser")
	private Users users;
	@Column(name = "balance")
	private double balance;
}
