package com.inwhichmovie.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.inwhichmovie.beans.Filme;

public class FilmeDAO implements AbstractDAO {

	public void add(Object obj) {
		EntityManager em = DataBaseFactory.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public Object findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
