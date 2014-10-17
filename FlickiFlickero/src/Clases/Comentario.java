package Clases;

import org.json.JSONObject;

public class Comentario {
	
	private String id;
	private String author;
	private String authorname;
	private String comment;
	
	public Comentario(JSONObject objComentario) {
		id = objComentario.optString("id");
		author = objComentario.optString("author");
		authorname = objComentario.optString("authorname");
		comment = objComentario.optString("_content");
	}

	public String getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getAuthorname() {
		return authorname;
	}

	public String getComment() {
		return comment;
	}
}
