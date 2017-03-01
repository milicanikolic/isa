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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.enumeracije.StatusSto;
import com.example.enumeracije.TipReona;

@Entity
@Table(name="stolovi")
public class Sto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2808024538155703909L;
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="id", unique=true, nullable=false)
	 private Long id;
	
	@Column(name="brojStola", unique=false, nullable=false)
	private int brojStola;
	
	@Column(name="tipReona", unique=false, nullable=false)
	private TipReona tipReona;
	
	@Column(name="vrsta", unique=false, nullable=true)
	private int i;
	
	@Column(name="kolona", unique=false, nullable=true)
	private int j;
	
	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="rezervisanSto")
	private Set<Rezervacija> rezervacijeStola=new HashSet<Rezervacija>();
	
	//@ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	//private Set<Konobar> konobari=new HashSet<Konobar>();
	
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	private Restoran restoran;
	
	@Column(name="statusSto", unique=false, nullable=false)
	private StatusSto status;
	
	
	
/*	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Restoran restoran;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Radnik konobar;*/
	
	
	
	public Set<Rezervacija> getRezervacijeStola() {
		return rezervacijeStola;
	}



	public void setRezervacijeStola(Set<Rezervacija> rezervacijeStola) {
		this.rezervacijeStola = rezervacijeStola;
	}



	Sto(){}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public int getBrojStola() {
		return brojStola;
	}



	public void setBrojStola(int brojStola) {
		this.brojStola = brojStola;
	}



	public TipReona getTipReona() {
		return tipReona;
	}



	public void setTipReona(TipReona tipReona) {
		this.tipReona = tipReona;
	}



	public int getI() {
		return i;
	}



	public void setI(int i) {
		this.i = i;
	}



	public int getJ() {
		return j;
	}



	public void setJ(int j) {
		this.j = j;
	}



	



	/*public Set<Konobar> getKonobari() {
		return konobari;
	}



	public void setKonobari(Set<Konobar> konobari) {
		this.konobari = konobari;
	}*/



	public Restoran getRestoran() {
		return restoran;
	}



	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}



	public StatusSto getStatus() {
		return status;
	}



	public void setStatus(StatusSto status) {
		this.status = status;
	}

	

}
