package quiniela.tesis.com.mundial2018.bd;
/**
 * Clase administadora de la base de datos
 *  
 * */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import quiniela.tesis.com.mundial2018.modelos.Usuario;

public class DatabaseHandler extends SQLiteOpenHelper {

 	private static final int DATABASE_VERSION = 1;	//DATABASE VERSION
	    
    public static final String DATABASE_NAME = "quiniela_db";	//DATABASE NAME

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//See crea la base de datos	
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		//TABLA ACTIVIDADES
		String DDLQuery = "CREATE TABLE \"usuarios\" (\"id_usuario_ref\" INTEGER PRIMARY KEY AUTOINCREMENT, \"id_pais\" TEXT, \"id_empresa\" TEXT, \"id_centro\" TEXT, \"id_ruta\" TEXT, \"id_repartidor\" TEXT, \"id_user\" TEXT, \"clave\" TEXT, \"nombre\" TEXT, \"login\" INTEGER DEFAULT 0)";
		db.execSQL(DDLQuery);

		//TABLA CHECKIN
		//DDLQuery = "CREATE TABLE \"mpt_checkin\" (\"id_checkin\" INTEGER PRIMARY KEY AUTOINCREMENT, \"id_gestion\" TEXT NOT NULL, \"id_pais\" TEXT NOT NULL, \"id_empresa\" TEXT NOT NULL, \"id_centro\" TEXT NOT NULL, \"id_ruta\" TEXT NOT NULL, \"id_repartidor\" TEXT NOT NULL, \"id_codigo_emp\" TEXT NOT NULL, \"id_codigo_actividad\" TEXT NOT NULL, \"reg_timestamp\" TEXT NOT NULL, \"reg_fecha\" TEXT NOT NULL, \"sincronizado\" TEXT NOT NULL DEFAULT 0)";
		//db.execSQL(DDLQuery);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS usuarios");

		//Creando las tablas de nuevo
		onCreate(db);
	}


/*
	//<editor-fold desc="MANEJO DE LA TABLA USUARIOS">

	public long addUsuario(Usuario registro) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("id_pais", registro.getIdPais());
		values.put("id_empresa", registro.getIdEmpresa());
		values.put("id_centro", registro.getIdCentro());
		values.put("id_ruta", registro.getIdRuta());
		values.put("id_repartidor", registro.getIdRepartidor());
		values.put("id_user", registro.getIdUser());
		values.put("clave", registro.getClave());
		values.put("nombre", registro.getNombre());


		long result= db.insert("usuarios", null, values);
		db.close(); // Cerrando conexión
		return result;
	}

	public Usuario getCurrentUsuario() {
		Usuario registro = new Usuario();
		String selectQuery = "SELECT * FROM usuarios WHERE login = 1"; // obtener el usuario logeado

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				registro.setIdUsuarioReferencia(cursor.getString(0));
				registro.setIdPais(cursor.getString(1));
				registro.setIdEmpresa(cursor.getString(2));
				registro.setIdCentro(cursor.getString(3));
				registro.setIdRuta(cursor.getString(4));
				registro.setIdRepartidor(cursor.getString(5));
				registro.setIdUser(cursor.getString(6));
				registro.setClave(cursor.getString(7));
				registro.setNombre(cursor.getString(8));

			} while (cursor.moveToNext());
		}
		db.close();
		return registro;

	}

	//Elimina todos los usuarios
	public long deleteAllUsuarios(){
		SQLiteDatabase db = this.getWritableDatabase();
		long i = db.delete("usuarios", null, null);
		db.close();
		return i;
	}

	public long setLoginUsuario(String idUsuarioReferencia){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("login", 1);
		long i = db.update("usuarios", valores, "id_usuario_ref = " + idUsuarioReferencia, null);
		db.close();
		return i;
	}

	public long setLogoffUsuario(String idUsuarioReferencia){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("login", 0);
		long i = db.update("usuarios", valores, "id_usuario_ref = " + idUsuarioReferencia, null);
		db.close();
		return i;
	}

	public Usuario getUsuario(Usuario u) {

		String selectQuery = String.format(
				"SELECT * FROM usuarios " +
				"where id_repartidor = \"%s\" AND " +
				"id_user = \"%s\" AND " +
				"clave = \"%s\" ",
				u.getIdRepartidor(), u.getIdUser(),u.getClave());
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		Usuario toReturn = null;
		if (cursor.moveToFirst()) {
			do {
				Usuario registro = new Usuario();
				registro.setIdUsuarioReferencia(cursor.getString(0));
				registro.setIdPais(cursor.getString(1));
				registro.setIdEmpresa(cursor.getString(2));
				registro.setIdCentro(cursor.getString(3));
				registro.setIdRuta(cursor.getString(4));
				registro.setIdRepartidor(cursor.getString(5));
				registro.setIdUser(cursor.getString(6));
				registro.setClave(cursor.getString(7));
				registro.setNombre(cursor.getString(8));
				toReturn = registro;
			} while (cursor.moveToNext());
		}
		db.close();
		return toReturn;

	}


	//</editor-fold>
*/
}
	