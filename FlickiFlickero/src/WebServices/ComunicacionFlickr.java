package WebServices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Clases.Comentario;
import Clases.Directorio;
import Clases.Imagen;
import Complementos.Http;

public class ComunicacionFlickr {
	public static String user_id;
	
	public static boolean login(String userName, String pass){
		user_id = "";
		return true;
	}
	
	public static JSONArray getDirectorios() throws JSONException, ClientProtocolException, IOException{
		//final String strJson = readTextFile(MainActivity.contexto.getResources().openRawResource(R.raw.getdirectorios));
		ArrayList<Directorio> directorios = new ArrayList<Directorio>();
		HttpUriRequest request = new HttpGet("https://www.flickr.com/services/api/explore/flickr.photosets.getList");
		HttpParams parametros = request.getParams();
		parametros.setParameter("user_id", user_id);
		request.setParams(parametros);
		JSONObject objResponse = Http.getResponse(request);
		JSONObject jsonObject = objResponse.getJSONObject("photosets");
		return jsonObject.getJSONArray("photoset");
	}
	
	public static ArrayList<Imagen> getFotos(String photoset_id) throws JSONException, ClientProtocolException, IOException{
		//final String strJson = readTextFile(MainActivity.contexto.getResources().openRawResource(R.raw.getfotos));
		ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
		HttpUriRequest request = new HttpGet("https://www.flickr.com/services/api/explore/flickr.photosets.getPhotos");
		HttpParams parametros = request.getParams();
		parametros.setParameter("photoset_id", photoset_id);
		request.setParams(parametros);
		JSONObject objResponse = Http.getResponse(request);
		JSONObject jsonObject = objResponse.getJSONObject("photoset");
		JSONArray jsonArray = jsonObject.getJSONArray("photo");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			imagenes.add(new Imagen(jsonArray.getJSONObject(i)));
		}
		return imagenes;
		
	}
	
	public static ArrayList<Comentario> getComentarios(String photo_id) throws JSONException, ClientProtocolException, IOException{
		//final String strJson = readTextFile(MainActivity.contexto.getResources().openRawResource(R.raw.getcomentarios));
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet("https://www.flickr.com/services/api/explore/flickr.photos.comments.getList");
		HttpParams parametros = request.getParams();
		parametros.setParameter("photo_id", photo_id);
		request.setParams(parametros);
		JSONObject objResponse = Http.getResponse(request);
		JSONObject jsonObject = objResponse.getJSONObject("comments");
		JSONArray jsonArray = jsonObject.getJSONArray("comment");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			comentarios.add(new Comentario(jsonArray.getJSONObject(i)));
		}
		return comentarios;
	}
	
	private static String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {

		}
		return outputStream.toString();
	}
}
