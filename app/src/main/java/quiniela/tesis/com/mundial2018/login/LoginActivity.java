package quiniela.tesis.com.mundial2018.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import quiniela.tesis.com.mundial2018.MainActivity;
import quiniela.tesis.com.mundial2018.R;
import quiniela.tesis.com.mundial2018.bd.DatabaseHandler;
import quiniela.tesis.com.mundial2018.bd.HelperSincroDB;
import quiniela.tesis.com.mundial2018.modelos.Usuario;
import quiniela.tesis.com.mundial2018.tools.SessionManager;
import quiniela.tesis.com.mundial2018.tools.Tools;


public class LoginActivity extends AppCompatActivity implements OnClickListener{
	String TAG = LoginActivity.class.getName();
    EditText txtUsuario;
	EditText txtPassword;
	TextView txtIMEI;
	Button bttLogin;

	SessionManager session;
	private FloatingActionButton fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		//setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		txtUsuario = (EditText) findViewById(R.id.log_txtUsuario);
		txtPassword= (EditText) findViewById(R.id.log_txtPassword);
		bttLogin = (Button) findViewById(R.id.log_bttLoggin);

		txtIMEI = (TextView) findViewById(R.id.log_txtImei);
		txtIMEI.setText(String.format("IMEI: %s", Tools.getIMEI(getApplicationContext())));
		// Session manager
		session = new SessionManager(getApplicationContext());

		setupFab();
		bttLogin.setOnClickListener(this);

	}

	private void setupFab(){
		fab = (FloatingActionButton) findViewById(R.id.fab);
		if(fab != null)
			fab.setOnClickListener(this);
	}

	private void checkLogin( String username, String password) {
		Usuario u = new Usuario(username, password);

		HelperSincroDB helper = new HelperSincroDB(new DatabaseHandler(getApplicationContext()));

		if(helper.login(u)){
			session.setLogin(true);
			session.setEmail(username);
			Intent intent = new Intent(LoginActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();
		}else{
			String msj = String.format("Credenciales incorrectas.");
			Tools.showMessage(msj, LoginActivity.this);
		}

	}
	@Override
	public void onClick(View v) {		
		switch (v.getId()){
			
		case R.id.log_bttLoggin:
			String username = txtUsuario.getText().toString();
			String password = txtPassword.getText().toString();

			// Check for empty data in the form
			if (username.trim().length() > 0 && password.trim().length() > 0) {
				checkLogin(username, password);
			}else {
				Tools.showMessage("Verificar que los datos estén completos y/o correctos",  LoginActivity.this);
			}
		break;
			case R.id.fab:
				//descargarUsuarios();
				break;
	
		}		
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
