package com.example.albin.studentlitteratur;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AndraInloggSidan extends ActionBarActivity {

    EditText userNameInputLoggin, userPassInputLoggin;
    String inputName, inputPass, name, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andra_inlogg_sidan);
        getSupportActionBar().hide();

        userNameInputLoggin = (EditText)findViewById(R.id.userNameInputLoggin);
        userPassInputLoggin = (EditText)findViewById(R.id.userPassInputLoggin);



    }
/*
*
*
*
* */

    public void btnOnclickLoggIn(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        //Hämtar inskriven info från loggin sidan där användaren skriver in sitt användarnamn och lösenord för att logga in
        inputName = userNameInputLoggin.getText().toString();
        inputPass = userPassInputLoggin.getText().toString();
        //Hämtar tidigare sparad info som är lagrad i sharedPreferensens
        name = sharedPreferences.getString("username", "");
        pw = sharedPreferences.getString("password", "");


        //If satsen kollar att det tidigare lagrade namnet från registrerirgen stämmer överens med det som anges nu vid imloggningein
        if (inputName.equals(name) && inputPass.equals(pw)){
            Intent intent = new Intent(this, MittKonto.class);
           // intent.putExtra("username", name);
            //intent.putExtra("password", pw);

            startActivity(intent);
        }

        else {
           // Context context = getApplicationContext();
            String text = "Felaktigt användarnamn eller lösenord";
            Toast toast = Toast.makeText(this, text,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
            toast.show();
        }





    }



}
