package quiniela.tesis.com.mundial2018.modelos;

import java.io.Serializable;

/**
 * Created by Javier on 29/07/2015.
 */
public class Usuario implements Serializable {
    private String Email;
    private String Password;

    public Usuario(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    //</editor-fold>

}
