package net.howhit.filmproject.controller;

import net.howhit.filmproject.model.Model;
import net.howhit.filmproject.view.View;
import java.io.IOException;
import java.io.Serializable;

public class Controller implements Serializable {

	private View view;
	private Model model;
	private final FilmController filmC;
	private final DirectorController directorC;
	private final ActorController actorC;
	
	public Controller() {
		filmC = new FilmController();
		directorC = new DirectorController();
		actorC = new ActorController();
	}
	
	public String[][] getListsFilmsTable() {
		return model.getListsFilmsTable();
	}
	public String[][] getListsDirectorsTable() {
		return model.getListsDirectorsTable();
	}
	public String[][] getListsActorsTable() {
		return model.getListsActorsTable();
	}
	
	public void loadModel() throws IOException, ClassNotFoundException {
		model.loadProgram();
	}
	
	public void closeProgram() throws IOException {
		model.closeProgram();
	}
	
	public void sendViewFileError(String file) {
		view.sendFileErrorMessage(file);
	}
	
	public FilmController getFilmC() {
		return filmC;
	}
	public DirectorController getDirectorC() {
		return directorC;
	}
	public ActorController getActorC() {
		return actorC;
	}
	
	
	public void injectView(View view) {
		this.view = view;
	}
	public void exportDirectorsCol() throws IOException, Exception {
		model.exportDirectorsToCol();
	}
	public void injectModel(Model model) {
		this.model = model;
		filmC.injectModel(model);
		directorC.injectData(model);
		actorC.injectData(model);
	}
}
