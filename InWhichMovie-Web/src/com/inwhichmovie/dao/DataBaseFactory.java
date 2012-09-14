package com.inwhichmovie.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataBaseFactory {
	
	private static DataBaseFactory dataBase;
	private static EntityManagerFactory emf;
	
	public DataBaseFactory() {
		if(emf == null)
			emf = Persistence.createEntityManagerFactory("IN_WHICH_MOVIE");
	}
	
	public DataBaseFactory getInstance(){
		if(dataBase == null)
			dataBase = new DataBaseFactory();
		return dataBase;
	}

	public static EntityManagerFactory getEmf() {
		return emf;
	}

	public static void setEmf(EntityManagerFactory emf) {
		DataBaseFactory.emf = emf;
	}

}
