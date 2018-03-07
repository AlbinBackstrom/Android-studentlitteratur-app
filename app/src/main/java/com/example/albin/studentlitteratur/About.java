package com.example.albin.studentlitteratur;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class About extends ActionBarActivity {
    Typeface myFont;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        getSupportActionBar().hide(); //gömmer actionbar
        customScrollV();     /*laddar en custom font och sätter fonten till en scrollbar textview.
                                texten som visas är lagrad i en sträng i strings.xml och laddas från activity_about.xml*/



    }


public void customScrollV(){
    myTextView = (TextView)findViewById(R.id.tx);
    myFont = Typeface.createFromAsset(getAssets(),"fonts/font.ttf");
    myTextView.setTypeface(myFont);
}



}
