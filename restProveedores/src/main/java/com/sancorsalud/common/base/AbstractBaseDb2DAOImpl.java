package com.sancorsalud.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.sancorsalud.common.error.GlobalException;

/**
 * Clase base para DAOs.
 * Esta clase implementa funciones comunes en operaciones con bases de datos.
 * 
 * @author nicolas.charczewski
 *
 * @param <T> Entidad
 */
public class AbstractBaseDb2DAOImpl<T> implements BaseDAO<T> {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "sessionFactory_db2")
	protected SessionFactory sessionFactory;
	
	/**
	 * Crea una entidad
	 */
	public T create(final T entidad) throws GlobalException {
		try {
			this.sessionFactory.getCurrentSession().persist(entidad);
			this.sessionFactory.getCurrentSession().flush();
			this.sessionFactory.getCurrentSession().refresh(entidad);
			return entidad;
		} catch (Exception e) {
			throw new GlobalException(e);
		}
	}

	/**
	 * Busca una entidad por su ID y tipo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T find(Class type, Serializable id) throws GlobalException {
		try {
			return (T) this.sessionFactory.getCurrentSession().get(type, id);
		} catch (Exception e) {
			throw new GlobalException(e);
		}
	}
	
	/**
	 * Elimina una entidad
	 */
	@SuppressWarnings("rawtypes")
	public void delete(Class type, Serializable id) throws GlobalException {
		try {
			Object ref = this.sessionFactory.getCurrentSession().get(type, id);
			this.sessionFactory.getCurrentSession().delete(ref);
		} catch (Exception e) {
			throw new GlobalException(e);
		}
	}

	/**
	 * Actualiza una entidad
	 */
	@SuppressWarnings("unchecked")
	public T update(T t) throws GlobalException {
		try {
			return (T) this.sessionFactory.getCurrentSession().merge(t);
		} catch (Exception e) {
			throw new GlobalException(e);
		}
	}
	
	/**
	 * Busca todas las entidades de un determinado tipo.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(Class type) throws GlobalException {
		try {
			Query cq = sessionFactory.getCurrentSession().createQuery("from " + type.getCanonicalName());
			return cq.list();
		} catch (Exception e) {
			throw new GlobalException(e);
		}
	}

	/**
	 * Elimina una entidad.
	 */
	public void delete(T t) throws GlobalException {
		try {
			this.sessionFactory.getCurrentSession().delete(t);
		} catch (Exception e) {
			throw new GlobalException(e);
		}
	}
	
	/**
	 * Envia cambios aún en sesión hacia la base de datos
	 */
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}

	/**
	 * Construye un resultado lazy por defecto.
	 * Esta funcion es un helper para devolver la cantidad de elementos y el listado
	 * resultante de una consulta hql paginada en base de datos.
	 * 
	 * @param hql
	 * @param parametros
	 * @param pageSize
	 * @param first
	 * @return
	 */
	protected Object[] buildResultado(String hql, Map<String, Object> parametros, int pageSize, int first) {
		Object[] resultado = new Object[2];
		
		// Query select
		Query querySelect = sessionFactory.getCurrentSession().createQuery(hql);
		querySelect.setMaxResults(pageSize).setFirstResult(first);
		// Query count
		Query queryCount = sessionFactory.getCurrentSession().createQuery("select count(*) " + hql);

		// seteo de parámetros
		if (parametros != null && !parametros.isEmpty()) {
			for (String nombre : parametros.keySet()) {
				querySelect.setParameter(nombre, parametros.get(nombre));
				queryCount.setParameter(nombre, parametros.get(nombre));
			}
		}
		resultado = new Object[] { queryCount.uniqueResult(), querySelect.list() };
		return resultado;
	}

}
