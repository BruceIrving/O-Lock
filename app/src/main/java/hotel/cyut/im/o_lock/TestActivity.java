package hotel.cyut.im.o_lock;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestActivity extends AppCompatActivity {

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);


        t1 = (TextView)findViewById(R.id.textView11);

        String jsonText="";
        String output="";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://127.0.0.1/new1.php");
            HttpResponse response = client.execute(get);
            jsonText = EntityUtils.toString(response.getEntity());

            JSONArray array = new JSONArray(jsonText);
            for (int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String email = obj.getString("m_email");
                String pw = obj.getString("m_pw");
                String name = obj.getString("m_name");
                String tel = obj.getString("m_tel");
                output += email+" "+pw+" "+name+" "+tel+" \n";
            }
            t1.setText(jsonText);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
