package com.inwhichmovie.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseFactory {
	
	private static DataBaseFactory dataBase;
	private EntityManagerFactory emf;
	
	public DataBaseFactory() {
		if(emf == null)
			emf = Persistence.createEntityManagerFactory("IN_WHICH_MOVIE");
	}
	
	public static DataBaseFactory getInstance(){
		if(dataBase == null)
			dataBase = new DataBaseFactory();
		return dataBase;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
