package com.inwhichmovie.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "filme")
public class Filme implements Comparable<Filme>{
	@Column
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFilme;
	@Column
	private String idImdb;
	@Column
	private String nome;
	@Column
	private String nomeOriginal;
	@Column
	private String ano;
	private List<Musica> musicas;
	
	@Override
	public String toString() {
		return "Filme [idFilme=" + idFilme + ", idImdb=" + idImdb + ", nome="
				+ nome + ", nomeOriginal=" + nomeOriginal + ", ano=" + ano
				+ ", musicas=" + musicas + "]";
	}

	public Integer getIdFilme() {
		return idFilme;
	}


	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}


	public String getIdImdb() {
		return idImdb;
	}


	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNomeOriginal() {
		return nomeOriginal;
	}


	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	public List<Musica> getMusicas() {
		return musicas;
	}


	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}


	@Override
	public int compareTo(Filme o) {
//		if((nome.compareToIgnoreCase(o.getNome()))==0)
//			return nomeOriginal.compareToIgnoreCase(o.getNomeOriginal());
		if(nome == null)
			nome = "";
		return nome.compareToIgnoreCase(o.getNome());
	}
}
