package com.example.albin.studentlitteratur;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * En adapter skapad för att göra en egen custom listview, den som finns som standard i
 * android biblioteket kan enbart visa en rad med text. Den här visar två rader och en bild
 * Layouten skapas i en xml fil som sedan klassen LayoutInflater hämtar och "fyller upp" och skapar en listview
 * i en annan klass. Den här adaptern används i klassen MinaBocker för att skapa listview som visar en bild
 * på bokens omslag, titel och författare.
 *
 *
 *
 * */
public class ListViewCursorAdapter extends CursorAdapter {
    public ListViewCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_layout_library_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView outputTitle = (TextView) view.findViewById(R.id.outputTitle);
        TextView outputAuthor = (TextView) view.findViewById(R.id.outputAuthor);
        ImageView imgCover = (ImageView) view.findViewById(R.id.imgCover);
/*
*
* cursorn nedan hämtar titeln från databasen och lagrar den i strängen title som sedan visas i listview.
* Samma sak gäller för author och img. Adaptern i "sammarbete" med mListview i MinaBocker fyller upp
* hela listviewn med all info i de angivna kolumnerna från databasen.
*
*
* */
        String title = cursor.getString(cursor.getColumnIndex("Titel"));
        String author = cursor.getString(cursor.getColumnIndex("Författare"));
        byte[] img = cursor.getBlob(cursor.getColumnIndex("Bild"));

        Typeface myFont = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");

        outputTitle.setTextColor(Color.WHITE);
        outputTitle.setText(title);
        outputTitle.setTypeface(myFont);

        outputAuthor.setTextColor(Color.WHITE);
        outputAuthor.setText(author);
        outputAuthor.setTypeface(myFont);

        Bitmap b1 = BitmapFactory.decodeByteArray(img, 0, img.length);
        imgCover.setImageBitmap(b1);




    }
}
