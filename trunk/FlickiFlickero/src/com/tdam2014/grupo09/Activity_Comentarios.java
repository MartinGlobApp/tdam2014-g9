package com.tdam2014.grupo09;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tdam2014.grupo09.Clases.Comentario;
import com.tdam2014.grupo09.Complementos.ComentariosAdaptador;
import com.tdam2014.grupo09.Listeners.GetComentariosListener;
import com.tdam2014.grupo09.Threads.AsyncTaskComentarios;

public class Activity_Comentarios extends Activity implements GetComentariosListener{
	
	ListView _listaComentarios;
	TextView _titulo;
	ProgressBar _progressBar;
	ComentariosAdaptador _adaptador;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_comentarios);
		_listaComentarios = (ListView) findViewById(R.id.listView_comentarios);
		_titulo = (TextView) findViewById(R.id.textView_titulo_foto);
		_titulo.setText((String)getIntent().getExtras().get("nombreImagen"));
		_progressBar = (ProgressBar) findViewById(R.id.progressBar_comentarios);
		String imagenId = (String) getIntent().getExtras().get("imagenId");
		_progressBar.setVisibility(View.VISIBLE);
		AsyncTaskComentarios task = new AsyncTaskComentarios(this);
		task.execute(imagenId);
	}

	@Override
	public void onComentariosRetrieved(ArrayList<Comentario> comentarios) {
		_progressBar.setVisibility(View.GONE);
		if(comentarios.size() > 0){
			_adaptador = new ComentariosAdaptador(getApplicationContext(), android.R.layout.simple_list_item_1 , comentarios);
			_listaComentarios.setAdapter(_adaptador);
		}else{
			Toast.makeText(getApplicationContext(), getString(R.string.Error_nohayComentarios), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onComentariosRetrieveError(Exception e) {
		_progressBar.setVisibility(View.GONE);
		Toast.makeText(getApplicationContext(), getString(R.string.Error_nohayComentarios), Toast.LENGTH_SHORT).show();
	}
}
