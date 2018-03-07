package com.example.albin.studentlitteratur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class NewUser extends ActionBarActivity  {

    EditText usernameInput;
    EditText passwordInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        usernameInput =(EditText)findViewById(R.id.newUserNameInput);
        passwordInput = (EditText)findViewById(R.id.newPasswordInput);
        getSupportActionBar().hide();
    }



    /*När användaren trycker på en knapp kopplat till metoden btnSaveNewUser anropas den.
    * Metoden nedan sparar användarnamn och lösenord som har skrivits in i EditTexten ovan.
    * Objectet sharedPreferences skapas och en fil som heter userInfo skapas och lagrar inhämtad
    * information. Ett editor inferace skapas som sedan lagrar infon i key/value-pair. Keyn är
    * i fallet nedan "username" och "password". I dessa nycklar sparas sedan informationen från
    * usernameInput och passwordInput som skrivits in av användaren.
    **/
    public void btnSaveNewUser(View view) {

        SharedPreferences shPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shPrefs.edit();

        editor.putString("username", usernameInput.getText().toString());
        editor.putString("password", passwordInput.getText().toString());
        editor.apply();
        editor.clear();
        Toast.makeText(this, "Användare sparad",Toast.LENGTH_SHORT).show();
    }


/*
* Metoden nedan anropas när knappen logga in trycks på. värdet från "userInfo" sparas
* och skickas med till nästa aktivitet för att kunna jämföras när personen loggar in.
**/

    public void btnLogginClicked(View view) {
        SharedPreferences shPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = shPrefs.getString("username", "");
        String passwordUser = shPrefs.getString("password", "");
        Intent i = new Intent(this, AndraInloggSidan.class);
        i.putExtra("user", userName);
        i.putExtra("pw", passwordUser);
        startActivity(i);
    }
}
