package net.howhit.filmproject.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ModelDatabase {

	private HashMap<String,Film> filmsList;
	private HashMap<String,Actor> actorsList;
	private HashMap<String,Director> directorList;
	
	public ModelDatabase() throws IOException, FileNotFoundException {
		filmsList = new HashMap<>();
		actorsList = new HashMap<>();
		directorList = new HashMap<>();
	}

	public HashMap<String,Film> getFilmsList() {
		return filmsList;
	}
	public void setFilmsList(HashMap<String,Film> filmsList) {
		this.filmsList = filmsList;
	}
	public HashMap<String,Actor> getActorsList() {
		return actorsList;
	}
	public void setActorsList(HashMap<String,Actor> actorsList) {
		this.actorsList = actorsList;
	}
	public HashMap<String,Director> getDirectorList() {
		return directorList;
	}
	public void setDirectorList(HashMap<String,Director> directorList) {
		this.directorList = directorList;
	}
	
}
