package com.sancorsalud.common.base;

import java.io.Serializable;
import java.util.List;

import com.sancorsalud.common.error.GlobalException;

/**
 * Interfaz de AbstractBaseDAOImpl
 * 
 * @author nicolas.charczewski
 *
 * @param <T> Tipo de entidad
 */
public interface BaseDAO<T> extends Serializable {

	public T create(T t) throws GlobalException;

	public T find(@SuppressWarnings("rawtypes") Class type, Serializable id) throws GlobalException;
	
	public void delete(@SuppressWarnings("rawtypes") Class type, Serializable id) throws GlobalException;
	
	public void delete(T t) throws GlobalException;
	
	public T update(T t) throws GlobalException;
	
	public List<T> findAll(@SuppressWarnings("rawtypes") Class type) throws GlobalException;

	public void flush();
	
}
