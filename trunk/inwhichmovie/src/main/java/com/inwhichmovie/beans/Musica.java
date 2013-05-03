package com.inwhichmovie.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "musica")
public class Musica {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMusica;
	@Column
	private String nome;
	@Column
	private String nomeArtista;
	@ManyToMany(mappedBy="musicas")
	private List<Filme> filmes;
	@Override
	
	public String toString() {
		return "Musica [idMusica=" + idMusica + ", nome=" + nome
				+ ", nomeArtista=" + nomeArtista + ", filmes=" + filmes + "]";
	}
	
	public Integer getIdMusica() {
		return idMusica;
	}
	
	public void setIdMusica(Integer idMusica) {
		this.idMusica = idMusica;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeArtista() {
		return nomeArtista;
	}
	
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}
	
	public List<Filme> getFilmes() {
		return filmes;
	}
	
	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
}
