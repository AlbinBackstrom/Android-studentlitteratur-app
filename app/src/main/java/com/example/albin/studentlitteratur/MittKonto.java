package com.example.albin.studentlitteratur;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MittKonto extends ActionBarActivity {

    Typeface myFont;
    String webbadress = "https://www.studentlitteratur.se/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitt_konto);
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mitt_konto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
* stänger tidigare öppnade aktiviteter så vid utlogg går det ej att
* gå tillbaka till den tidigare öppna aktiviteten med
* tillbaka knappen på telefeon. Med hjälp av en if sats i Main.
* */
    public void btnLoggOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void myLibraryClicked(View view) {
        Intent i = new Intent(this, MinaBocker.class);
        startActivity(i);
    }

    public void btnFavoritesClicked(View view) {
        Intent i = new Intent(this, Favorites.class);
        startActivity(i);
    }


    public void uploadClicked(View view) {

        Toast toast = Toast.makeText(this, "Ej tillgänglig i demoversionen!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }

    //Knapp som vid tryck öppnar en webbsida som är angiven
    public void searchClicked(View view) {
        Intent iBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(webbadress));
        startActivity(iBrowser);
    }
}

