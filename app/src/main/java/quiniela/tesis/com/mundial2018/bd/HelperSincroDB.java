package quiniela.tesis.com.mundial2018.bd;

import android.util.Log;

import java.util.ArrayList;

import quiniela.tesis.com.mundial2018.modelos.Usuario;

/**
 * Created by Javier on 28/07/2015.
 */
public class HelperSincroDB {

    private static final String TAG =HelperSincroDB.class.getName() ;
    DatabaseHandler databaseHandler;

    public HelperSincroDB(DatabaseHandler databaseHandler){
        this.databaseHandler=databaseHandler;
    }

    public boolean login(Usuario u) {
        if(u.getEmail().equals("admin")&& u.getPassword().equals("admin")){
            return true;
        }
        return  false;

    }

    /*
    public boolean login(Usuario u){
        Usuario tmp =databaseHandler.getUsuario(u); //obteniendo usuario
        if(tmp!=null){ // usuario existe
            //setear al usuario la columna login con 1 para determinar que está logeado
            long i = databaseHandler.setLoginUsuario(tmp.getIdUsuarioReferencia());
                if(i!=-1){ //setteo correcto
                    return true;
                }else{ //setteo incorrecto
                    return false;
                }
        }else{
            return false;
        }
    }*/

}
