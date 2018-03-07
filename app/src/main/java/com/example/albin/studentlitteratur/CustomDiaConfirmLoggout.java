package com.example.albin.studentlitteratur;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * För att skapa egna dialogrutor med utseende som inte finns som färdig mall så måste en custom class skapas.
 * Den här klassen får sin layout från custom_dialog_conf_loggout som är en xml fil i "Layout" mappen. Den har skapats
 * för att ge ett utseende som passar min app. Det tar emot två strängar som parametrar och har två knappar. I xml filen
 * så anges även färg/backgrund osv för själva utseendet. Denna klass används vid bekräftelse vid utloggning.
 */
public class CustomDiaConfirmLoggout extends Dialog implements View.OnClickListener {

    Button yes, no;
    TextView confLogoutTvHead, conLogoutTvBody;
    Typeface myFont;
    String txtHead, txtBody;

    public CustomDiaConfirmLoggout(Context context, String txtHead, String txtBody) {
        super(context);
        this.txtHead = txtHead;
        this.txtBody = txtBody;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_dialog_conf_loggout); //ger dialogrutan det utseende som skapats i xml-filen
        myFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf"); //laddar en egen font som lagts in i mappen "assets"

        confLogoutTvHead = (TextView)findViewById(R.id.confHead);
        confLogoutTvHead.setText(txtHead); //sätter texten till det som skickas in via konstruktorn
        confLogoutTvHead.setTypeface(myFont);//sätter min font till texten.

        conLogoutTvBody = (TextView) findViewById(R.id.confBody);
        conLogoutTvBody.setText(txtBody);
        conLogoutTvBody.setTypeface(myFont);

        yes = (Button) findViewById(R.id.btn_yes_loggout);
        yes.setOnClickListener(this);
        yes.setTypeface(myFont);

        no = (Button) findViewById(R.id.btn_no_loggout);
        no.setOnClickListener(this);
        no.setTypeface(myFont);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes_loggout:

                Intent i = new Intent(getContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                getContext().startActivity(i);

                break;
            case R.id.btn_no_loggout:
                dismiss();
                break;
            default:
        }
        dismiss();
    }
}
