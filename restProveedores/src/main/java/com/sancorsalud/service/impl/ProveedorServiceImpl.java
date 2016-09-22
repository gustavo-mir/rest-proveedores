package com.sancorsalud.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.sancorsalud.common.error.GlobalException;
import com.sancorsalud.dao.ProveedorDAO;
import com.sancorsalud.entity.Proveedor;
import com.sancorsalud.service.ProveedorService;

@Named
public class ProveedorServiceImpl implements ProveedorService {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProveedorDAO proveedorDAO;

	@Override
	@Transactional(value="transactionManager_db2", readOnly=true)
	public Proveedor getProveedor(int numero, int subNumero) throws GlobalException {

		return proveedorDAO.getProveedor(numero, subNumero);
	}

	@Override
	@Transactional(value="transactionManager_db2", readOnly=true)
	public List<Proveedor> getProveedores(String nombre) throws GlobalException  {

		//Valida que al menos tenga 3 caracteres !!!
		if (nombre.length() >= 3) {
			return proveedorDAO.getProveedores(nombre);	
		}
		return null;
	}

}