package net.howhit.filmproject.model;

public final class ModelConst {
	private final String FOLDER_NAME = "IMDB21";
	
	private final String FILM_BIN_NAME = "peliculas.bin";
	private final String ACTORS_BIN_NAME = "actores.bin";
	private final String DIRECTOR_BIN_NAME = "directores.bin";
	
	private final String FILM_TXT_NAME = "peliculas.txt";
	private final String ACTORS_TXT_NAME = "actores.txt";
	private final String DIRECTOR_TXT_NAME = "directores.txt";
	
	private final String FILM_HTML_NAME = "peliculas.html";
	private final String DIRECTOR_COL_NAME = "directores.col";

	private final String HTML_HEADER = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style> div{display: flex;justify-content: center;margin-top: 50px;} table, td, th {border: 1px solid black;text-align: center;vertical-align: middle;}table {border-collapse: collapse;}th {height: 70px;width: 150px;}</style></head><body><div><table><tr bgcolor=\"lightblue\"><th>TÍTULO</th><th>AÑO</th><th>DURACIÓN (minutos)</th><th>PAÍS</th><th>DIRECCIÓN</th><th>GUIONISTA</th><th>MÚSICA</th><th>REPARTO</th><th>PRODUCTORA</th><th>GÉNERO</th><th>SINOPSIS</th></tr>";
	private final String HTML_FOOTER = "</div></body></html>";
	
	public String getFolderName() {
		return FOLDER_NAME;
	}

	public String getFilmBinFileName() {
		return FILM_BIN_NAME;
	}

	public String getActorsBinFileName() {
		return ACTORS_BIN_NAME;
	}

	public String getDirectorsBinFileName() {
		return DIRECTOR_BIN_NAME;
	}
	public String getFilmTxtFileName() {
		return FILM_TXT_NAME;
	}

	public String getActorsTxtFileName() {
		return ACTORS_TXT_NAME;
	}

	public String getDirectorsTxtFileName() {
		return DIRECTOR_TXT_NAME;
	}
	
	public String getFilmHtmlFileName() {
		return FILM_HTML_NAME;
	}
	public String getDirectorColFileName() {
		return DIRECTOR_COL_NAME;
	}

	public String getHtmlHeader() {
		return HTML_HEADER;
	}

	public String getHtmlFooter() {
		return HTML_FOOTER;
	}
	
}
