package net.howhit.filmproject.model;

import com.coti.tools.OpMat;
import com.coti.tools.Rutas;
import net.howhit.filmproject.controller.Controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UTFDataFormatException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Model implements Serializable {

	private Controller controller;
	private final ModelConst modelConst;
	private final ModelDatabase modelDatabase;
	
	public Model() throws IOException, FileNotFoundException {
		modelConst = new ModelConst();
		modelDatabase = new ModelDatabase();
	}
	
	public void loadProgram() throws IOException, ClassNotFoundException {
		File dbFolder = Rutas.pathToFolderOnDesktop(modelConst.getFolderName()).toFile();
		if (!dbFolder.exists()) {
			dbFolder.mkdir();
		}
		readFilmFile();
		readActorsFile();
		readDirectorsFile();
	}
	
	public void closeProgram() throws FileNotFoundException, IOException {
		File file;
		ObjectOutputStream oos;
		
		//films
		file = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getFilmBinFileName()).toFile();
		oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		oos.writeObject(modelDatabase.getFilmsList());
		oos.close();
		//directors
		file = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getDirectorsBinFileName()).toFile();
		oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		oos.writeObject(modelDatabase.getDirectorList());
		oos.close();
		//actors
		file = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getActorsBinFileName()).toFile();
		oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		oos.writeObject(modelDatabase.getActorsList());
		oos.close();
	}
	
	public void exportDirectorsToCol() throws IOException, Exception {
		File colFile = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getDirectorColFileName()).toFile();
		
		String[][] mat = new String[modelDatabase.getDirectorList().size()][5];
		
		int j = 0;
		for (Director director : modelDatabase.getDirectorList().values()) {
			mat[j][0] = director.getName();
			mat[j][1] = director.getBornDate();
			mat[j][2] = director.getNationality();
			mat[j][3] = director.getOccupation();
			mat[j][4] = director.getFilms().toString();
			j++;
		}
		
		OpMat.exportToDisk(mat, colFile, "#");
	}
	
	public void exportFilmsToHtml() throws IOException {
		Path htmlFile = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getFilmHtmlFileName());
		
		List<String> data = new ArrayList<>();
		data.add(modelConst.getHtmlHeader());
		data.add(getFilmsHtml());
		data.add(modelConst.getHtmlFooter());
		
		Files.write(htmlFile, data, Charset.forName("UTF-8"));
	}
	
	private String getFilmsHtml() {
		StringBuilder str = new StringBuilder();
		if (modelDatabase.getFilmsList().isEmpty()) {
			str.append("<h1>NO HAY NINGUNA PELÍCULA REGISTRADA EN LA APLICACIÓN</h1>");
		}
		for (Film film : modelDatabase.getFilmsList().values()) {
			str.append("<tr><td width=\"10%\">")
				.append(film.getTitle())
				.append("</td><td width=\"4%\">")
				.append(film.getYear())
				.append("</td><td width=\"4%\">")
				.append(film.getDuration())
				.append("</td><td width=\"9%\">")
				.append(film.getCountry())
				.append("</td><td width=\"9%\">");
			film.getDirection().forEach(director -> {
				str.append(director).append(" ");
			});
			str.append("</td><td width=\"9%\">")
				.append(film.getScreenWriter())
				.append("</td><td width=\"7%\">")
				.append(film.getMusic())
				.append("</td><td width=\"12%\">");
			film.getActors().forEach(actor -> {
				str.append(actor).append(" ");
			});
			str.append("</td><td width=\"9%\">")
				.append(film.getProducer())
				.append("</td><td width=\"4%\">")
				.append(film.getGenre().toUpperCase())
				.append("</td><td width=\"20%\">")
				.append(film.getSynopsis())
				.append("</td><tr>");
		}
		str.append("</table>");
		return str.toString();
	}
	
	private void readFilmFile() throws IOException, ClassNotFoundException {
		File films = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getFilmBinFileName()).toFile();
		if (!isFile(films)) {
			films = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getFilmTxtFileName()).toFile();
			if (!isFile(films)) {
				controller.sendViewFileError(modelConst.getFilmTxtFileName());
			} else {
				 List<String> lines = Files.readAllLines(films.toPath(), StandardCharsets.UTF_8);
				 for(String s : lines) { 
					String[] v = s.split("#");
					int year = -1;
					if (isNumber(v[1])) {
						year = Integer.parseInt(v[1]);
					}
					int duration = -1;
					if (isNumber(v[2])) {
						duration = Integer.parseInt(v[2]);
					}
					ArrayList<String> directors = new ArrayList<>();
					directors.addAll(Arrays.asList(v[4].split("\t")));
					ArrayList<String> actors = new ArrayList<>();
					actors.addAll(Arrays.asList(v[7].split("\t")));
					Film filmObject = new Film(v[0],year, duration, v[3], directors , v[5], v[6], actors,
						v[8], v[9], v[10]);
					modelDatabase.getFilmsList().put(v[0].toUpperCase(), filmObject);
				 }
			}
		} else {
			try {
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(films)));
				HashMap<String,Film> list = (HashMap<String,Film>) ois.readObject();
				modelDatabase.setFilmsList(list);
			} catch (UTFDataFormatException|StreamCorruptedException e) {
				controller.sendViewFileError(modelConst.getFilmBinFileName());
			}
		}
	}
	private void readActorsFile() throws IOException, ClassNotFoundException {
		File actors = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getActorsBinFileName()).toFile();
		if (!isFile(actors)) {
			actors = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getActorsTxtFileName()).toFile();
			if (!isFile(actors)) {
				controller.sendViewFileError(modelConst.getActorsTxtFileName());
			} else {
				 List<String> lines = Files.readAllLines(actors.toPath(), StandardCharsets.UTF_8);
				 for(String s : lines) {
					String[] v = s.split("#");
					int year = -1;
					if (isNumber(v[3])) {
						year = Integer.parseInt(v[3]);
					}
					ArrayList<String> films = new ArrayList<>();
					films.addAll(Arrays.asList(v[4].split("\t")));
					
					Actor actorObject = new Actor(v[0], v[1], v[2], year, films);
					modelDatabase.getActorsList().put(v[0].toUpperCase(), actorObject);
					 
				 }
			}
		} else {
			try {
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(actors)));
				HashMap<String,Actor> list = (HashMap<String,Actor>) ois.readObject();
				modelDatabase.setActorsList(list);
			} catch (UTFDataFormatException|StreamCorruptedException e) {
				controller.sendViewFileError(modelConst.getActorsBinFileName());
			}
		}
	}
	private void readDirectorsFile() throws IOException, ClassNotFoundException {
		File directors = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getDirectorsBinFileName()).toFile();
		if (!isFile(directors)) {
			directors = Rutas.pathToFileInFolderOnDesktop(modelConst.getFolderName(), modelConst.getDirectorsTxtFileName()).toFile();
			if (!isFile(directors)) {
				controller.sendViewFileError(modelConst.getDirectorsTxtFileName());
			} else {
				 List<String> lines = Files.readAllLines(directors.toPath(), StandardCharsets.UTF_8);
				 for(String s : lines) {
					String[] v = s.split("#");
					
					ArrayList<String> films = new ArrayList<>();
					films.addAll(Arrays.asList(v[4].split("\t")));
					
					Director directorObject = new Director(v[0], v[1], v[2], v[3], films);
					modelDatabase.getDirectorList().put(v[0].toUpperCase(), directorObject);
				 }
			}
		} else {
			try {
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(directors)));
				HashMap<String,Director> list = (HashMap<String,Director>) ois.readObject();
				modelDatabase.setDirectorList(list);
			} catch (UTFDataFormatException|StreamCorruptedException e) {
				controller.sendViewFileError(modelConst.getDirectorsBinFileName());
			}

		}
	}
	
	private boolean isNumber(String str) {
		if (str == null) {
			return false;
		}
		try {
			int value = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	//ADD FILM TO DB
	public void putNewFilm(String title, int year, int duration, String country,
		ArrayList<String> direction, String screenWriter,
		String music, ArrayList<String> actors, String producer,
		String genre, String sinopsis) {
		
		modelDatabase.getFilmsList().put(title.toUpperCase(), new Film(title, year, duration, country, direction, screenWriter, music, actors, producer, genre, sinopsis));
		
		for (String actor : actors) {
			if (modelDatabase.getActorsList().containsKey(actor.toUpperCase())) {
				Actor actorObject = modelDatabase.getActorsList().get(actor.toUpperCase());
				boolean containsFilm = false;
				for (String s : actorObject.getFilms()) {
					if (title.equalsIgnoreCase(s)) {
						containsFilm = true;
						break;
					}
				}
				if (!containsFilm) {
					actorObject.getFilms().add(title);
				}
			} else {
				ArrayList<String> aFilms = new ArrayList<>();
				aFilms.add(title);
				modelDatabase.getActorsList().put(actor.toUpperCase(), new Actor(actor, "", "", -1, aFilms));
			}
		}
	
		for (String director : direction) {
			if (modelDatabase.getActorsList().containsKey(director.toUpperCase())) {
				Director directorObject = modelDatabase.getDirectorList().get(director.toUpperCase());
				if (directorObject != null) {
					boolean containsFilm = false;
					for (String s : directorObject.getFilms()) {
						if (title.equalsIgnoreCase(s)) {
							containsFilm = true;
							break;
						}
					}
					if (!containsFilm) {
						directorObject.getFilms().add(title);
					}
				} else {
					ArrayList<String> aFilms = new ArrayList<>();
					aFilms.add(title);
					modelDatabase.getDirectorList().put(director.toUpperCase(), new Director(director, "", "", "", aFilms));
				}
			} else {
				ArrayList<String> aFilms = new ArrayList<>();
				aFilms.add(title);
				modelDatabase.getDirectorList().put(director.toUpperCase(), new Director(director, "", "", "", aFilms));
			}
		}
	}
	
	//REMOVE FILM DB
	public void removeFilm(String title) {
		modelDatabase.getFilmsList().remove(title.toUpperCase());
		for (Actor actor : modelDatabase.getActorsList().values()) {
			actor.getFilms().removeIf(film -> film.equalsIgnoreCase(title));
			//concurrent exception
			/*for (String film : actor.getFilms()) {
				if (title.equalsIgnoreCase(film)) {
					actor.getFilms().remove(film);
				}
			}*/
		}
		for (Director director : modelDatabase.getDirectorList().values()) {
			director.getFilms().removeIf(film -> film.equalsIgnoreCase(title));
			//concurrent exception
			/*for (String film : director.getFilms()) {
				if (title.equalsIgnoreCase(film)) {
					director.getFilms().remove(film);
				}
			}*/
		}
	}
	
	//ADD DIRECTOR DB
	public void putNewDirector(String name, String date, String country, String occupation, ArrayList<String> films) {
		modelDatabase.getDirectorList().put(name.toUpperCase(), new Director(name, date, country, occupation, films));
	}
	
	//ADD ACTOR DB
	public void putNewActor(String name, String date, String country, int debut, ArrayList<String> films) {
		modelDatabase.getActorsList().put(name.toUpperCase(), new Actor(name, date, country, debut, films));
	}
	
	//REMOVE DIRECTOR DB
	public void removeDirector(String name) {
		Director director = modelDatabase.getDirectorList().get(name.toUpperCase());
		boolean canBeRemoved = true;
		for (String s : director.getFilms()) {
			if (modelDatabase.getFilmsList().containsKey(s.toUpperCase())) {
				canBeRemoved = false;
				break;
			}
		}
		if (canBeRemoved) {
			modelDatabase.getDirectorList().remove(name.toUpperCase());
		}
	}
	
	//REMOVE ACTOR DB
	public void removeActor(String name) {
		Actor actor = modelDatabase.getActorsList().get(name.toUpperCase());
		boolean canBeRemoved = true;
		for (String s : actor.getFilms()) {
			if (modelDatabase.getFilmsList().containsKey(s.toUpperCase())) {
				canBeRemoved = false;
				break;
			}
		}
		if (canBeRemoved) {
			modelDatabase.getActorsList().remove(name.toUpperCase());
		}
	}
	
	public String[][] actorsGetFilmsTable(String name) {
		Actor actor = modelDatabase.getActorsList().get(name.toUpperCase());
		List<Film> films = new ArrayList<>();
		for (String filmStr : actor.getFilms()) {
			Film film = modelDatabase.getFilmsList().get(filmStr.toUpperCase());
			if (film != null) {
				films.add(film);
			}
		}
		Collections.sort(films);
		String[][] table = new String[films.size()+1][5];
		table[0][0] = "TÍTULO";
		table[0][1] = "AÑO";
		table[0][2] = "DURACIÓN";
		table[0][3] = "PAÍS";
		table[0][4] = "GÉNERO";
		int j = 1;
		for (Film film : films) {
			table[j][0] = film.getTitle();
			table[j][1] = Integer.toString(film.getYear());
			table[j][2] = Integer.toString(film.getDuration());
			table[j][3] = film.getCountry();
			table[j][4] = film.getGenre();
			j++;
		}
		return table;
	}
	public String[][] getListsFilmsTable() {
		String[][] table = new String[modelDatabase.getFilmsList().size()+1][5];
		table[0][0] = "TÍTULO";
		table[0][1] = "AÑO";
		table[0][2] = "DURACIÓN";
		table[0][3] = "PAÍS";
		table[0][4] = "GÉNERO";
		int j = 1;
		List<Film> films = new ArrayList<>();
		for (Film film : modelDatabase.getFilmsList().values()) {
			films.add(film);
		}
		
		Collator collator = Collator.getInstance(Locale.US);
		Collections.sort(films, (Film c1, Film c2) -> collator.compare(c1.getTitle(), c2.getTitle()));
		
		for (Film film : films) {
			table[j][0] = film.getTitle();
			table[j][1] = Integer.toString(film.getYear());
			table[j][2] = Integer.toString(film.getDuration());
			table[j][3] = film.getCountry();
			table[j][4] = film.getGenre();
			j++;
		}
		return table;
	}
	public String[][] getListsDirectorsTable() {
		String[][] table = new String[modelDatabase.getDirectorList().size()+1][4];
		table[0][0] = "NOMBRE";
		table[0][1] = "CUMPLEAÑOS";
		table[0][2] = "NACIONALIDAD";
		table[0][3] = "OCUPACIÓN";
		int j = 1;
		List<Director> directors = new ArrayList<>();
		for (Director director : modelDatabase.getDirectorList().values()) {
			directors.add(director);
		}
		Collator collator = Collator.getInstance(Locale.US);
		Collections.sort(directors, (Director d1, Director d2) -> {
			int value = collator.compare(d1.getNationality(), d2.getNationality());
			if (value == 0) {
				return collator.compare(d1.getBornDate(), d2.getBornDate());
			}
			return value;
		});
		
		for (Director director : directors) {
			table[j][0] = director.getName();
			table[j][1] = director.getBornDate();
			table[j][2] = director.getNationality();
			table[j][3] = director.getOccupation();
			j++;
		}
		return table;
	}
	public String[][] getListsActorsTable() {
		String[][] table = new String[modelDatabase.getActorsList().size()+1][4];
		table[0][0] = "NOMBRE";
		table[0][1] = "CUMPLEAÑOS";
		table[0][2] = "NACIONALIDAD";
		table[0][3] = "DEBUT";
		int j = 1;
		List<Actor> actors = new ArrayList<>();
		for (Actor actor : modelDatabase.getActorsList().values()) {
			actors.add(actor);
		}
		Collator collator = Collator.getInstance(Locale.US);
		Collections.sort(actors, (Actor a1, Actor a2) -> {
			int value = collator.compare(Integer.toString(a1.getDebutYear()), Integer.toString(a2.getDebutYear()));
			if (value == 0) {
				return collator.compare(a1.getName(), a2.getName());
			}
			return value;
		});
		
		for (Actor actor : actors) {
			table[j][0] = actor.getName();
			table[j][1] = actor.getBornDate();
			table[j][2] = actor.getNationality();
			if (actor.getDebutYear() == -1) {
				table[j][3] = "";
			} else {
				table[j][3] = Integer.toString(actor.getDebutYear());
			}
			j++;
		}
		return table;
	}
	
	private boolean isFile(File file) {
		return file.exists() && file.isFile();
	}
	
	//CONTAINS DB CHECKS
	public boolean filmsDbContains(String title) {
		return modelDatabase.getFilmsList().containsKey(title.toUpperCase());
	}
	public boolean directorDbContains(String name) {
		return modelDatabase.getDirectorList().containsKey(name.toUpperCase());
	}
	public boolean actorsDbContains(String name) {
		return modelDatabase.getActorsList().containsKey(name.toUpperCase());
	}
	
	//EMPTY DB CHECKS
	public boolean isFilmsDbEmpty() {
		return modelDatabase.getFilmsList().isEmpty();
	}
	public boolean isDirectorDbEmpty() {
		return modelDatabase.getDirectorList().isEmpty();
	}
	public boolean isActorsDbEmpty() {
		return modelDatabase.getActorsList().isEmpty();
	}
	
	//UPDATE DIRECTORS DATA
	public void updateDirectorDate(String name, String date) {
		modelDatabase.getDirectorList().get(name.toUpperCase()).setBornDate(date);
	}
	public void updateDirectorCountry(String name, String country) {
		modelDatabase.getDirectorList().get(name.toUpperCase()).setNationality(country);
	}
	public void updateDirectorOccupation(String name, String occupation) {
		modelDatabase.getDirectorList().get(name.toUpperCase()).setOccupation(occupation);
	}
	
	//UPDATE ACTORS DATA
	public void updateActorDate(String name, String date) {
		modelDatabase.getActorsList().get(name.toUpperCase()).setBornDate(date);
	}
	public void updateActorCountry(String name, String country) {
		modelDatabase.getActorsList().get(name.toUpperCase()).setNationality(country);
	}
	public void updateActorDebut(String name, int debut) {
		modelDatabase.getActorsList().get(name.toUpperCase()).setDebut(debut);
	}
	
	//UPDATE FILMS DATA
	public void updateFilmYear(String film, int year) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setYear(year);
	}
	public void updateFilmDuration(String film, int duration) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setDuration(duration);
	}
	public void updateFilmCountry(String film, String country) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setCountry(country);
	}
	public void updateFilmScreenWritter(String film, String screenWritter) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setScreenWriter(screenWritter);
	}
	public void updateFilmMusic(String film, String music) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setMusic(music);
	}
	public void updateFilmProducer(String film, String producer) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setProducer(producer);
	}
	public void updateFilmGenre(String film, String genre) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setGenre(genre);
	}
	public void updateFilmSinopsis(String film, String sinopsis) {
		modelDatabase.getFilmsList().get(film.toUpperCase()).setSynopsis(sinopsis);
	}
	
	//RETURN ALL FILM DATA
	public String getFilmData(String film) {
		return modelDatabase.getFilmsList().get(film.toUpperCase()).dataToString();
	}
	
	public void injectController(Controller controller) {
		this.controller = controller;
	}
}
