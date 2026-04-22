package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "medicamento")
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data public class Medicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idmedicamento")
	@ToString.Exclude private int idmedicamento;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "formato")
	private Formato formato;

	@Column(name = "fechacaducidad")
	private String fechacaducidad;
	
	@Column(name = "stock")
	private boolean stock;
	

	public Medicamento(String nombre, Formato formato, String fechacaducidad, boolean stock) {
		super();
		this.nombre = nombre;
		this.fechacaducidad = fechacaducidad;
		this.stock = stock;
		this.formato=formato;
	}

}
