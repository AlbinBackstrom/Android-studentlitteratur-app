package com.example.albin.studentlitteratur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;



/*
* _id = 0
* Författare = 1
* Titel = 2
* Favorit = 3
* Innehåll = 4
* Bild = 5
* Webbadress = 6
*
* Ovan är positionerna för de olika kolumnerna i databasen.
*
* För att få in en redan färdig databas i en app så behövs först och främst en  färdig databas som läggs in i mappen
* assets. Sedan används koden mellan raderna (cirka) 60 och 130 för att se till att databasen implementeras
* och används riktigt under appens gång. I normala fall så har man mer kod vid tex uppdatering eller
* liknande vid databasen och nya versioner av appar. Men det är inget som har lagts tid på i detta
* exempel.
* */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteOpenHelper";
    private static  String DB_PATH = "/data/data/com.example.albin.studentlitteratur/databases/";
    private static  String DB_NAME ="bibliotekDB";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Böcker";
    public static final String COL_ID = "_id";
    public String COL_FAV = "Favorit";
    public static String favoriter  = "ja";


    private SQLiteDatabase myDatabase;
    private  final Context myContext;
    /*You should not initialize your helper object using with new DatabaseHelper(context)!
    Instead, always use DatabaseHelper.getInstance(context), as it guarantees that only
    one database helper will exist across the entire application's lifecycle.*/

    public static DatabaseHelper sInstance;
    public static synchronized DatabaseHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new DatabaseHelper(context.getApplicationContext());

        }return sInstance;
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;

        try {
            createDatabase();
            openDataBase();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void createDatabase() throws IOException{

        System.out.println("skapar data");
        boolean dbExist = checkDatabase();
        if (dbExist){
        }
        else {System.out.println("skapar data");

            this.getReadableDatabase();
            try{
                copyDatabase();
            }catch (IOException e){
                e.printStackTrace();
                throw new Error("Fel vid kopiering av databasen");

            }
        }
    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
           e.printStackTrace();
        }
        if (checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    private void copyDatabase() throws IOException{
        Log.i(TAG, "copydatabase");
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, lenght);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLiteException{
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close(){
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
/*
* Nedan så komer att SQL-satser jag använt för att få fram eller skriva data till databasen.
* Vid skriving har klassen Cursor användts som returnerar ett objekt med informationen som eftersökts
* Har det skrivits till databasen har en vanlig metod skapats som använder ContenValues som lagrar
* angiven information som sedan skrivs till databasen med hjälp av .put . Det är db.update som skriver
* själva informationen i databasen med hjälp av angivna argument.
*
* Metoderna addFavorites() och removeOneFavorite() har en in-parameter som är en position/id från en listview.
* Parametern anger vilket id eller position som skickas in i metoden och bestämmer vilken bok/rad eller
* kolumn som påverkas och skrivs över i databasen.
*
* removeAllFavorites() har ingen parameter då den skriver över/ använder allt som är angivet som
* argument.
*
* public Cursor visaFavoriter() har "favorit" som argument i sin sql-sats. Den är angiven till "ja" för att
* visa alla böcker med värdet "ja" i COL_FAV som motsvarar kolumnen "Favorit" i databasen.
* */

    public Cursor visaAllaBocker(){
        SQLiteDatabase db = this.getReadableDatabase();
        String SQLStmnt = " select * from " + TABLE_NAME + ";";

        return db.rawQuery(SQLStmnt, null);

    }
    public Cursor visaEnBok(String bok){
        SQLiteDatabase db = this.getWritableDatabase();
        String Sql =  " select * from " + TABLE_NAME + " where " + COL_ID + " = '" + bok + "';";
        return db.rawQuery(Sql, null);
    }



public  void addFavorites (String addFavsC){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COL_FAV, "ja");
    String whereClause = COL_ID + " = ? " ;
    String[] whereArgs = new  String[] { addFavsC };
    String sql = "update * from " + TABLE_NAME + " where " + COL_FAV + " = '"  + addFavsC + "';";


 db.update(TABLE_NAME,
        values,
        whereClause,
        whereArgs);
    Log.d(TAG,String.valueOf(db.update(TABLE_NAME, values, whereClause, whereArgs)) );

    System.out.println("add favs: "+ sql);

}
    public void removeOneFavorite(String removeFavs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAV, "nej");
        String whereClause = COL_ID + " = ?";
        String[] whereArgs = new String[]{removeFavs};
        String sql = "update * from " + TABLE_NAME + " where " + whereClause  + removeFavs + ":";

        db.update(
                TABLE_NAME,
                values,
                whereClause,
                whereArgs);

        System.out.println("remove added favs: " + sql);
    }

    public Cursor visaFavoriter(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlFavs = " select * from " + TABLE_NAME + " where " + COL_FAV + " = '" + favoriter + "';";
        System.out.print(sqlFavs);
        return db.rawQuery(sqlFavs, null);
    }

    public void removeAllFavorites(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAV, "nej");
        String whereClause = COL_FAV + "= ?";
        String[] whereArgs = {"ja"};
        db.update(TABLE_NAME,
                values,
                whereClause,
                whereArgs);
        String sql  = "update * from " + TABLE_NAME + whereClause + whereArgs + ";";
        System.out.print(sql);

    }

}
