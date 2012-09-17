package com.inwhichmovie.beans;

public class Musica {
	private String nome;
	private String nomeArtista;
	
	@Override
	public String toString() {
		return "Musica [nome=" + nome + ", nomeArtista=" + nomeArtista + "]";
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
}
