package com.example.albin.studentlitteratur;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class VisaBoken extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ActionBarDrawerToggle myDrawerToggle;
    DrawerLayout myDrawerLayout;
    ListView myDrawerListView;
    ArrayList<DrawerMenuName> myDrawerMenuName = new ArrayList<>();
    DatabaseHelper dbHelper;
    TextView visaBokenTV;
    String[] myStringArray = new String[4];
    String txtHead = "Logga ut?";
    String txtBody = "Vill du verkligen logga ut?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_boken);
        visaBokenTV = (TextView)findViewById(R.id.visaBokenTV);



        /*Hämtar infon som skickas via intent från Favorites och/eller MinaBocker (beroende vart man
        * kommer ifrån) myStringArray[1] innehåller innehållet från boken och visas i en textView och
        * [2] innehåller titeln på boken som sätts som titel på Actionbaren för att tydliggöra vilken
        * bok som läses.
        * */
        Bundle extras = getIntent().getExtras();
        myStringArray = extras.getStringArray(null);
        visaBokenTV.setText(myStringArray[1]);
        getSupportActionBar().setTitle(myStringArray[2]);


        myDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        dbHelper = DatabaseHelper.getInstance(this);
        dbHelper.getWritableDatabase();




        createDrawer();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
/*
* i createDrawer() skapas själva sidomenyn/hamburgermenyn. Text och bilder placeras ut på listview.
* Som är "skapad/definerad" i en xml fil som "inflatas" via DrawerMenuAdapter
*
*
*/

  private void createDrawer(){
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.home, "Mitt konto"));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.library, "Mina böcker"));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.folderstar, "Mina favoriter"));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.star, "Lägg till favorit"));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.ic_action_discard, "Ta bort favorit"));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.ic_action_web_site, "Besök bokens websida"));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.transparent, ""));
        myDrawerMenuName.add(new DrawerMenuName(R.drawable.loggoutcopy, "Logga ut"));



        myDrawerListView = (ListView) findViewById(R.id.drawerListView);
        DrawerMenuAdapter myDrawerAdapter = new DrawerMenuAdapter(this, myDrawerMenuName);
        myDrawerListView.setAdapter(myDrawerAdapter);

        myDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    Intent iHome = new Intent(VisaBoken.this, MittKonto.class);
                    startActivity(iHome);
                }
                else if (position == 1){
                    Intent iLibrary = new Intent(VisaBoken.this, MinaBocker.class);
                    startActivity(iLibrary);
                }
                else if (position == 2){

                    Intent iShowFavs = new Intent(VisaBoken.this, Favorites.class);
                    startActivity(iShowFavs);

                }
                else if (position == 3){
                    dbHelper.addFavorites(myStringArray[0]); //Skickar med id och lägger till den aktuella boken i favoriter
                    myDrawerLayout.closeDrawers();
                    Toast.makeText(getApplicationContext(), "Tillagd till dina favoriter", Toast.LENGTH_SHORT).show();
                }
                else if (position == 4){
                    dbHelper.removeOneFavorite(myStringArray[0]);
                    myDrawerLayout.closeDrawers();

                    Toast.makeText(getApplicationContext(), "Raderad från dina favoriter", Toast.LENGTH_SHORT).show();

                }
                else if (position == 5){
                    Intent iBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(myStringArray[3]));
                    startActivity(iBrowser);

                }
                else if (position == 7){

                    CustomDiaConfirmLoggout diaConf = new CustomDiaConfirmLoggout(VisaBoken.this,txtHead,txtBody);
                    diaConf.show();


                }
            }
        });

    }



    private void setupDrawer(){

        myDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
              //  getSupportActionBar().setTitle("Stäng meny");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
               // getSupportActionBar().setTitle(null);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }




        };

        myDrawerToggle.setDrawerIndicatorEnabled(true);
        myDrawerLayout.setDrawerListener(myDrawerToggle);
    }

    @Override
    public void onBackPressed() {

        if (myDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            myDrawerLayout.closeDrawer(Gravity.LEFT);
        }else {
            super.onBackPressed();

        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        myDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visa_boken, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (myDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void homeClicked(MenuItem item) {
        Intent i = new Intent(VisaBoken.this, MittKonto.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         //Fyller ingen funktion för mig men måste finnas i koden för att det ska fungera...?
    }


    public class DrawerMenuName{
        private int drawableID;
        private String menuName;

        public DrawerMenuName(int drawableID, String menuName){
            this.menuName = menuName;
            this.drawableID = drawableID;
        }
        public String getMenuName(){
            return menuName;
        }

        public int getDrawableID(){
            return drawableID;
        }
    }
}
