package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kuvar {

	public enum vrstaKuvara {
		ZA_SALATE, ZA_KUVANA_JELA, ZA_PECENA_JELA
	};

	@Column(name = "vrstaKuvara", unique = false, nullable = false)
	private vrstaKuvara vrstaKuvara;
	@Column(name = "ime", unique = false, nullable = false)
	private String ime;
	@Column(name = "prezime", unique = false, nullable = false)
	private String prezime;
	@Column(name = "email", unique = false, nullable = false)
	private String email;
	@Column(name = "korisnickoIme", unique = true, nullable = false)
	private String korisnickoIme;
	@Column(name = "sifra", unique = false, nullable = false)
	private String sifra;
	// private ArrayList<Gost>prijatelji;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	public Kuvar() {
		super();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public vrstaKuvara getVrstaKuvara() {
		return vrstaKuvara;
	}

	public void setVrstaKuvara(vrstaKuvara vrstaKuvara) {
		this.vrstaKuvara = vrstaKuvara;
	}
	
	

}
