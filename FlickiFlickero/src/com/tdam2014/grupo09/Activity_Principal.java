package com.tdam2014.grupo09;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.tdam2014.grupo09.BaseDeDatos.ControllerBD;
import com.tdam2014.grupo09.Clases.Directorio;
import com.tdam2014.grupo09.Clases.Imagen;
import com.tdam2014.grupo09.Complementos.Http;
import com.tdam2014.grupo09.Complementos.ImagenHelper;
import com.tdam2014.grupo09.Complementos.ImagenesAdaptador;
import com.tdam2014.grupo09.Fragments.FragmentsDirectorios;
import com.tdam2014.grupo09.Listeners.GetImagenesListener;
import com.tdam2014.grupo09.Listeners.SelectDirectorioListener;
import com.tdam2014.grupo09.Threads.AsyncTaskImagenes;
@SuppressLint("NewApi")
public class Activity_Principal extends FragmentActivity implements GetImagenesListener,SelectDirectorioListener{

	public ArrayList<Imagen> _listaImagenes;
	ImagenesAdaptador _adaptador;
	TextView _titulo;
	private static Directorio _seleccionado;
	public GridView _listaFotos;
	public DrawerLayout _menuListaDirectorios;
	public static Context CONTEXTO;
	public static String photosets_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_principal);
		CONTEXTO = getApplicationContext();
		_listaFotos = (GridView) findViewById(R.id.gridView_lista_fotos);
		_menuListaDirectorios = (DrawerLayout) findViewById(R.id.drawer_lista_directorios);
		_titulo = (TextView) findViewById(R.id.textView_titulo_album);
		_menuListaDirectorios.openDrawer(Gravity.LEFT);
		
		_listaFotos.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
				try {                    
                    Imagen seleccionada = (Imagen) adapter.getItemAtPosition(position);
                    dialogoDeOpciones(seleccionada);
				}
                catch(Exception e) {
                }
			}
         });
		
		if(!Http.isOnline()){
			Toast.makeText(getApplicationContext(), getString(R.string.Mensaje_sinConexion), Toast.LENGTH_LONG).show();
		}

		Fragment fragmentDirectorios = new FragmentsDirectorios(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.lista_fragments_directorios, fragmentDirectorios);
		transaction.commit();
		
	}
	
    public void dialogoDeOpciones(final Imagen imagen){
    	
    	final CharSequence[] items = {this.getString(R.string.ver_comentarios),this.getString(R.string.ver_enlaweb), this.getString(R.string.compartir_email)};
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
            	switch(item){
            	case 0:
            		Intent intent = new Intent(Activity_Principal.CONTEXTO, Activity_Comentarios.class);
            		intent.putExtra("imagenId", imagen.getId());
            		intent.putExtra("nombreImagen", imagen.getTitulo());
            		startActivity(intent);
            		break;
            	case 1:
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(imagen.getURL(Imagen.TAMAÑO_GRANDE)));
                    startActivity(i);
            		break;
            	case 2 :
            		Intent sendIntent = new Intent(Intent.ACTION_SEND);
            		sendIntent.setType("text/plain");
            		sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.Asunto_email));
            		sendIntent.putExtra(Intent.EXTRA_TEXT, imagen.getURL(Imagen.TAMAÑO_GRANDE));
            		startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.Compartir)));
            		break;
            	}
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.ordenar_nombre) {
			_listaImagenes = new ArrayList<Imagen>();
			_adaptador = new ImagenesAdaptador(Activity_Principal.CONTEXTO, android.R.layout.simple_list_item_1 , _listaImagenes);
			_listaFotos.setAdapter(_adaptador);
			AsyncTaskImagenes taskCargarImagenes = new AsyncTaskImagenes(this, true);
			taskCargarImagenes.execute(_seleccionado.getId());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onImagenesRetrieved(ArrayList<Imagen> imagenes) {
		_menuListaDirectorios.closeDrawer(Gravity.LEFT);
		AsyncTaskCargarImagenes task = new AsyncTaskCargarImagenes();
		task.execute(imagenes);
	}

	@Override
	public void onImagenesRetrieveError(Exception e) {
		Toast.makeText(this, getResources().getString(R.string.Error_cargar), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSelectDirectorioRetrieved(Directorio directorio) {
		_seleccionado = directorio;
		_titulo.setText(directorio.getTitulo());
		_listaImagenes = new ArrayList<Imagen>();
		_adaptador = new ImagenesAdaptador(Activity_Principal.CONTEXTO, android.R.layout.simple_list_item_1 , _listaImagenes);
		_listaFotos.setAdapter(_adaptador);
		AsyncTaskImagenes taskCargarImagenes = new AsyncTaskImagenes(this, false);
		taskCargarImagenes.execute(directorio.getId());
	}
	
	public class AsyncTaskCargarImagenes extends AsyncTask<ArrayList<Imagen>, Imagen, Void> {

		@Override
		protected Void doInBackground(ArrayList<Imagen>... arg) {
			ArrayList<Imagen> imagenes = arg[0];
			for (int i = 0; i < imagenes.size(); i++) {
				Imagen imagen = imagenes.get(i);
				Drawable drawable = null;
				if(Http.isOnline()){
					drawable = new BitmapDrawable(Activity_Principal.CONTEXTO.getResources(), 
							ImagenHelper.getBitmapFromURL(imagen.getURL(Imagen.TAMAÑO_CUADRADO_PEQUEÑO)));
				}else{
					drawable = ImagenHelper.getDrawablePath(imagen.getPath());
				}
				imagen.setImagen(drawable);
				publishProgress(imagen);
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Imagen... values) {
			super.onProgressUpdate(values);
			Imagen imagen = values[0];
			_listaImagenes.add(imagen);
			_adaptador.notifyDataSetChanged();
			imagen.setPath(ImagenHelper.guardarImagen(Activity_Principal.CONTEXTO, imagen.getTitulo(), ImagenHelper.getDrawableToBitmap(imagen.getImagen())));
			ControllerBD.insertarImagenes(imagen);

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}
}
