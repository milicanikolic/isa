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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.enumeracije.StatusNarudzbine;



@Entity
@Table(name="narudzbineGostiju")
public class GostNarudzbina implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2132971814933783536L;

	
	//iymenjeno jelo, pice, gost, radnik
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="narudzbina_id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Gost gostNarucio;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Jelo> jelaNaNarudzbini= new HashSet<Jelo>();
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Pice> picaNaNarudzbini= new HashSet<Pice>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Radnik konobar;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Rezervacija rezervacija;
	
	@Column(name="cena", unique=false, nullable=false)
	private double cenaRacuna=0;
	
	@Column(name="jeloSpremljeno", unique=false, nullable=false)
	boolean jeloSpremljeno=false;
	
	@Column(name="piceSpremljeno", unique=false, nullable=false)
	boolean piceSpremljeno=false;
	
	@Column(name="statusNarudzbine", unique=false, nullable=false)
	private StatusNarudzbine statusNarudzbine;
	
	@Column(name="ocenioRes", unique=false, nullable=false)
	private boolean ocenioRes;
	@Column(name="ocenioObrok", unique=false, nullable=false)
	private boolean ocenioObrok;
	@Column(name="ocenioUsugu", unique=false, nullable=false)
	private boolean ocenioUslugu;
	
	
	public GostNarudzbina() {
		super();
	}
	public Gost getGostNarucio() {
		return gostNarucio;
	}
	public void setGostNarucio(Gost gostNarucio) {
		this.gostNarucio = gostNarucio;
	}
	public Set<Jelo> getJelaNaNarudzbini() {
		return jelaNaNarudzbini;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public Radnik getKonobar() {
		return konobar;
	}
	public void setKonobar(Radnik konobar) {
		this.konobar = konobar;
	}*/

	
	
	public Rezervacija getRezervacija() {
		return rezervacija;
	}
	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}
	public void setJelaNaNarudzbini(Set<Jelo> jelaNaNarudzbini) {
		this.jelaNaNarudzbini = jelaNaNarudzbini;
	}
	public Set<Pice> getPicaNaNarudzbini() {
		return picaNaNarudzbini;
	}
	public void setPicaNaNarudzbini(Set<Pice> picaNaNarudzbini) {
		this.picaNaNarudzbini = picaNaNarudzbini;
	}
	
	
	
	public boolean isJeloSpremljeno() {
		return jeloSpremljeno;
	}
	public void setJeloSpremljeno(boolean jeloSpremljeno) {
		this.jeloSpremljeno = jeloSpremljeno;
	}
	public boolean isPiceSpremljeno() {
		return piceSpremljeno;
	}
	public void setPiceSpremljeno(boolean piceSpremljeno) {
		this.piceSpremljeno = piceSpremljeno;
	}
	public Radnik getRadnik() {
		return konobar;
	}
	public void setRadnik(Radnik konobar) {
		this.konobar = konobar;
	}
	public double getCenaRacuna() {
		for(Jelo j:jelaNaNarudzbini) {
			cenaRacuna+=j.getCena();
		}
		for(Pice p:picaNaNarudzbini) {
			cenaRacuna+=p.getCena();
		}
		return cenaRacuna;
	}
	public void setCenaRacuna(double cenaRacuna) {
		this.cenaRacuna = cenaRacuna;
	}
	public StatusNarudzbine getStatusNarudzbine() {
		return statusNarudzbine;
	}
	public void setStatusNarudzbine(StatusNarudzbine statusNarudzbine) {
		this.statusNarudzbine = statusNarudzbine;
	}
	public boolean isOcenioRes() {
		return ocenioRes;
	}
	public void setOcenioRes(boolean ocenioRes) {
		this.ocenioRes = ocenioRes;
	}
	public boolean isOcenioObrok() {
		return ocenioObrok;
	}
	public void setOcenioObrok(boolean ocenioObrok) {
		this.ocenioObrok = ocenioObrok;
	}
	public boolean isOcenioUslugu() {
		return ocenioUslugu;
	}
	public void setOcenioUslugu(boolean ocenioUslugu) {
		this.ocenioUslugu = ocenioUslugu;
	}
	
	
	
	

}
