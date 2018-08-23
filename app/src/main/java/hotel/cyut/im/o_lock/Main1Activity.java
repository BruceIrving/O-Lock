package hotel.cyut.im.o_lock;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import prefs.UserInfo;
import prefs.UserSession;

public class Main1Activity extends AppCompatActivity implements View.OnClickListener {

    String TAG = Main1Activity.class.getSimpleName();
    EditText username, email, password, password1, tel;
    ImageButton  back;
    Button signup;
    ProgressDialog progressDialog;
    UserSession session;
    UserInfo userInfo;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        getSupportActionBar().hide();

        username = findViewById(R.id.name);
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        password1 = findViewById(R.id.password3);
        signup = findViewById(R.id.signup);
        back = findViewById(R.id.back);
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);

        back.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup:
                String uName = username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                signup(uName, mail, pass);

                break;
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

        private void signup(final String username, final String email, final String password) {
            // Tag used to cancel the request
            String tag_string_req = "req_signup";
            progressDialog.setMessage("Signing up...");
            progressDialog.show();

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    Utils.REGISTER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Register Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");

                        // Check for error node in json
                        if (!error) {
                            JSONObject user = jObj.getJSONObject("user");
                            String uName = user.getString("username");
                            String email = user.getString("email");

                            // Inserting row in users table
                            userInfo.setEmail(email);
                            userInfo.setUsername(uName);
                            session.setLoggedin(true);

                            startActivity(new Intent(Main1Activity.this, MainActivity.class));
                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObj.getString("error_msg");
                            toast(errorMsg);
                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                        toast("Json error:" + e.getMessage());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Login Error:" + error.getMessage());
                    toast("Unknown Error occurred");
                    progressDialog.hide();
                }
            }) {

                protected Map<String, String> getParams() {
                    // Posting parameters to login ur1
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("email", email);
                    params.put("password", password);

                    return params;

                }

            };

            // Adding request to request queue
            AndroidLoginController.getmInstance().addToRequestQueue(strReq, tag_string_req);
        }

    private void toast(String x) {
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }

}