package com.inwhichmovie.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.inwhichmovie.beans.Filme;

public class FilmeDAO implements AbstractDAO {
	protected EntityManager em;

	public FilmeDAO() {
		if(em == null)
			em = DataBaseFactory.getInstance().getEmf().createEntityManager();
	}
	
	public void add(Object obj) {
		if (obj != null) {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			em.close();
		}
	}
	
	public void update(Object obj) {
		Filme filme = em.find(Filme.class, ((Filme)obj).getIdImdb());
		if(filme != null){
			em.getTransaction().begin();
			filme = (Filme)obj;
			em.getTransaction().commit();
			em.close();
		}
	}
	
	public void remove(Object obj) {
		
	}

	public Object findById(Integer id) {
		return null;
	}

	public Object findByName(String nome) {
		return null;
	}

	public List<Object> findAll() {
		return null;
	}
}
