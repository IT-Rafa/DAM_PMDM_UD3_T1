package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.drawable.Drawable;
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
                    textView_showText.append(editText_inputText.getText().toString());
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
                String selected = spinner_provincesList.getSelectedItem().toString();
                Drawable drawable;
                String msg;
                msg = getResources().getString(R.string.text_toast_gal);
                switch (selected) {
                    case "A Coruña":
                        drawable = getResources().getDrawable(R.drawable.a_coruna, getTheme());
                        break;
                    case "Lugo":
                        drawable = getResources().getDrawable(R.drawable.lugo, getTheme());
                        break;
                    case "Ourense":
                        drawable = getResources().getDrawable(R.drawable.orense, getTheme());
                        break;
                    case "Pontevedra":
                        drawable = getResources().getDrawable(R.drawable.pontevedra, getTheme());
                        break;
                    default:
                        drawable = getResources().getDrawable(R.drawable.ic_no_image_icon, getTheme());
                        msg = getResources().getString(R.string.text_toast_no_gal);
                }
                imageView_picture.setImageDrawable(drawable);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        imageView_picture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String msg = getResources().getString(R.string.text_toast_no_gal);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}