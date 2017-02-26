package com.example.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="pica")
public class Pice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 871796990671380670L;

	public enum vrstaPica {
		ALKOHOLNA, BEZALKOHOLNA, TOPLI_NAPICI, ZESTINA  
	};
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Restoran restoran;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Sanker sanker;
	
	@Column(name="vrsta_pica", unique=false, nullable=false)
	private vrstaPica vrstaP;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pice_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;
	@Column(name = "opis", unique = false, nullable = true)
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

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Sanker getSanker() {
		return sanker;
	}

	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}

	public vrstaPica getVrstaP() {
		return vrstaP;
	}

	public void setVrstaP(vrstaPica vrstaP) {
		this.vrstaP = vrstaP;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
