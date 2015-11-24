package proyectagenda.com;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Detalle extends Activity{
	public String nombre;
	public TextView name,tel,ema,dir;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle01);
        
        name=(TextView)findViewById(R.id.nombre);
	    tel=(TextView)findViewById(R.id.telefono);
	    dir=(TextView)findViewById(R.id.direccion);
	    ema=(TextView)findViewById(R.id.email); 
        
        Bundle bundle=getIntent().getExtras();
        nombre=bundle.getString("nombre");
        
        
        DataBaseHelper dbh=new DataBaseHelper(this);
      	SQLiteDatabase db= dbh.getWritableDatabase();
      	
      	String[] campos = new String[] {"nombre","telefono","email","direccion"};
      	String[] args = new String[] {nombre};
      	 
      	Cursor c = db.query("AgendaContactos", campos, "nombre=?", args, null, null, null);
      	 
      
      	if (c.moveToFirst()) {
      		

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
        	
        	case R.id.item3:  Intent it = new Intent(this,buscar.class);
        	
        	startActivity(it);
                         	break;
        }
        return true;
    }

}
