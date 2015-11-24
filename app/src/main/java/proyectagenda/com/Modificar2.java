package proyectagenda.com;

import android.app.Activity;
import android.content.ContentValues;
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

public class Modificar2 extends Activity{
	public String nombre;
	public EditText name,tel,ema,dir;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modficacion02);
        
        
	    name=(EditText)findViewById(R.id.nombre);
	    tel=(EditText)findViewById(R.id.telefono);
	    dir=(EditText)findViewById(R.id.direccion);
	    ema=(EditText)findViewById(R.id.email); 
        
        Bundle bundle=getIntent().getExtras();
        nombre=bundle.getString("nombre");
        
        DataBaseHelper dbh=new DataBaseHelper(this);
      	SQLiteDatabase db= dbh.getWritableDatabase();
      	
      	String[] campos = new String[] {"nombre","telefono","email","direccion"};
      	String[] args = new String[] {nombre};
      	 
      	Cursor c = db.query("AgendaContactos", campos, "nombre=?", args, null, null, null);
      	 
      	//Nos aseguramos de que existe al menos un registro
      	if (c.moveToFirst()) {
      		
      	//Recorremos el cursor hasta que no haya ms registros
      	     do {
      	          String nombre = c.getString(0);
      	          String telefono = c.getString(1);
      	          String email=c.getString(2);
      	        String direccion=c.getString(3);
      	        
      	        name.setText(nombre);
      	        tel.setText(telefono);
      	        ema.setText(email);
      	        dir.setText(direccion);
      	        
      	     } while(c.moveToNext());
      	   
      	}
      	else
      	{
      	
      	Toast.makeText(getApplicationContext(), 
 	      		  "Ha ocurrido un error :(", Toast.LENGTH_LONG).show();
      	db.close();
      	}
        
    }
    
    public void modificar(View b)
    {
    	DataBaseHelper dbh=new DataBaseHelper(this);
     	SQLiteDatabase db= dbh.getWritableDatabase();
     	
     	ContentValues valores = new ContentValues();
     	valores.put("telefono",tel.getText().toString());
     	valores.put("email", ema.getText().toString());
     	valores.put("direccion", dir.getText().toString());

     	 
     	String[] args = new String[]{name.getText().toString()};
     	db.update("AgendaContactos", valores, "nombre=?", args);
     	
     	Toast.makeText(getApplicationContext(), 
	      		  "Contacto se ha modificado exitosamente!!", Toast.LENGTH_LONG).show();
     	db.close();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.layout.menu2, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent i;
        switch (item.getItemId()) {
        	
        	case R.id.item2:  Intent intent = new Intent(this,AgendaAndroidActivity.class);
        	
        	startActivity(intent);
        }
        	
switch (item.getItemId()) {
        	
        	case R.id.item3:  Intent it = new Intent(this,Modificar1.class);
        	
        	startActivity(it);
                         	break;
        }
        return true;
    }

}
