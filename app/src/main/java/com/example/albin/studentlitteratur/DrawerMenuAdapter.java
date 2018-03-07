package com.example.albin.studentlitteratur;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * En custom drawer/navigation bar/slide meny som dras ut från vänster eller via "hamburgermenyn"
 */
public class DrawerMenuAdapter extends BaseAdapter {

    Context context;
    List<VisaBoken.DrawerMenuName> menuNames;
    LayoutInflater inflater;

    public DrawerMenuAdapter(Context context, List<VisaBoken.DrawerMenuName> menuNames){
        this.menuNames = menuNames;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }




    @Override
    public int getCount() {
        return menuNames.size();
    }

    @Override
    public VisaBoken.DrawerMenuName getItem(int position) {
        return menuNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menuNames.get(position).getDrawableID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.custom_layout_drawer_list, parent, false);
            holder.txtMenuName = (TextView)convertView.findViewById(R.id.lbl_menu_text);
            holder.imgMenuIcon = (ImageView)convertView.findViewById(R.id.img_icon);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        VisaBoken.DrawerMenuName mDrawerName = menuNames.get(position);
        holder.txtMenuName.setText(mDrawerName.getMenuName());
        holder.txtMenuName.setTextColor(Color.WHITE);
        Typeface mFont = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
        holder.txtMenuName.setTypeface(mFont);

        holder.imgMenuIcon.setImageResource(mDrawerName.getDrawableID());


        return convertView;
    }
    public class ViewHolder{
        TextView txtMenuName;
        ImageView imgMenuIcon;
    }
}
