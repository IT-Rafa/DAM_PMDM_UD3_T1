package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = getWindow().getDecorView();
        int orientation = getResources().getConfiguration().orientation;
        if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
            //Do SomeThing; // Landscape
        } else {
            //Do SomeThing;  // Portrait
        }


        EditText editText_inputText = findViewById(R.id.editText_inputText);
        CheckBox checkBox_clear = findViewById(R.id.checkBox_clear);
        Button button_addClear = findViewById(R.id.button_addClear);
        TextView textView_showText = findViewById(R.id.textView_showText);
        RadioGroup radioGroup_color = findViewById(R.id.radioGroup_color);
        Spinner spinner_provincesList = findViewById(R.id.spinner_provincesList);
        ImageView imageView_picture = findViewById(R.id.imageView_picture);
        Chronometer chronometer = findViewById(R.id.chronometer);
        SwitchCompat switch_chronometer = findViewById(R.id.switch_chronometer);


        button_addClear.setOnClickListener(v -> {

            if (checkBox_clear.isChecked()) {
                //clear
                editText_inputText.setText((""));
                textView_showText.setText((""));
            } else {
                //add
                textView_showText.append(editText_inputText.getText().toString());
            }
        });

        radioGroup_color.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton_red) {

                textView_showText.setTextColor(getResources().getColor(R.color.red));
            } else if (checkedId == R.id.radioButton_blue) {

                textView_showText.setTextColor(getResources().getColor(R.color.blue));
            } else {
                throw new IllegalStateException("Unexpected value: " + checkedId);
            }
        });
        OnByUserItemSelectedListener listener = new OnByUserItemSelectedListener();
        spinner_provincesList.setOnTouchListener(listener);
        spinner_provincesList.setOnItemSelectedListener(listener);


        if(imageView_picture != null){
            imageView_picture.setOnClickListener(v -> {
                Toast.makeText(getApplicationContext(), imageView_picture.getTag().toString()  , Toast.LENGTH_SHORT).show();
            });

            switch_chronometer.setOnCheckedChangeListener((compoundButton, active) -> {
                if(active){
                    compoundButton.setText(R.string.text_start);
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }else{
                    compoundButton.setText(R.string.text_stop);
                    chronometer.stop();
                }
            });
        }


        chronometer.setOnChronometerTickListener(c -> {
            if(c.getText().equals("00:15")) {
                this.finish();
            }
        });
    }
}