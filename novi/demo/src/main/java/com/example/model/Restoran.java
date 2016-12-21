package com.example.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

enum TipRestorana{DOMACA_KUHINJA, ITALIJANSKI, KINESKI, VEGAN}

@Entity
@Table(name = "restorani")
public class Restoran implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190684828794300206L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="restoran_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="restoran_tip", unique=false, nullable=false)
	private TipRestorana tipRestorana;
	
	@Column(name="restoran_ime", unique=false, nullable=false)
	private String ime;
	
	//@Column(name="restoran_jelovnik", unique=false, nullable=false)
	@OneToOne(cascade={ CascadeType.ALL }, fetch= FetchType.LAZY)
	private Jelovnik jelovnik;
	
	@OneToOne(cascade={ CascadeType.ALL }, fetch= FetchType.LAZY)
	private KartaPica kartaPica;
	
	@Column(name="restoran_menadzer", unique=false, nullable=false)
	private MenadzerRestorana menadzerRestorana;
	
	public Restoran(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipRestorana getTipRestorana() {
		return tipRestorana;
	}

	public void setTipRestorana(TipRestorana tipRestorana) {
		this.tipRestorana = tipRestorana;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Jelovnik getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(Jelovnik jelovnik) {
		this.jelovnik = jelovnik;
	}

	public KartaPica getKartaPica() {
		return kartaPica;
	}

	public void setKartaPica(KartaPica kartaPica) {
		this.kartaPica = kartaPica;
	}
	
	
	
}
