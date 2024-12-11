package model;

/**
 * 
 * @author erika263
 * @description Model for book system
 * email: erika.contax@gmail.com
 */

public class Livro {
	
	private String titulo;
	private String autor;

	public Livro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Título: " + titulo + " | Autor: " + autor;
	}
}
