package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.enumeracije.VrsteKorisnika;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="gosti")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gost extends Korisnik implements Serializable{

 /**
  * 
  */
 private static final long serialVersionUID = 1666304099366498432L;
 
  @Column(name="aktiviran", unique=false, nullable=false)
  private boolean aktiviran=false;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", unique=true, nullable=false)
  private Long id;
  
  public Gost(){
    
  }

 
 public Gost(String ime, String prezime, String email, String korisnickoIme, String sifra,
   VrsteKorisnika vrstaKorisnika) {
  super(ime, prezime, email, korisnickoIme, sifra, vrstaKorisnika);
  // TODO Auto-generated constructor stub
 }

 public boolean isAktiviran() {
  return aktiviran;
 }

 public void setAktiviran(boolean aktiviran) {
  this.aktiviran = aktiviran;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }
  
  
 }