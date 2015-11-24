package proyectagenda.com;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar1 extends Activity{
	public EditText nombre;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificarcontacto);
        
        nombre=(EditText)findViewById(R.id.nombre);
    }
    
    public void modificar(View b)
    {
    	 DataBaseHelper dbh=new DataBaseHelper(this);
      	SQLiteDatabase db= dbh.getWritableDatabase();
      	
      	String[] campos = new String[] {"nombre"};
      	String[] args = new String[] {nombre.getText().toString()};
      	 
      	Cursor c = db.query("AgendaContactos", campos, "nombre=?", args, null, null, null);
      	 
      	//Nos aseguramos de que existe al menos un registro
      	if (c.moveToFirst()) {
      	    Intent it=new Intent(this,Modificar2.class);
      	    it.putExtra("nombre", nombre.getText().toString());
      	    startActivity(it);
      	}
      	else
      	{
      	
      	Toast.makeText(getApplicationContext(), 
 	      		  "El contacto especificado no existe.!", Toast.LENGTH_LONG).show();
      	db.close();
      	}
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
