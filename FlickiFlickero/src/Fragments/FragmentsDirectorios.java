package Fragments;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;

import com.tdam2014.grupo09.R;

import Clases.Cuenta;
import Clases.Directorio;
import WebServices.ComunicacionFlickr;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentsDirectorios extends Fragment{
	
	
	public ArrayList<Directorio> directorios;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		directorios = new ArrayList<Directorio>();
        return inflater.inflate(R.layout.view_inicio, container, false);
	}
	
	public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
    protected class AsyncThread_cargarDirectorios extends AsyncTask<Void, Directorio, Void> {

        @Override
        protected Void doInBackground(Void ... voids) {
        	try {
				JSONArray jsonArray = ComunicacionFlickr.getDirectorios();
				for (int i = 0; i < jsonArray.length(); i++) {
					publishProgress(new Directorio(jsonArray.getJSONObject(i)));
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return null;
        }
        
		@Override
		protected void onProgressUpdate(Directorio... values){
			super.onProgressUpdate(values);
			directorios.add(values[0]);		
		}

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
