package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Jelo {
	public enum vrstaJela {
		SALATA, KUVANO_JELO, PECENO_JELO
	};
	
	@Column(name = "vrstaJela", unique = false, nullable = false)
	private vrstaJela vrstaJela;
	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;
	@Column(name = "opis", unique = false, nullable = false)
	private String opis;
	@Column(name = "cena", unique = false, nullable = false)
	private double cena;
	@Column(name = "ocena", unique = false, nullable = false)
	private double ocena;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	public Jelo() {}

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

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public vrstaJela getVrstaJela() {
		return vrstaJela;
	}

	public void setVrstaJela(vrstaJela vrstaJela) {
		this.vrstaJela = vrstaJela;
	}
	
	
	
	
}
