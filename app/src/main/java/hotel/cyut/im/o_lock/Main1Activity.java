package hotel.cyut.im.o_lock;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class Main1Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Calendar c = Calendar.getInstance();
    TextView tvDate;
    RadioGroup rgSex;
    String sex="男";
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        getSupportActionBar().hide();

        tvDate = (TextView)findViewById(R.id.textView9);
        tvDate.setOnClickListener(this);

        rgSex = (RadioGroup)findViewById(R.id.sex);
        rgSex.setOnCheckedChangeListener(this);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        RadioButton rb = (RadioButton)findViewById(checkedId);
        sex = rb.getText().toString();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView9:
                if (v == tvDate) {
                    new DatePickerDialog(this, this,
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH))
                            .show();
                    }
                break;
            case R.id.back:
                Intent intent = new Intent(Main1Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker v, int y, int m, int d) {

        tvDate.setText(y + "/" + (m+1) + "/" + d);

    }
}
