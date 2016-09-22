package com.sancorsalud.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sancorsalud.common.base.AbstractBaseDb2DAOImpl;
import com.sancorsalud.dao.ProveedorDAO;
import com.sancorsalud.entity.Proveedor;

@Repository
public class ProveedorDAOImpl extends AbstractBaseDb2DAOImpl<Proveedor> implements ProveedorDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public Proveedor getProveedor(int numero, int subNumero) {

		Query query = null;
		String hql = "from Proveedor p where p.numero = :numero and p.subNumero = :subNumero";
		query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("numero", numero);
		query.setParameter("subNumero", subNumero);
		return (Proveedor) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Proveedor> getProveedores(String nombre) {

    	Query query = null;
    	String hql = "from Proveedor p where upper(p.nombre) like upper(:nombre) order by p.nombre";
		query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nombre", "%" + nombre + "%");
        return query.list();
	}

}