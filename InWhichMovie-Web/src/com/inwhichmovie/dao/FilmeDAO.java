package com.inwhichmovie.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.inwhichmovie.beans.Filme;

public class FilmeDAO implements AbstractDAO {

	@Override
	public void add(Object obj) {
		EntityManager em = DataBaseFactory.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Filme findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filme findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
