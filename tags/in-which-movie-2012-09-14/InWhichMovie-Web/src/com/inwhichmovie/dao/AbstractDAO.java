package com.inwhichmovie.dao;

import java.util.List;

public interface AbstractDAO {
	public void add(Object obj);

	public void remove(Object obj);

	public Object findById(Integer id);

	public Object findByName(String nome);

	public List findAll();
}
