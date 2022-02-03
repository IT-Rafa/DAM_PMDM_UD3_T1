package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] provinces = new String[]{"India", "USA", "China", "Japan", "Other"};
        Spinner s = (Spinner) findViewById(R.id.spinner_provincesList);

    }


}