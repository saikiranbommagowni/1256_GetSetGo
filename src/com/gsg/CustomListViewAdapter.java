package com.gsg;

import java.util.List;

import com.gsg.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CustomListViewAdapter extends ArrayAdapter<RowItem> {
 
    Context context;
 
    public CustomListViewAdapter(Context context, int resourceId,
            List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView image1;
        TextView txtPlace;
        TextView txtCateory;
        TextView txtArea;
        TextView txtAddress;
        TextView txtPhoneno;
        TextView txtDescription;
        
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.searchtable, null);
            holder = new ViewHolder();
            holder.txtPlace = (TextView) convertView.findViewById(R.id.textView2);
            holder.txtCateory = (TextView) convertView.findViewById(R.id.textView4);
            holder.txtArea = (TextView) convertView.findViewById(R.id.textView6);
            holder.txtAddress = (TextView) convertView.findViewById(R.id.textView8);
            holder.txtPhoneno = (TextView) convertView.findViewById(R.id.textView10);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.textView12);
            
            holder.image1 = (ImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.txtPlace.setText(rowItem.getPlaceName());
        holder.txtCateory.setText(rowItem.getCategory());
        holder.txtArea.setText(rowItem.getArea());
        holder.txtAddress.setText(rowItem.getAddress());
        holder.txtPhoneno.setText(rowItem.getPhoneno());
        holder.txtDescription.setText(rowItem.getDescription());
        
        holder.image1.setImageBitmap(rowItem.getImageId());
 
        return convertView;
    }
}