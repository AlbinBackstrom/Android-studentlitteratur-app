package com.example.albin.studentlitteratur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MinaBocker extends ActionBarActivity {

    DatabaseHelper dbHelper;
    Cursor mCursor;
    ListView mListview;
    String[] myStringArray = new String[4];
    ListViewCursorAdapter mLvAdapter;
    String openBookCmeny = "Öppna boken";
    String addFavsCmeny = "Lägg till i dina favoriter";
    String rmvFavsCmeny = "Ta bort från dina favoriter";
    String webSiteCmeny = "Besök bokens hemsida";
    String openFavsCmeny = "Visa dina favoriter";
    String showWebSiteCmeny;
    String addBooks = "Det är ej möjligt att lägga till böcker i Ditt bibliotek demoversionen.";
    String rmvBooks = "Det är ej möjligt att radera böcker ur Ditt bibliotek i demoversioen.";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina_bocker);

        dbHelper = DatabaseHelper.getInstance(this);
        mCursor = dbHelper.visaAllaBocker();

        /*
        * mLvAdapter skapas från ListViewCursorAdapter som tar emot ett objekt av en Cursor som innehåller
        * alla böcker från dbHelper.visaAllaBocker.  Det är det här som fyller den
        * custom listviewn med bild på omslag, titel och författare.
        * */
        mLvAdapter = new ListViewCursorAdapter(this, mCursor);
        mListview = (ListView) findViewById(R.id.listViewMinaBocker);

        mListview.setAdapter(mLvAdapter); //mlistview använder adaptern från ListViewCursorAdapter för att skapa/fylla en custom listview

        registerForContextMenu(mListview); /*Gör det möjligt för mListview att få en context meny,
                                             när man håller inne på listviewn, istället för att trycka snabbt
                                            får man upp en popup/context meny.*/


        /*
        * Cursor c = dbHelper anropar metoden visaEnBok() som innehåller
        * sql-satsen för att få fram  alla böcker Integer.toString(position + 1) skickar med posistionen
        * som en parameter for att visa vilken bok som ska visas i nästa aktivitet. Listview börjar på index 0
        * och databasen på 1. Därav + 1.
        *
        * c.getString(x) hämtar motsvaraden index från databasen och lagrar detta i en StringArray som sedan
        * skickas med till VisaBoken som visar den valda bokens innehåll
        * */

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = dbHelper.visaEnBok(Integer.toString(position + 1));
                while (c.moveToNext()) {

                    myStringArray[0] = c.getString(0); //id på bok
                    myStringArray[1] = c.getString(4); //innehållet på boken
                    myStringArray[2] = c.getString(2); //titel på boken
                    myStringArray[3] = c.getString(6); //webbadress till boken


                    Intent i = new Intent(MinaBocker.this, VisaBoken.class);
                    i.putExtra(null, myStringArray);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mina_boker, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    * Nedan är koden för contextmenyn. getItem(info.position)) hämtar positionen på den tryckta boken
    * och c.getString(2) får fram titeln. På så sätt visas den aktuella bokens titel även i contextmenyn
    * som sätts med setHeaderTitle. Resten av koden skapar menyn och namnet på listview som kan tryckas
    * på
    * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        Cursor c = ((Cursor) mLvAdapter.getItem(info.position));
        String cursorTitle = c.getString(2);

        menu.setHeaderTitle(cursorTitle);
        menu.add(0, v.getId(), 0, openBookCmeny);
        menu.add(0, v.getId(), 0, openFavsCmeny);
        menu.add(0, v.getId(), 0, addFavsCmeny);
        menu.add(0, v.getId(), 0, rmvFavsCmeny);
        menu.add(0, v.getId(), 0, webSiteCmeny);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        //Cursor hämtar information som är aktuell vid contextmenyns tryckningar. Samma index och
        //info som ovan
        Cursor c = ((Cursor) mLvAdapter.getItem(info.position));
        myStringArray[0] = c.getString(0);
        myStringArray[1] = c.getString(4);
        showWebSiteCmeny = c.getString(6);


        if (item.getTitle() == openBookCmeny){ //öppnar den valda boken
            Intent i = new Intent(MinaBocker.this, VisaBoken.class);
            i.putExtra(null,myStringArray );
            startActivity(i);
        }
        else if (item.getTitle() == openFavsCmeny){ //öppnar favorit menyn
            Intent i = new Intent(MinaBocker.this, Favorites.class);
            startActivity(i);
        }
        else if  (item.getTitle() == addFavsCmeny) {
            dbHelper.addFavorites(myStringArray[0]); //skickar med bokens id som används i databasen för att lägga till som favorit
            Toast.makeText(this, "Tillagd i dina favoriter", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle() == rmvFavsCmeny) {
            dbHelper.removeOneFavorite(myStringArray[0]); //samma som ovan fast raderar ur favoritmenyn
            Toast.makeText(this, "Raderad från dina favoriter", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle() == webSiteCmeny){ //Hämtar bokens webbadress och öppnar en extern webbläsare
            Toast.makeText(this, "Öppnar hemsidan", Toast.LENGTH_SHORT).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(showWebSiteCmeny));
            System.out.println(showWebSiteCmeny);
            startActivity(browserIntent);
        }
        return super.onContextItemSelected(item);
    }

    /*
    *
    * Nedan är actionbarknapparna och dess metoder. De två sista har inga funktioner i appen utan
    * använder sig av en custom dialogruta (CustomDialogInfoClass) för att visa att den funktionen inte finns i demoversionen.
    * "Klassen" skickar med en sträng som parameter sedan visas i dialogrutan när den visas.
    *
    *
    * */

    public void favsClicked(MenuItem item) {
        Intent i = new Intent(this, Favorites.class);
        startActivity(i);
    }

    public void homeClicked(MenuItem item) {
        Intent i = new Intent(this, MittKonto.class);
        startActivity(i);
    }

    public void addNewBookClicked(MenuItem item) {
        CustomDialogInfoClass diaInfo = new CustomDialogInfoClass(this, addBooks);
        diaInfo.show();

    }

    public void removeBookClicked(MenuItem item) {
        CustomDialogInfoClass diaInfo = new CustomDialogInfoClass(this, rmvBooks);

        diaInfo.show();
    }






}
