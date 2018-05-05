package quiniela.tesis.com.mundial2018.tools;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;


public class SessionManager {

	// LogCat tag
	private static String TAG = SessionManager.class.getSimpleName();

	// Shared Preferences
	SharedPreferences pref;

	Editor editor;
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared preferences file name
	private static final String PREF_NAME = SessionManager.class.getName();

	private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
	private static final String KEY_EMAIL= "Email";

	public SessionManager(Context context) {
		this._context = context;
		pref = PreferenceManager.getDefaultSharedPreferences(context);
		editor = pref.edit();
	}

	public void setLogin(boolean isLoggedIn) {
		editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
		editor.commit();
		Log.d(TAG, "User login session modified!");
	}
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_IS_LOGGEDIN, false);
	}


	public void setEmail(String email) {
		editor.putString(KEY_EMAIL, email);
		editor.commit();
		Log.d(TAG, "User mail session modified!");
	}
	public String getEmail(){
		return pref.getString(KEY_EMAIL, "");
	}

}
