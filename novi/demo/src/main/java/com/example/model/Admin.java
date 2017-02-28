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
@Table(name="admin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Admin extends Korisnik implements Serializable{

 /**
  * 
  */
 private static final long serialVersionUID = 5845498081747537893L;

 
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", unique=true, nullable=false)
  private Long id;
  
  public Admin(){}
  
  

 public Admin(String ime, String prezime, String email, String korisnickoIme, String sifra,
   VrsteKorisnika vrstaKorisnika) {
  super(ime, prezime, email, korisnickoIme, sifra, vrstaKorisnika);
  // TODO Auto-generated constructor stub
 }



 /*public Admin(String ime, String prezime, String email, String korisnickoIme, String sifra, Long id) {
  super();
  this.ime = ime;
  this.prezime = prezime;
  this.email = email;
  this.korisnickoIme = korisnickoIme;
  this.sifra = sifra;
  this.id=id;
 }
*/

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }
  
  
 
}