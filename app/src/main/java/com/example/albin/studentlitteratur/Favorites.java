package com.example.albin.studentlitteratur;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
* Den här klassen är i stort sätt likadan och har samma funktioner och metoder som
* MinaBocker.class förutom att det enbart är tillagda favoriter som visas och
* contextmenyn är mindre. Längst ner finns dock en till custom dialog klass som har som funktion att
* visa en dialogruta där man får ett val att radera alla favoriter som är tillagda.
*
*
*
* */
public class Favorites extends ActionBarActivity {

    DatabaseHelper dbHelper;
    ListView favoritesListView;
    Cursor myCursor;
    String[] myStringArray = new String[3];
    ListViewCursorAdapter myFavsAdapter;
    String openBookCmeny = "Öppna boken";
    String rmvFavsCmeny = "Radera från dina favoriter";
    String webSiteCmeny = "Besök bokens hemsida";
    String showWebSiteCmeny;
    String textHeadRemove = "Radera all favoriter ?";
    String textBodyRemove = "För att ta bort en favortit, tryck och håll in på vald bok för att radera den.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        dbHelper = DatabaseHelper.getInstance(this);
        myCursor = dbHelper.visaFavoriter();
        dbHelper.getWritableDatabase();
        myFavsAdapter = new ListViewCursorAdapter(this, myCursor);
        favoritesListView = (ListView)findViewById(R.id.favoritesListView);
        favoritesListView.setAdapter(myFavsAdapter);
        registerForContextMenu(favoritesListView);


        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor) myFavsAdapter.getItem(position);

                myStringArray[0] = c.getString(0);
                myStringArray[1] = c.getString(4);
                myStringArray[2] = c.getString(2);

                Intent i = new Intent(Favorites.this, VisaBoken.class);
                i.putExtra(null, myStringArray);
                startActivity(i);

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =  (AdapterView.AdapterContextMenuInfo) menuInfo;

        Cursor c = ((Cursor) myFavsAdapter.getItem(info.position));
        String cTitle = c.getString(2);

        menu.setHeaderTitle(cTitle);
        menu.add(0, v.getId(), 0, openBookCmeny);
        menu.add(0, v.getId(), 0, rmvFavsCmeny);
        menu.add(0, v.getId(), 0, webSiteCmeny);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        Cursor c = ((Cursor) myFavsAdapter.getItem(info.position));
        myStringArray[0] = c.getString(0);
        myStringArray[1] = c.getString(4);
        showWebSiteCmeny = c.getString(6);

        if (item.getTitle() == openBookCmeny){
            Intent i = new Intent(Favorites.this, VisaBoken.class);
            i.putExtra(null, myStringArray);
            startActivity(i);
        }

        else if (item.getTitle() == rmvFavsCmeny){
            dbHelper.removeOneFavorite(myStringArray[0]);
            recreate();

        }
        else if (item.getTitle() == webSiteCmeny){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(showWebSiteCmeny));
            startActivity(browserIntent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_favorites, menu);
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


    public void removeFavoritesClicked(MenuItem item) {
        CustomDialogYesOrNo dialogYesOrNo = new CustomDialogYesOrNo(Favorites.this, textHeadRemove,textBodyRemove );
        dialogYesOrNo.show();



    }

    public void homeClicked(MenuItem item) {
        Intent i = new Intent(Favorites.this, MittKonto.class);
        startActivity(i);
    }

    public void booksClicked(MenuItem item) {
        Intent i = new Intent(this, MinaBocker.class);
        startActivity(i);
    }

    public class CustomDialogYesOrNo extends Dialog implements View.OnClickListener{
        Button yes, no;
        TextView headerTextView, bodyTextView;
        String textHeaderString, textBodyString;



        public CustomDialogYesOrNo(Context context, String textHeaderString, String textBodyString) {
            super(context);
            this.textHeaderString = textHeaderString;
            this.textBodyString = textBodyString;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog_remove);
            yes = (Button)findViewById(R.id.btn_yes);
            no = (Button) findViewById(R.id.btn_no);
            headerTextView = (TextView) findViewById(R.id.customDiaHeader);
            bodyTextView = (TextView)findViewById(R.id.customDiaBody);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);

            Typeface myFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf");
            yes.setTypeface(myFont);
            no.setTypeface(myFont);
            headerTextView.setTypeface(myFont);
            bodyTextView.setTypeface(myFont);
            headerTextView.setText(textHeadRemove);
            bodyTextView.setText(textBodyRemove);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_yes:
                    dbHelper.removeAllFavorites();
                    recreate();
                    Toast.makeText(getContext(), "Favoriter raderade", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_no:

                    dismiss();
                    break;
                default:
                    break;
            }

            dismiss();
        }
    }


}
