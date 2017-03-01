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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

enum TipRestorana{DOMACA_KUHINJA, ITALIJANSKI, KINESKI, VEGAN}

@Entity
@Table(name = "restorani")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	
	@Column(name="restoran_ocena", unique=false, nullable=true)
	private double ocena;
	
	@Column(name="brojOcenaRes", unique=false, nullable=false)
	 private int brojOcenaRes;
	 
	 
	 @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="restoran")
	 @JsonIgnore
	 private Set<Sto> stoloviURestoranu= new HashSet<Sto>();
	 
	 //za rastojanje
	/*  @Column(name="lat", unique=false, nullable=false)
	  private double latitude;
	  @Column(name="lon", unique=false, nullable=false)
	  private double longitude;
	*/
	
	
	/*	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "restoran_jelo", joinColumns = @JoinColumn(name = "restoran_id"), inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Jelo> jelovnik= new HashSet<Jelo>();
*/
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
	
	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public int getBrojOcenaRes() {
		return brojOcenaRes;
	}

	public void setBrojOcenaRes(int brojOcenaRes) {
		this.brojOcenaRes = brojOcenaRes;
	}

	public Set<Sto> getStoloviURestoranu() {
		return stoloviURestoranu;
	}

	public void setStoloviURestoranu(Set<Sto> stoloviURestoranu) {
		this.stoloviURestoranu = stoloviURestoranu;
	}




	


	
	
	
}
