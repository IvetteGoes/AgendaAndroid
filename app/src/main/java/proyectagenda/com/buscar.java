package proyectagenda.com;

import proyectagenda.com.R;
import proyectagenda.com.buscar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class buscar extends Activity{
	
	public EditText nombre;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscarcontacto);
        
        nombre=(EditText)findViewById(R.id.nombre);
    }
    
    public void buscar(View b)
    {
    	DataBaseHelper dbh=new DataBaseHelper(this);
      	SQLiteDatabase db= dbh.getWritableDatabase();
      	
      	String[] campos = new String[] {"nombre"};
      	String[] args = new String[] {nombre.getText().toString()};
      	 
      	Cursor c = db.query("AgendaContactos", campos, "nombre=?", args, null, null, null);
      	 
      	
      	if (c.moveToFirst()) {
      	    Intent it=new Intent(this,Detalle.class);
      	    it.putExtra("nombre", nombre.getText().toString());
      	    startActivity(it);
      	}
      	else
      	{
      	
      	Toast.makeText(getApplicationContext(), 
 	      		  "No existe registro con ese nombre, intente nuevamente.!", Toast.LENGTH_LONG).show();
      	db.close();
      	}
    }
    
  

    
    public String recuperanum()
    {
    	String numero="";
    	  DataBaseHelper dbh=new DataBaseHelper(this);
        	SQLiteDatabase db= dbh.getWritableDatabase();
        	
        	String[] campos = new String[] {"teléfono"};
        	String[] args = new String[] {nombre.getText().toString()};
        	 
        	Cursor c = db.query("AgendaContactos", campos, "nombre=?", args, null, null, null);
        	 
        
        	if (c.moveToFirst()) {
        		
        	
        	     do {
        	          
        	          String telefono = c.getString(0);
        	          numero=telefono;
        	         
        	        
        	     } while(c.moveToNext());
        	   
        	}
        	else
        	{
        	
        	Toast.makeText(getApplicationContext(), 
   	      		  "Ha ocurrido un error :(", Toast.LENGTH_LONG).show();
        	db.close();
        	}
        	
        	return numero;
    }
    
    
    
    public void llamar(View b)
    {
    	 
    	
        
        DataBaseHelper dbh=new DataBaseHelper(this);
      	SQLiteDatabase db= dbh.getWritableDatabase();
      	
      	String[] campos = new String[] {"nombre"};
      	String[] args = new String[] {nombre.getText().toString()};
      	 
      	Cursor c = db.query("AgendaContactos", campos, "nombre=?", args, null, null, null);
      	 
      	
      	if (c.moveToFirst()) {
      		AlertDialog.Builder alertDialog = new AlertDialog.Builder(buscar.this);
            alertDialog.setMessage("�Desea realizar la llamada al contacto?");
            alertDialog.setTitle("Llamar a contacto...");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() 
            {
              public void onClick(DialogInterface dialog, int which) 
              {
                try 
                {          			 
                 String numero=recuperanum();
                  String number = "tel:" + numero.trim();
                  Toast.makeText(getApplicationContext(), 
                		  "Llamando al " + numero.trim(), Toast.LENGTH_LONG).show();
                  Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number)); 
                  startActivity(callIntent);
                } 
                catch (Exception e) 
                {
                	Toast.makeText(getApplicationContext(), 
                			"No se ha podido realizar la llamada", Toast.LENGTH_LONG).show();
                }
              } 
            }); 
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
            {
              public void onClick(DialogInterface dialog, int which) 
              {
                Toast.makeText(getApplicationContext(), 
                		"Llamada cancelada", Toast.LENGTH_LONG).show();
              } 
            }); 
            alertDialog.show();
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
