package proyectagenda.com;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarContacto extends Activity{
	EditText nombre;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminarcontacto);
        
        nombre=(EditText)findViewById(R.id.nombre);
        
       
    	
        
    }
    
    public void eliminar(View b)
    {
    	 DataBaseHelper dbh=new DataBaseHelper(this);
     	SQLiteDatabase db= dbh.getWritableDatabase();
     	
     	String[] args = new String[]{nombre.getText().toString()};
     	db.execSQL("DELETE FROM AgendaContactos WHERE nombre=?", args);
     	Toast.makeText(getApplicationContext(), 
	      		  "Contacto eliminado con exito.!", Toast.LENGTH_LONG).show();
     	db.close();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.layout.menu1, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent i;
        switch (item.getItemId()) {
        	
        	case R.id.item2:  Intent intent = new Intent(this,AgendaAndroidActivity.class);
        	
        	startActivity(intent);
                         	break;
        }
        return true;
    }

}
