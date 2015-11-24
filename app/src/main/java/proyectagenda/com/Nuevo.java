package proyectagenda.com;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Nuevo extends Activity{
	public EditText nombre,telefono,email,direccion;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.agregarususario);
       
    	
    	
    	nombre=(EditText)findViewById(R.id.nombre);
	    telefono=(EditText)findViewById(R.id.telefono);
	    direccion=(EditText)findViewById(R.id.direccion);
	    email=(EditText)findViewById(R.id.email); 
    	
    }
    
    private void insert(String nombre, String telefono, String email, String direccion)
	
	{
    	DataBaseHelper dbh=new DataBaseHelper(this);
    	SQLiteDatabase db= dbh.getWritableDatabase();
    	
    	ContentValues cv=new ContentValues();
    	
    	cv.put(DataBaseHelper.NOMBRE, nombre);
    	cv.put(DataBaseHelper.TELEFONO, telefono);
    	cv.put(DataBaseHelper.EMAIL, email);
    	cv.put(DataBaseHelper.DIRECCION, direccion);
    	
    	
    	
    	db.insert("AgendaContactos",DataBaseHelper.NOMBRE,cv);
    	db.close();
		
	}
    
    public void agregar(View b)
    {
    	insert(nombre.getText().toString(),telefono.getText().toString(),email.getText().toString(),direccion.getText().toString());
    	Toast.makeText(getApplicationContext(), 
	      		  "Contacto agregado correctamente", Toast.LENGTH_LONG).show();
	      nombre.setText("");
	      email.setText("");
	      direccion.setText("");
	      telefono.setText("");
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
