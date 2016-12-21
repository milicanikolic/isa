package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "karte_pica")
public class KartaPica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6373415296066395638L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kartaPica_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ime", unique = false, nullable = false)
	private String ime;

	@Column(name = "opis", unique = false, nullable = false)
	private String opis;

	@Column(name = "cena", unique = false, nullable = false)
	private int cena;

	public KartaPica() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	
}
