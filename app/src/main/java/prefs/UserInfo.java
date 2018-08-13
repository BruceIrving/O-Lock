package prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bruce on 2018/6/12.
 */

public class UserInfo {
    String TAG = UserSession.class.getSimpleName();
    String PREF_NAME = "userinfo";
    String KEY_USERNAME = "username";
    String KEY_EMAIL = "email";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyusername(){return prefs.getString(KEY_USERNAME, "");}

    public String getKsyEmail(){return prefs.getString(KEY_EMAIL, "");}
}
