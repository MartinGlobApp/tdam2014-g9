package com.tdam2014.grupo09;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import Clases.Cuenta;
import Clases.Directorio;
import WebServices.ComunicacionFlickr;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

public class Activity_Principal extends ActionBarActivity {

	public static Context contexto;
	ArrayList<Directorio> list;
	TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_principal);
		contexto = getApplicationContext();
		text = (TextView) findViewById(R.id.textView1);
		list = new ArrayList<Directorio>();
		new AsyncThread_cargarCuenta().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    protected class AsyncThread_cargarCuenta extends AsyncTask<Void, Directorio, Boolean> {

        @Override
        protected Boolean doInBackground(Void ... voids) {
    		try {
    			JSONArray jsonArray = ComunicacionFlickr.getDirectorios();
    			for (int i = 0; i < jsonArray.length(); i++) {
					publishProgress(new Directorio(jsonArray.getJSONObject(i)));
    			}
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (JSONException e) {
    			Log.e("ERROR", e.getMessage());
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
			return true;
        }
        
		@Override
		protected void onProgressUpdate(Directorio... values){
			super.onProgressUpdate(values);
			Directorio dire = values[0];
			String texto = text.getText().toString();
			texto += dire.getTitulo();
			text.setText(texto);
			list.add(dire); 
		
		}

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        }
    }
}
