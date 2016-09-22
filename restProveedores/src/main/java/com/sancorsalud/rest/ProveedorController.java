package com.sancorsalud.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sancorsalud.common.error.GlobalException;
import com.sancorsalud.entity.Proveedor;
import com.sancorsalud.json.ProveedorJSON;
import com.sancorsalud.service.ProveedorService;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

	@Inject
	private ProveedorService proveedorService;

	@RequestMapping(value = "/get/{numero}/{subNumero}", method = RequestMethod.GET)
	@ResponseBody
	public ProveedorJSON getProveedor(
			@PathVariable int numero,
			@PathVariable int subNumero) throws GlobalException, IllegalAccessException, InvocationTargetException {

		ProveedorJSON proveedorJSON = new ProveedorJSON();
		Proveedor proveedor = proveedorService.getProveedor(numero, subNumero);
		if (proveedor != null) {
			BeanUtils.copyProperties(proveedorJSON, proveedor);
		}
		return proveedorJSON;
	}

	@RequestMapping(value = "/get/{nombre}", method = RequestMethod.GET)
	@ResponseBody
	public List<ProveedorJSON> getProveedores(
			@PathVariable String nombre) throws GlobalException, IllegalAccessException, InvocationTargetException {

		List<ProveedorJSON> proveedoresJSON = new ArrayList<ProveedorJSON>();
		List<Proveedor> proveedores = proveedorService.getProveedores(nombre);
		if (proveedores != null) {
			for (Proveedor p : proveedores) {
				ProveedorJSON proveedorJSON = new ProveedorJSON();
				BeanUtils.copyProperties(proveedorJSON, p);
				proveedoresJSON.add(proveedorJSON);
			}
		}
		return proveedoresJSON;
	}

}