package quiniela.tesis.com.mundial2018.login;

/**
 * Splash activity
 * */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import quiniela.tesis.com.mundial2018.MainActivity;
import quiniela.tesis.com.mundial2018.R;


public class SplashActivity extends Activity {
	private static int SPLASH_TIME_OUT = 1200;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		 new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                Intent i = new Intent(SplashActivity.this, MainActivity.class);
	                startActivity(i);//d
	                finish();
	            }
	        }, SPLASH_TIME_OUT);
	}
}
