package com.sancorsalud.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SMPDATD.M124FPROV")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 1L;

	public Proveedor(){}

	@Id
	@Column(name = "PV_NPRO")
	private int numero;
	
	@Id
	@Column(name = "PV_SNPRO")
	private int subNumero;

	@Column(name = "PV_DESC")
	private String nombre;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getSubNumero() {
		return subNumero;
	}

	public void setSubNumero(int subNumero) {
		this.subNumero = subNumero;
	}

	public String getNombre() {
		return nombre.trim();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}