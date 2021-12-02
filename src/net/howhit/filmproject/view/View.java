package net.howhit.filmproject.view;

import com.coti.tools.Esdia;
import net.howhit.filmproject.controller.Controller;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class View implements Serializable {
	
	private Controller controller;
	private final ViewConst viewConst;
	
	private final FilmsView filmsView;
	private final DirectorView directorView;
	private final ActorsView actorsView;
	private final ListsView listsView;
	
	public View() {
		viewConst = new ViewConst();
		
		filmsView = new FilmsView();
		directorView = new DirectorView();
		actorsView = new ActorsView();
		listsView = new ListsView();
	}
	
	public void runMainMenu() throws IOException, ClassNotFoundException, Exception {
		controller.loadModel();
		String option;
		mainLoop: while (true) {
			clearConsole();
			for (String message : viewConst.getMainMenuMesssage()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opción válida: ", viewConst.getMainOptions()).toUpperCase();
			switch (option) {
				case "1" -> runFilesMenu();
				case "2" -> filmsView.runFilmsMenu();
				case "3" -> directorView.runDirectorMenu();
				case "4" -> actorsView.runActorsMenu();
				case "5" -> listsView.runListsMenu();
				case "S" -> {
					System.out.println("");
					System.out.println("Cerrando todos los procesos de BESTV...");
					controller.closeProgram();
					break mainLoop;
				}
			}
			pressEnterToContinue();
		}
	}
	
	private void runFilesMenu() throws IOException, Exception {
		clearConsole();
		String option;
		filesLoop: while (true) {
			for (String message : viewConst.getFilesMenuMesssage()) {
				System.out.println(message);
			}
			option = Esdia.readString("Introduce una opcion válida: ", viewConst.getFilesOptions()).toUpperCase();
			switch (option) {
				case "1" -> {
					if (!controller.getDirectorC().isDirectorDbEmpty()) {
						System.out.println("Exportando directores a formato COL...");
						controller.exportDirectorsCol();
					} else {
						System.out.println("La base de datos de directores esta vacía. No se ha podido exportar nada.");
					}
				}
				case "2" -> {
					if (!controller.getFilmC().isFilmsDbEmpty()) {
						System.out.println("Exportando películas a formato HTML...");
						controller.getFilmC().exportHtml();
					} else {
						System.out.println("La base de datos de películas esta vacía. No se ha podido exportar nada.");
					}

				}
				case "V" -> {
					System.out.println("Has salido del menu de archivos correctamente.");
					break filesLoop;
				}
			}
		}
	}

	
	public void sendFileErrorMessage(String file) {
		System.out.println("ERROR: Hay un problema con el archivo: "+file+" por lo que no se puede ejecutar el programa.");
		System.exit(0);
	}
	
	public void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("");
	}
	public void pressEnterToContinue() {
		System.out.println("Presiona \"ENTER\" para continuar...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
	
	public FilmsView getFilmsView() {
		return filmsView;
	}
	public DirectorView getDirectorView() {
		return directorView;
	}
	public ActorsView getActorsView() {
		return actorsView;
	}
	public ListsView getListsView() {
		return listsView;
	}
	
	public void injectController(Controller controller) {
		this.controller = controller;
		
		filmsView.injectData(controller, this, viewConst);
		directorView.injectData(controller, this, viewConst);
		actorsView.injectData(controller, this, viewConst);
		listsView.injectData(controller, this, viewConst);
	}

}
