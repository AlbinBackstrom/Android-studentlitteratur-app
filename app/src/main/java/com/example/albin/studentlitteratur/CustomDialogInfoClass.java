package com.example.albin.studentlitteratur;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Samma som CustomDiaConfirmLoggout men något simplare då den enbart tar emot
 * en sträng och har en "ok" knapp. Denna klass används vid informationsvisning för
 * användaren. Den har dock en egen layout som skiljer sig lite från CustomDiaConfirmLoggout.
 */
public class CustomDialogInfoClass extends Dialog implements View.OnClickListener {

    Button ok;
    TextView myTextView;
    Typeface myFont;
    String text;


    public CustomDialogInfoClass(Context context, String text) {
        super(context);
        this.text = text;
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog_demo); //annan layout som finns i /layout/...xml

            ok = (Button)findViewById(R.id.btnOk);
            myTextView = (TextView) findViewById(R.id.textViewDialog);
            myTextView.setText(text);
            ok.setOnClickListener(this);

            myFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf");
            ok.setTypeface(myFont);
            myTextView.setTypeface(myFont);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnOk:
                    dismiss();
                    break;
                default:
            }
            dismiss();
        }

    }


