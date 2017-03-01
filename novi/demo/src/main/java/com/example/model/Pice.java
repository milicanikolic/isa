package com.example.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.enumeracije.StatusPica;
import com.example.enumeracije.VrstaPica;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="pica")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 871796990671380670L;


	@ManyToOne(cascade = CascadeType.ALL)
	private Restoran restoran;
	
	
	@Column(name = "statusPica", unique = true, nullable = true)
	 private  StatusPica statusPica;
	 
	 @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="picaNaNarudzbini")
	 private Set<GostNarudzbina> naNarudzbinama=new HashSet<GostNarudzbina>();
	
	@Column(name="vrsta_pica", unique=false, nullable=false)
	private VrstaPica vrstaP;
	
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

	public VrstaPica getVrstaP() {
		return vrstaP;
	}

	public void setVrstaP(VrstaPica vrstaP) {
		this.vrstaP = vrstaP;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPica getStatusPica() {
		return statusPica;
	}

	public void setStatusPica(StatusPica statusPica) {
		this.statusPica = statusPica;
	}

	public Set<GostNarudzbina> getNaNarudzbinama() {
		return naNarudzbinama;
	}

	public void setNaNarudzbinama(Set<GostNarudzbina> naNarudzbinama) {
		this.naNarudzbinama = naNarudzbinama;
	}

	
	
}
