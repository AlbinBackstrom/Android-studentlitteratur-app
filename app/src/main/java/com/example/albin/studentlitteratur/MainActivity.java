package com.example.albin.studentlitteratur;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
* Tar emot getExtras från andra aktiviter som skickar "exit" via intent. Vid anrop stängs alla tidigare aktiviterer.
* */
        getSupportActionBar().hide();
        if (getIntent().getBooleanExtra("exit", false)){
            finish();
        }

    }

    public void btnOnclickLoggIn(View view) {
        Intent intent = new Intent(this, AndraInloggSidan.class);
        startActivity(intent);
    }

    public void btnAboutOnClick(View view) {
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

    public void btnNewUser(View view) {
        Intent i = new Intent(this, NewUser.class);
        startActivity(i);
    }


}
