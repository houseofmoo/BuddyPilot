package com.things.moos.buddypilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // variables for EditText boxes
    private EditText editText_Heading;
    private EditText editText_Reciprocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // store the elements into variables
        editText_Heading = (EditText) findViewById(R.id.editText_currentHeading);
        editText_Reciprocal = (EditText) findViewById(R.id.editText_reciprocalRadial);

        // make reciprocal unchangeable by user
        editText_Reciprocal.setEnabled(false);

        // make sure text is centered
        editText_Heading.setGravity(Gravity.CENTER_HORIZONTAL);
        editText_Reciprocal.setGravity(Gravity.CENTER_HORIZONTAL);

        // on heading text change, we update reciprocal textbox
        editText_Heading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore me
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore me
            }

            @Override
            public void afterTextChanged(Editable s) {
                // get text user has input
                String headingText = editText_Heading.getText().toString();

                try {
                    // parse int value
                    int value = Integer.parseInt(headingText);

                    // get reciprocal
                    value = GetReciprocal(value);

                    // get reciprocal and set at reciprocal elements text
                    editText_Reciprocal.setText(AddLeadingZeros(value));
                } catch (Exception e) {
                    // if we had a bad value, blank out reciprocal elements text
                    editText_Reciprocal.setText("");
                }
            }
        });
    }

    // gets the reciprocal heading
    public int GetReciprocal(int heading) {
        // remove excess degrees
        heading = heading % 360;

        // get reciprocal heading
        heading = heading + 180 > 360 ? heading - 180 : heading + 180;

        // if heading is 360, replace with 0
        heading = heading == 360 ? 0 : heading;

        return heading;
    }

    // adds leading zeros to a heading
    public String AddLeadingZeros(int heading) {
        // add leading zeros cause pilots are weird
        String headingText;
        if (heading < 10) {
            headingText = "0" + "0" +  Integer.toString(heading);
        } else if (heading < 100) {
            headingText = "0" + Integer.toString(heading);
        } else {
            headingText = Integer.toString(heading);
        }

        return headingText;
    }
}
