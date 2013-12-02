package com.gsg;
import java.util.ArrayList;
import java.util.List;

import com.gsg.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

    public class CabsActivity extends Activity implements OnClickListener
    {
   	 ImageView imgHeaderIcon,imgSearch=null;
   	List<RowItemCab> rowItemsCab;
   	byte[] byteArray=null;
	Bitmap bitmap;
	ListView listView;
	
	@Override
   public void onCreate(Bundle savedInstanceState)
	{
      super.onCreate(savedInstanceState);
      setContentView(R.layout.cab);
      
      	imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
		imgHeaderIcon.setOnClickListener(this);
		imgSearch.setOnClickListener(this);

      
      DBHelper entry=new DBHelper(CabsActivity.this);
		 entry.openForRead();
		  Cursor c=entry.getCabResults();
		
		 	 int placeno =1;
		 	rowItemsCab = new ArrayList<RowItemCab>();
		 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			
			 
		 		//String id=c.getString(c.getColumnIndex("PLACE_ID"));
				String cabName=c.getString(c.getColumnIndex("CAB_NAME"));
				String cabArea=c.getString(c.getColumnIndex("CAB_AREA"));
				String cabAddress=c.getString(c.getColumnIndex("CAB_ADDRESS"));
				String cabPhoneno=c.getString(c.getColumnIndex("PHONE1"));
				
				//camera
				byteArray=null;
				bitmap=null;
				byteArray = c.getBlob(c.getColumnIndex("CAB_IMAGE")); //This line gets the image's blob data
	            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length); //Convert bytearray to bitmap
				
	            
	            
	            RowItemCab item=new RowItemCab(cabName,cabArea,cabAddress,cabPhoneno,bitmap);
	         
	            rowItemsCab.add(item);
		 }   
	            listView = (ListView) findViewById(R.id.cabsListView);
	            CustomListViewAdapterCab adapter = new CustomListViewAdapterCab(this,R.layout.cabtable, rowItemsCab);
	            listView.setAdapter(adapter);
	   
      
      //creating objects & mapping ids
     /* GridView gv=new GridView(getApplicationContext());
		gv=(GridView)findViewById(R.id.gvCab);
		
		
		 imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());

		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);

		imgHeaderIcon.setOnClickListener(this);
		imgSearch.setOnClickListener(this);

		DBHelper entry=new DBHelper(CabsActivity.this);
		 entry.openForRead();
		 Cursor c=entry.getCabDetails();
		
		 String cols[]=new String[]{"_id","CAB_NAME","CAB_AREA","PHONE1","PHONE2"};
		 int[] names=new int[]{R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5};
		 
		 SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(), R.layout.cabsdata, c,cols,names);
		gv.setAdapter(adapter);
*/		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgsearch:
			Intent isearch=new Intent(getApplicationContext(),SearchActivity.class);
			startActivity(isearch);

			break;
		case R.id.headericon:
			Intent ihome=new Intent(getApplicationContext(),HomePgActivity.class);
			startActivity(ihome);
			break;
		}
	}
}