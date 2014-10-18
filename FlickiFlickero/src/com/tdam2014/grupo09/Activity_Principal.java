package com.tdam2014.grupo09;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import Clases.Cuenta;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Activity_Principal extends ActionBarActivity {

	public static Context contexto;
	TextView hello;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contexto = getApplicationContext();
		hello = (TextView) findViewById(R.id.textView_hello);
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
	
    protected class AsyncThread_cargarCuenta extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void ... voids) {
    		try {
    			Cuenta cuenta;
				cuenta = new Cuenta("128719791@N07");
    			String a = cuenta.getUser_id();
    		} catch (JSONException e) {
    			e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			return true;
        }
        
		@Override
		protected void onProgressUpdate(Integer... values){
			super.onProgressUpdate(values);

		
		}

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            hello.setText("Listo!");

        }
    }

}
