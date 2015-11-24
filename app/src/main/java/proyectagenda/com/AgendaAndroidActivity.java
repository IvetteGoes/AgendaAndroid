package proyectagenda.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgendaAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenida);
    }
    
    public void agregar(View b)
    {
      Intent it=new Intent(this,Nuevo.class);
      startActivity(it);
    }
    
    public void eliminar(View b)
    {
      Intent it=new Intent(this,EliminarContacto.class);
      startActivity(it);
    }
    
    public void modificar(View b)
    {
      Intent it=new Intent(this,Modificar1.class);
      startActivity(it);
    }
    
    public void buscar(View b)
    {
    	  Intent it=new Intent(this,buscar.class);
          startActivity(it);
    }
}