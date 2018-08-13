package prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bruce on 2018/6lllllll/12.
 */

public class UserSession {
    String TAG = UserSession.class.getSimpleName();
    String PREF_NAME = "login";
    String KEY_IS_LOGGED_IN = "isloggedin";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserSession(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean isLoggedin){
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedin);
        editor.apply();
    }

    public boolean isUserLoggedin(){return prefs.getBoolean(KEY_IS_LOGGED_IN, false);}
}
