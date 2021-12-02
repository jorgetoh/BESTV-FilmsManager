package net.howhit.filmproject.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Director implements Serializable {
	
	private final String name;
	private String bornDate;
	private String nationality;
	private String occupation;
	private final ArrayList<String> films;
	
	public Director(String name, String bornDate, String nationality, String occupation, ArrayList<String> films) {
		this.name = name;
		this.bornDate = bornDate;
		this.nationality = nationality;
		this.occupation = occupation;
		this.films = films;
	}

	public String getName() {
		return name;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public ArrayList<String> getFilms() {
		return films;
	}
}
