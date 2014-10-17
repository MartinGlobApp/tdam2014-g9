package WebServices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tdam2014.grupo09.MainActivity;
import com.tdam2014.grupo09.R;

public class ComunicacionFlickr {
	
	public static JSONArray getDirectorios(String user_id) throws JSONException{
		final String strJson = readTextFile(MainActivity.contexto.getResources().openRawResource(R.raw.getdirectorios));
		JSONObject objResponse = new JSONObject(strJson);
		JSONObject jsonObject = objResponse.getJSONObject("photosets");
		return jsonObject.getJSONArray("photoset");
		
	}
	
	public static JSONArray getFotos(String photoset_id) throws JSONException{
		final String strJson = readTextFile(MainActivity.contexto.getResources().openRawResource(R.raw.getfotos));
		JSONObject objResponse = new JSONObject(strJson);
		JSONObject jsonObject = objResponse.getJSONObject("photoset");
		return jsonObject.getJSONArray("photo");
		
	}
	
	public static JSONArray getComentarios(String photo_id) throws JSONException{
		final String strJson = readTextFile(MainActivity.contexto.getResources().openRawResource(R.raw.getcomentarios));
		JSONObject objResponse = new JSONObject(strJson);
		JSONObject jsonObject = objResponse.getJSONObject("comments");
		return jsonObject.getJSONArray("comment");
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
