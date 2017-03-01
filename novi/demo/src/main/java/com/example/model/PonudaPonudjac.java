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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.enumeracije.StatusPonudePonudjac;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="ponudaPonudjac")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PonudaPonudjac implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="cena", unique=false, nullable=false)
	private double cena;
	
	@Column(name="rokIsporuke", unique=false, nullable=false)
	private int rokIsporuke;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private Ponudjac ponudjac;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private Ponuda ponuda;
	
	@Column(name="status", unique=false, nullable=false)
	private StatusPonudePonudjac status;
	
	public PonudaPonudjac() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(int rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public StatusPonudePonudjac getStatus() {
		return status;
	}

	public void setStatus(StatusPonudePonudjac status) {
		this.status = status;
	}

	public Ponudjac getPonudjac() {
		return ponudjac;
	}

	public void setPonudjac(Ponudjac ponudjac) {
		this.ponudjac = ponudjac;
	}

	public Ponuda getPonuda() {
		return ponuda;
	}

	public void setPonuda(Ponuda ponuda) {
		this.ponuda = ponuda;
	}
	
	
	
}
