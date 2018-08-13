package hotel.cyut.im.o_lock;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Search extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    Calendar c = Calendar.getInstance();
    TextView tvDate, tvDate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        getSupportActionBar().hide();

        tvDate = (TextView)findViewById(R.id.o_in);
        tvDate2 = (TextView)findViewById(R.id.o_out);

        tvDate.setOnClickListener(this);
        tvDate2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvDate) {
            new DatePickerDialog(this, this,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH))
                    .show();
        }
        else if (v == tvDate2) {
            new DatePickerDialog(this, this,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH))
                    .show();
        }

    }

    @Override
    public void onDateSet(DatePicker v, int y, int m, int d) {

        tvDate.setText(y + "/" + (m+1) + "/" + d);
        tvDate2.setText(y + "/" + (m+1) + "/" + d);
    }
}
