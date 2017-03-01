package com.example.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table(name="namirnice")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Namirnica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9205199620691721563L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime", unique = true, nullable = false)
	private String ime;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="namirnice")
	private Set<Ponuda> ponuda;
	
	public Namirnica(){}

	public Namirnica(String ime) {
		super();
		this.ime = ime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Set<Ponuda> getPonuda() {
		return ponuda;
	}

	public void setPonuda(Set<Ponuda> ponuda) {
		this.ponuda = ponuda;
	}


	
	
	
}
