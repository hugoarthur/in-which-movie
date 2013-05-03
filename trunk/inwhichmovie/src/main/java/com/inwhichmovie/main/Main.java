/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inwhichmovie.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.NumberFormat;

import com.inwhichmovie.beans.Filme;
import com.inwhichmovie.dao.FilmeDAO;
import com.inwhichmovie.utils.ExtracaoUtils;

/**
 * 
 * @author hugoarthur
 */
public class Main {
	
	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(new File("/Users/hugoarthur/Downloads/log.txt")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		// TODO Jogar isso em um metodo e concluir a importacao de dados.
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setMinimumIntegerDigits(7);
		nf.setGroupingUsed(false);
		// 848228 - the avengers
		int cont = 0;
		while (cont < 1000) {
			Integer id = (int) ((Math.random()*10000000));
			System.out.println(id);
			if (id > 0 && id < 10000000) {
				try {
					gravaFilmeBanco(ExtracaoUtils.getFilme(nf.format(id)));
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cont++;
			}
		}
	}

	public static void gravaFilmeBanco(Filme filme) {
		FilmeDAO filmeDAO = new FilmeDAO();
		filmeDAO.add(filme);
	}
}
