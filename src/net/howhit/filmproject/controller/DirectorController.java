package net.howhit.filmproject.controller;

import net.howhit.filmproject.model.Model;
import java.util.ArrayList;

public class DirectorController {

	private Model model;
	
	public void updateDate(String name, String date) {
		model.updateDirectorDate(name, date);
	}
	public void updateCountry(String name, String country) {
		model.updateDirectorCountry(name, country);
	}
	public void updateOccupation(String name, String occupation) {
		model.updateDirectorOccupation(name, occupation);
	}
	public boolean isDirectorDbEmpty() {
		return model.isDirectorDbEmpty();
	}

	
	public void removeDirector(String name) {
		model.removeDirector(name);
	}
	public void putNewDirector(String name, String date, String country, String occupation, ArrayList<String> films) {
		model.putNewDirector(name, date, country, occupation, films);
	}
	public boolean directorDbContains(String name) {
		return model.directorDbContains(name);
	}
	
	public void injectData(Model model) {
		this.model = model;
	}
	
}
