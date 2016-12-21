package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Pice {
	
	public enum vrstaPica {
		ALKOHOLNA, BEZALKOHOLNA, TOPLI_NAPICI, ZESTINA  
	};
	
	@Column(name = "vrstaPica", unique = false, nullable = false)
	private vrstaPica vrstaPica;
	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;
	@Column(name = "opis", unique = false, nullable = false)
	private String opis;
	@Column(name = "cena", unique = false, nullable = false)
	private double cena;

	public Pice() {}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public vrstaPica getVrstaPica() {
		return vrstaPica;
	}

	public void setVrstaPica(vrstaPica vrstaPica) {
		this.vrstaPica = vrstaPica;
	}
	
	
	
	
}
