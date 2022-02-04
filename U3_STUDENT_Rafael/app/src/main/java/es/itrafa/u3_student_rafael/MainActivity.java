package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText editText_inputText  = findViewById(R.id.editText_inputText);
        CheckBox checkBox_clear = findViewById(R.id.checkBox_clear);
        Button button_addClear = findViewById(R.id.button_addClear);
        TextView textView_showText = findViewById(R.id.textView_showText);
        RadioButton radioButton_red = findViewById(R.id.radioButton_red);
        RadioButton radioButton_blue = findViewById(R.id.radioButton_blue);
        Spinner spinner_provincesList = findViewById(R.id.spinner_provincesList);
        ImageView imageView_picture = findViewById(R.id.imageView_picture);
        TextView textView_chrono = findViewById(R.id.textView_chrono);
        SwitchCompat switch_chrono = findViewById(R.id.switch_chrono);


        button_addClear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(checkBox_clear.isChecked()){
                    //clear
                    editText_inputText.setText((""));
                }else{
                    //add
                    textView_showText.append(editText_inputText.getText());
                }
            }
        });
    }


}