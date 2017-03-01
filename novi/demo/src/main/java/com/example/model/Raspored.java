package com.example.model;

import java.io.Serializable;
import java.sql.Date;
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

import com.example.enumeracije.Smena;


@Entity
@Table(name="rasporedi")
public class Raspored implements Serializable{
	
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idRasporeda", unique=true, nullable=false)
	private Long id;
	
	private static final long serialVersionUID = -3755812148840403460L;
	
	@Column(name="smena", unique=false, nullable=false)
	private Smena smena;
	
	@Column(name="datumSmene", unique=false, nullable=false)
	private Date datum;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Reon reon;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Radnik konobar;

	public Raspored() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Smena getSmena() {
		return smena;
	}

	public void setSmena(Smena smena) {
		this.smena = smena;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Reon getReon() {
		return reon;
	}

	public void setReon(Reon reon) {
		this.reon = reon;
	}

	public Radnik getKonobar() {
		return konobar;
	}

	public void setKonobar(Radnik konobar) {
		this.konobar = konobar;
	}


	
	

}
