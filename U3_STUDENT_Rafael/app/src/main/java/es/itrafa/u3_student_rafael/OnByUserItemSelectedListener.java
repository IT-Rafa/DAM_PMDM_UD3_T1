package es.itrafa.u3_student_rafael;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public class OnByUserItemSelectedListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener  {
    boolean  userSelect = false;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (userSelect) {
            System.out.println("spinner changed");
            userSelect = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        userSelect = true;
        return false;
    }
}
