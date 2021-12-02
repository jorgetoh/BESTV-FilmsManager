package net.howhit.filmproject.view;

import com.coti.tools.Esdia;
import com.coti.tools.OpMat;
import net.howhit.filmproject.controller.Controller;

public class ListsView {

	private Controller controller;
	private ViewConst viewConst;
	private View view;
	
	public void runListsMenu() throws Exception {
		view.clearConsole();
		String option;
		filmsLoop: while (true) {

			for (String message : viewConst.getListsMenuMesssage()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion valida: ", viewConst.getListsOptions()).toUpperCase();
			switch (option) {
				case "1" -> printFilms();
				case "2" -> printDirectors();
				case "3" -> printActors();
				case "V" -> {
					System.out.println("Has salido del menu de directores correctamente.");
					break filmsLoop;
				}
			}
		}
	}
	
	private void printFilms() throws Exception {
		view.clearConsole();
		if (controller.getFilmC().isFilmsDbEmpty()) {
			System.out.println("La base de datos de películas esta vacía. No se puede mostrar ningúna película.");
			return;
		}
		System.out.println("");
		System.out.println("Los resultados de la petición están ordenados alfabéticamente.");
		System.out.println("");
		OpMat.printToScreen3(controller.getListsFilmsTable());
		System.out.println("");
	}
	private void printDirectors() throws Exception {
		view.clearConsole();
		if (controller.getDirectorC().isDirectorDbEmpty()) {
			System.out.println("La base de datos de directores esta vacía. No se puede mostrar ningún director.");
			return;
		}
		System.out.println("");
		System.out.println("Los resultados de la petición están ordenados por nacionalidad y año de nacimiento.");
		System.out.println("");
		OpMat.printToScreen3(controller.getListsDirectorsTable());
		System.out.println("");
	}
	private void printActors() throws Exception {
		view.clearConsole();
		if (controller.getActorC().isActorsDbEmpty()) {
			System.out.println("La base de datos de actores esta vacía. No se puede mostrar ningún actor.");
			return;
		}
		System.out.println("");
		System.out.println("Los resultados de la petición están ordenados por año de debut y nombre.");
		System.out.println("");
		OpMat.printToScreen3(controller.getListsActorsTable());
		System.out.println("");
	}
	
	public void injectData(Controller controller, View view, ViewConst viewConst) {
		this.controller = controller;
		this.viewConst = viewConst;
		this.view = view;
	}

}
