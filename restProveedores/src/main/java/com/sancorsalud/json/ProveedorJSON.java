package com.sancorsalud.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ProveedorJSON")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProveedorJSON {

	private int numero;
	private int subNumero;
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