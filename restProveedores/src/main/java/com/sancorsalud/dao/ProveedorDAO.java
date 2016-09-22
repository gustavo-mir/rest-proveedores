package com.sancorsalud.dao;

import java.util.List;

import com.sancorsalud.common.base.BaseDAO;
import com.sancorsalud.entity.Proveedor;

public interface ProveedorDAO extends BaseDAO<Proveedor> {

	public Proveedor getProveedor(int numero, int subNumero);
	public List<Proveedor> getProveedores(String nombre);

}