package com.example.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.enumeracije.StatusPonude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table(name="ponude")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ponuda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1752902469450691771L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL )
	@JsonIgnore
	private Set<Namirnica> namirnice;
	

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private MenadzerRestorana menadzerRestorana;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="ponuda")
	@JsonIgnore
	private Set<PonudaPonudjac> ponudaPonudjac;
	
	@Column(name = "status", unique = false, nullable = false)
	private StatusPonude status;
	
	@Column(name = "od", unique = false, nullable = false)
	private Date aktivnoOd;
	
	@Column(name = "do", unique = false, nullable = false)
	private Date aktivnoDo;
	
	public Ponuda(){}

	public Ponuda(Date aktivnoOd, Date aktivnoDo) {
		super();
		this.aktivnoOd = aktivnoOd;
		this.aktivnoDo = aktivnoDo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAktivnoOd() {
		return aktivnoOd;
	}

	public void setAktivnoOd(Date aktivnoOd) {
		this.aktivnoOd = aktivnoOd;
	}

	public Date getAktivnoDo() {
		return aktivnoDo;
	}

	public void setAktivnoDo(Date aktivnoDo) {
		this.aktivnoDo = aktivnoDo;
	}

	public MenadzerRestorana getMenadzerRestorana() {
		return menadzerRestorana;
	}

	public void setMenadzerRestorana(MenadzerRestorana menadzerRestorana) {
		this.menadzerRestorana = menadzerRestorana;
	}

	public Set<Namirnica> getNamirnice() {
		return namirnice;
	}

	public void setNamirnice(Set<Namirnica> namirnice) {
		this.namirnice = namirnice;
	}

	public StatusPonude getStatus() {
		return status;
	}

	public void setStatus(StatusPonude status) {
		this.status = status;
	}

	public Set<PonudaPonudjac> getPonudaPonudjac() {
		return ponudaPonudjac;
	}

	public void setPonudaPonudjac(Set<PonudaPonudjac> ponudaPonudjac) {
		this.ponudaPonudjac = ponudaPonudjac;
	}
	
	
	
}
