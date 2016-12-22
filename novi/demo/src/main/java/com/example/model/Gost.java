package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gosti")
public class Gost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1666304099366498432L;
	@Column(name="ime", unique=false, nullable=false)
	 private String ime;
	 @Column(name="prezime", unique=false, nullable=false)
	 private String prezime;
	 @Column(name="email", unique=true, nullable=false)
	 private String email;
	 @Column(name="korisnickoIme", unique=true, nullable=false)
	 private String korisnickoIme;
	 @Column(name="sifra", unique=false, nullable=false)
	 private String sifra;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="id", unique=true, nullable=false)
	 private Long id;
	 
	 public Gost(){
	   
	 }

	 public String getIme() {
	  return ime;
	 }
	 public void setIme(String ime) {
	  this.ime = ime;
	 }
	 public String getPrezime() {
	  return prezime;
	 }
	 public void setPrezime(String prezime) {
	  this.prezime = prezime;
	 }
	 public String getEmail() {
	  return email;
	 }
	 public void setEmail(String email) {
	  this.email = email;
	 }

	 public String getSifra() {
	  return sifra;
	 }
	 public void setSifra(String sifra) {
	  this.sifra = sifra;
	 }
	/* public ArrayList<Gost> getPrijatelji() {
	  return prijatelji;
	 }
	 public void setPrijatelji(ArrayList<Gost> prijatelji) {
	  this.prijatelji = prijatelji;
	 }*/

	 public String getKorisnickoIme() {
	  return korisnickoIme;
	 }

	 public void setKorisnickoIme(String korisnickoIme) {
	  this.korisnickoIme = korisnickoIme;
	 }
	 
	 
	}


