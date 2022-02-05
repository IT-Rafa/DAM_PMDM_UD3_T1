package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
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

        spinner_provincesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = spinner_provincesList.getSelectedItem().toString();
                Drawable drawable;
                String msg;
                String contentDescription;
                String tag;
                msg = getResources().getString(R.string.text_toast_gal);
                switch (selected) {
                    case "A Coruña":
                        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.a_coruna, null);
                        contentDescription = getResources().getString(R.string.img_description_aCoruna);
                        tag = getResources().getString(R.string.text_image_aCoruna);

                        break;
                    case "Lugo":
                        drawable =  ResourcesCompat.getDrawable(getResources(), R.drawable.lugo, null);
                        contentDescription = getResources().getString(R.string.img_description_lugo);
                        tag = getResources().getString(R.string.text_image_lugo);

                        break;
                    case "Ourense":
                        drawable =  ResourcesCompat.getDrawable(getResources(), R.drawable.orense, null);
                        contentDescription = getResources().getString(R.string.img_description_orense);
                        tag = getResources().getString(R.string.text_image_orense);

                        break;
                    case "Pontevedra":
                        drawable =  ResourcesCompat.getDrawable(getResources(), R.drawable.pontevedra, null);
                        contentDescription = getResources().getString(R.string.img_description_pontevedra);
                        tag = getResources().getString(R.string.text_image_pontevedra);

                        break;
                    default:
                        drawable =  ResourcesCompat.getDrawable(getResources(), R.drawable.ic_no_image_icon, null);
                        contentDescription = "";
                        tag = getResources().getString(R.string.text_image_none);
                        msg = getResources().getString(R.string.text_toast_no_gal);
                }
                imageView_picture.setImageDrawable(drawable);
                imageView_picture.setContentDescription(contentDescription);
                imageView_picture.setTag(tag);

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


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

        chronometer.setOnChronometerTickListener(c -> {
            if(c.getText().equals("00:15")) {
                this.finish();
            }
        });
    }
}