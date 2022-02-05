package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText_inputText = findViewById(R.id.editText_inputText);
        CheckBox checkBox_clear = findViewById(R.id.checkBox_clear);
        Button button_addClear = findViewById(R.id.button_addClear);
        TextView textView_showText = findViewById(R.id.textView_showText);
        RadioGroup radioGroup_color = findViewById(R.id.radioGroup_color);
        RadioButton radioButton_red = findViewById(R.id.radioButton_red);
        RadioButton radioButton_blue = findViewById(R.id.radioButton_blue);
        Spinner spinner_provincesList = findViewById(R.id.spinner_provincesList);
        ImageView imageView_picture = findViewById(R.id.imageView_picture);
        TextView textView_chrono = findViewById(R.id.textView_chrono);
        SwitchCompat switch_chrono = findViewById(R.id.switch_chrono);


        button_addClear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (checkBox_clear.isChecked()) {
                    //clear
                    editText_inputText.setText((""));
                    textView_showText.setText((""));
                } else {
                    //add
                    textView_showText.append(editText_inputText.getText());
                }
            }
        });

        radioGroup_color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_red:
                        textView_showText.setTextColor(getResources().getColor(R.color.red));
                        break;
                    case R.id.radioButton_blue:
                        textView_showText.setTextColor(getResources().getColor(R.color.blue));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + checkedId);
                }
            }
        });

        spinner_provincesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(getApplicationContext(), "Seleccionaste", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }
}