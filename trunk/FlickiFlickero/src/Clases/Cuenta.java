package Clases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebServices.ComunicacionFlickr;

public class Cuenta {
	
	private String user_id;
	private List<Directorio> directorios;
	
	public Cuenta(String user_id) throws JSONException, ClientProtocolException, IOException{
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public List<Directorio> getDirectorios() {
		return directorios;
	}
}
