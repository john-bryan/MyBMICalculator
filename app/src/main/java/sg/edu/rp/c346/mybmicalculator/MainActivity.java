package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    Button btnCalc;
    Button btnReset;
    TextView tvDate;
    TextView tvBMI;

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String date = prefs.getString("date", "");

        String bmi = prefs.getString("bmi", "");

        tvDate.setText(date);
        tvBMI.setText(bmi);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.weight);
        etHeight = findViewById(R.id.height);
        btnCalc = findViewById(R.id.calculate);
        btnReset = findViewById(R.id.reset);
        tvDate = findViewById(R.id.date);
        tvBMI = findViewById(R.id.bmi);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);


                double weight = Double.parseDouble(etWeight.getText().toString());
                double height = Double.parseDouble(etHeight.getText().toString());

                Double bmi = weight / (height * height);

                String nbmi = String.format("%.2f", bmi);

                tvBMI.setText("Last Calculated BMI: " + nbmi);
                tvDate.setText("Last Calculated Date: "+ datetime);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("bmi",nbmi);
                prefEdit.putString("date",datetime);


                prefEdit.commit();
            }
        });

    }

}
