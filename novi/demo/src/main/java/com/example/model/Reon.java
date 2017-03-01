package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.enumeracije.TipReona;

//enum TipReona{PUSACKI_DEO, NEPUSACKI_DEO, BASTA}

@Entity
@Table(name="reoni")
public class Reon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3893110031866469461L;
	
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="id", unique=true, nullable=false)
	 private Long id;
	
	
	
	@Column(name="tipReona", unique=false, nullable=false)
	private TipReona tipReona;
	
	/*@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Radnik konobar;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Restoran restoran;*/
	
	
	Reon(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public TipReona getTipReona() {
		return tipReona;
	}

	public void setTipReona(TipReona tipReona) {
		this.tipReona = tipReona;
	}

	
	
	

}
