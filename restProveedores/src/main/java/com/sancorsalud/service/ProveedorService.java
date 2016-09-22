package com.sancorsalud.service;

import java.io.Serializable;
import java.util.List;

import com.sancorsalud.common.error.GlobalException;
import com.sancorsalud.entity.Proveedor;

public interface ProveedorService extends Serializable {
	
	public Proveedor getProveedor(int numero, int subNumero) throws GlobalException;
	public List<Proveedor> getProveedores(String nombre) throws GlobalException;

}