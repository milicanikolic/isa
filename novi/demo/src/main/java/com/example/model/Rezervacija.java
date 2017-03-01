package com.example.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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

import com.example.enumeracije.StatusRezervacije;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="rezervacije")
public class Rezervacija implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3660790878317332619L;
	
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  @Column(name="id", unique=true, nullable=false)
	  private Long id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	@JsonIgnore
	private Gost gost;
	
	@OneToOne(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	@JsonIgnore
	private Restoran restoran;
	
	@Column(name="datumVreme", unique=false, nullable=false)
	private Date datum;
	
	@Column(name="vremeTrajanja", unique=false, nullable=false)
	private int vremeTrajanja;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	@JsonIgnore
	private Sto rezervisanSto;
	
	@Column(name="statusRez", unique=false, nullable=false)
	private StatusRezervacije statusRez;
	
	@Column(name="vremeRez", unique=false, nullable=false)
	private String vreme;
	

	public int getVremeTrajanja() {
		return vremeTrajanja;
	}

	public void setVremeTrajanja(int vremeTrajanja) {
		this.vremeTrajanja = vremeTrajanja;
	}

	public Rezervacija() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datumVreme) {
		this.datum = datumVreme;
	}

	public Sto getRezervisanSto() {
		return rezervisanSto;
	}

	
	
	public void setRezervisanSto(Sto rezervisanSto) {
		this.rezervisanSto = rezervisanSto;
	}

	public StatusRezervacije getStatusRez() {
		return statusRez;
	}

	public void setStatusRez(StatusRezervacije statusRez) {
		this.statusRez = statusRez;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	

}
