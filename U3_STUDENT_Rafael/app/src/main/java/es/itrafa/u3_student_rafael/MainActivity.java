package es.itrafa.u3_student_rafael;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
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
    private EditText editText_inputText;
    private CheckBox checkBox_clear;
    private Button button_addClear;
    private TextView textView_showText;
    private RadioGroup radioGroup_color;
    private Spinner spinner_provincesList;
    private ImageView imageView_picture;
    private Chronometer chronometer;
    private SwitchCompat switch_chronometer;
    static private int actualSpinnerSelectionPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_inputText = findViewById(R.id.editText_inputText);
        checkBox_clear = findViewById(R.id.checkBox_clear);
        button_addClear = findViewById(R.id.button_addClear);
        textView_showText = findViewById(R.id.textView_showText);
        radioGroup_color = findViewById(R.id.radioGroup_color);
        spinner_provincesList = findViewById(R.id.spinner_provincesList);
        imageView_picture = findViewById(R.id.imageView_picture);
        chronometer = findViewById(R.id.chronometer);
        switch_chronometer = findViewById(R.id.switch_chronometer);

        //  orientation Control
        // (only show picture in portrait but can change selected picture with spinner)
        int orientation = getResources().getConfiguration().orientation;

        if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
            System.out.println("Cambio orientacion: landscape");

        } else {
            System.out.println("Cambio orientacion: portrait");
            // update picture data upon selection in spinner
            // needed for when spinner changed while orientation was landscape
            // (picture view no exist so it can´t be changed)
            changePictureData();
        }

        // button  listener/handler;
        // if checkbox checked, clear editText and textView
        // else add text in editText to text in textView
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

        // radioGroup  listener/handler;
        // Always only one selected
        // if red, textView in red, if blue textView in blue
        radioGroup_color.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton_red) {

                textView_showText.setTextColor(getResources().getColor(R.color.red));
            } else if (checkedId == R.id.radioButton_blue) {

                textView_showText.setTextColor(getResources().getColor(R.color.blue));
            } else {
                throw new IllegalStateException("Unexpected value: " + checkedId);
            }
        });

        // Assignation of spinner listener
        // new version cause it old was triggered when activity begin or change orientation property
        spinner_provincesList.setSelection(actualSpinnerSelectionPos);
        OnByUserItemSelectedListener listener = new OnByUserItemSelectedListener();
        spinner_provincesList.setOnTouchListener(listener);
        spinner_provincesList.setOnItemSelectedListener(listener);

        // image  listener/handler; ONLY IN PORTRAIT
        // show Toast with tag of picture when click on picture
        if (imageView_picture != null) {
            imageView_picture.setOnClickListener(v ->
                    Toast.makeText(getApplicationContext(),
                            imageView_picture.getTag().toString(),
                            Toast.LENGTH_SHORT).show());
        }

        // switch for chronometer listener/handler;
        // when left, text is "stopped" and chronometer is 00:00;
        // when right, text is "started" and chronometer move on
        // note: when activity restart (example change orientation) chronometer restart to 00:00
        switch_chronometer.setOnCheckedChangeListener((compoundButton, active) -> {
            if (active) {
                compoundButton.setText(R.string.text_start);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            } else {
                compoundButton.setText(R.string.text_stop);
                chronometer.stop();
            }
        });

        // chronometer listener;
        // when 00:15 destroy activity
        chronometer.setOnChronometerTickListener(c -> {
            if (c.getText().equals("00:15")) {
                this.finish();
            }
        });
    }

    /**
     * Listener for spinner
     * check if spinner was selected by user or by begin/change of activity
     */
    private class OnByUserItemSelectedListener
            implements AdapterView.OnItemSelectedListener, View.OnTouchListener {
        boolean userSelect = false;

        /**
         * On change show Toast message about if is a galician province or not and
         * save position in a static field
         *
         * @param parent
         * @param view
         * @param position
         * @param id
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (userSelect) {

                // save data on static attribute needed to update picture
                actualSpinnerSelectionPos = position;

                String msg;
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        msg = getResources().getString(R.string.text_toast_gal);
                        break;
                    default:
                        msg = getResources().getString(R.string.text_toast_no_gal);
                }
                // update picture ONLY IN PORTRAIT
                if (imageView_picture != null) {
                    changePictureData();
                }

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();


                userSelect = false;
            }
        }

        // is required
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        // is required
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userSelect = true;
            // is required
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //some code....
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    break;
                default:
                    break;

            }
            return false;
        }
    }

    /**
     * Change picture data upon spinner selection
     */
    private void changePictureData() {
        Drawable drawable;
        String contentDescription;
        String tag;

        switch (actualSpinnerSelectionPos) {
            case 0:
                drawable = ResourcesCompat.getDrawable(
                        getResources(), R.drawable.a_coruna, null
                );
                contentDescription = getResources().getString(
                        R.string.img_description_aCoruna);
                tag = getResources().getString(R.string.text_image_aCoruna);

                break;
            case 1:
                drawable = ResourcesCompat.getDrawable(getResources(),
                        R.drawable.lugo, null);
                contentDescription = getResources().getString(
                        R.string.img_description_lugo
                );
                tag = getResources().getString(R.string.text_image_lugo);

                break;
            case 2:
                drawable = ResourcesCompat.getDrawable(getResources(),
                        R.drawable.orense, null);
                contentDescription = getResources().getString(
                        R.string.img_description_orense
                );
                tag = getResources().getString(R.string.text_image_orense);

                break;
            case 3:
                drawable = ResourcesCompat.getDrawable(getResources(),
                        R.drawable.pontevedra, null);
                contentDescription = getResources().getString(
                        R.string.img_description_pontevedra
                );
                tag = getResources().getString(R.string.text_image_pontevedra);

                break;
            default:
                drawable = ResourcesCompat.getDrawable(getResources(),
                        R.drawable.ic_no_image_icon, null);
                contentDescription = "";
                tag = getResources().getString(R.string.text_image_none);
                break;
        }

        imageView_picture.setImageDrawable(drawable);
        imageView_picture.setContentDescription(contentDescription);
        imageView_picture.setTag(tag);

    }
}

