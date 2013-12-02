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
 
public class CustomListViewAdapterCab extends ArrayAdapter<RowItemCab> {
 
    Context context;
 
    public CustomListViewAdapterCab(Context context, int resourceId,
            List<RowItemCab> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView image1;
        TextView txtName;
        TextView txtArea;
        TextView txtAddress;
        TextView txtPhone1;
        
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItemCab rowItemCab = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cabtable, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.textView2);
            holder.txtArea = (TextView) convertView.findViewById(R.id.textView4);
            holder.txtAddress = (TextView) convertView.findViewById(R.id.textView6);
            holder.txtPhone1 = (TextView) convertView.findViewById(R.id.textView8);
            
            holder.image1 = (ImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.txtName.setText(rowItemCab.getName());
        holder.txtArea.setText(rowItemCab.getArea());
        holder.txtAddress.setText(rowItemCab.getAddress());
        holder.txtPhone1.setText(rowItemCab.getPhone1());
        
        holder.image1.setImageBitmap(rowItemCab.getImage());
 
        return convertView;
    }
}