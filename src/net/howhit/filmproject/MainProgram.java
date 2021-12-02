package net.howhit.filmproject;

import net.howhit.filmproject.controller.Controller;
import net.howhit.filmproject.model.Model;
import net.howhit.filmproject.view.View;
import java.io.IOException;

public class MainProgram {

	private final View view;
	private final Controller controller;
	private final Model model;
	
	public MainProgram() throws IOException {
		model = new Model();
		controller = new Controller();
		view = new View();
		
		view.injectController(controller);
		controller.injectModel(model);
		controller.injectView(view);
		model.injectController(controller);
	}
	public void run() throws IOException, ClassNotFoundException, Exception {
		view.runMainMenu();
	}
	
	
	
}
