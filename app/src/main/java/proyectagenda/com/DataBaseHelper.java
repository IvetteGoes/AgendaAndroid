package proyectagenda.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="Agenda01";
	public static final String NOMBRE="nombre";
	public static final String TELEFONO="telefono";
	public static final String EMAIL="email";
	public static final String DIRECCION="direccion";
	
	public  DataBaseHelper(Context context)
	{
		super(context,DATABASE_NAME,null,1);
	}
    /** Called when the activity is first created. */
    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL("CREATE TABLE AgendaContactos(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, email TEXT,direccion TEXT);");
    }
    
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	android.util.Log.v("Constante", "Actualiza la base de datos y destruye la base de datos antigua con el mismo nombre");
    	db.execSQL("DROP table if exists AgendaContactos");
    	onCreate(db);
    }
}