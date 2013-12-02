package com.gsg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gsg.CustomListViewAdapter;
import com.gsg.R;
import com.gsg.RowItem;

public class SearchResultsActivity extends Activity implements OnClickListener{
	
	String area,category;
	TextView tvName,tvCategory,tvArea,tvAddress,tvPhoneno,tvDescription;
  	ImageView imgHeaderIcon,researcher_img,imgSearch=null;
  	String address;
  	ListView listView;
    List<RowItem> rowItems;
    
    byte[] byteArray=null;
	Bitmap bitmap;
	@Override
 public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresults);
		/*tvName =(TextView)findViewById(R.id.TextView08);
		tvCategory =(TextView)findViewById(R.id.TextView06);
		tvArea =(TextView)findViewById(R.id.TextView05);
		tvLandmark =(TextView)findViewById(R.id.TextView03);
		tvPhoneno =(TextView)findViewById(R.id.TextView02);
		tvLandlineno =(TextView)findViewById(R.id.TextView01);
		tvDescription =(TextView)findViewById(R.id.TextView04);
		researcher_img =(ImageView)findViewById(R.id.imageView1);
		GridView gv=new GridView(getApplicationContext());
		gv=(GridView)findViewById(R.id.gridViewSearch);
		*/
		/*
		ListView lv=new ListView(getApplicationContext());
		lv=(ListView)findViewById(R.id.listView1);
		*/
		imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());

		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
		imgHeaderIcon.setOnClickListener(this);
		imgSearch.setOnClickListener(this);

		
		DBHelper entry=new DBHelper(SearchResultsActivity.this);
		 entry.openForRead();
		 Cursor c=entry.getSearchResults();
		
		 	 int placeno =1;
		 	rowItems = new ArrayList<RowItem>();
		 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			
			 
		 		//String id=c.getString(c.getColumnIndex("PLACE_ID"));
				String placeName=c.getString(c.getColumnIndex("PLACE_NAME"));
				String category=c.getString(c.getColumnIndex("CATEGORY"));
				String area=c.getString(c.getColumnIndex("AREA"));
				String address=c.getString(c.getColumnIndex("ADDRESS"));
				String phoneno=c.getString(c.getColumnIndex("PHONE_NO"));
				String description=c.getString(c.getColumnIndex("DESCRIPTION"));
				
				//camera
				byteArray=null;
				bitmap=null;
				byteArray = c.getBlob(c.getColumnIndex("IMAGE1")); //This line gets the image's blob data
	            BitmapFactory.Options options = new BitmapFactory.Options();
	            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options); //Convert bytearray to bitmap
	            
	            
	            RowItem item =new RowItem(placeName, category, area, address, phoneno, description, bitmap);

	            rowItems.add(item);
	            
		 }
	           /* tvName.setText(place);
				tvCategory.setText(category);
				tvArea.setText(place);
				tvLandmark.setText(place);
				tvPhoneno.setText(place);
				tvLandlineno.setText(place);
				tvDescription.setText(description);
				
				researcher_img.setImageBitmap(bitmap);*/
		//	String data=placeno +".Name :"+place+"\nCategory :"+category+"\nArea :"+area+"\nLandmark :"+landmark+"\nPhone no :"+phoneno+"\nLandline no :"+landlineno+"\nDescription :"+description;	
			
			//placeno ++;
			
			
		
		/*
		 
		ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,R.layout.searchdata);
		 lv.setAdapter(adapter);
		
		 */
		 
		 /*String cols[]=new String[]{"PLACE_NAME","AREA","CATEGORY","LANDMARK","PHONE_NO","LANDLINE_NO","DESCRIPTION","IMAGE1"};
		 int[] names=new int[]{R.id.TextView08,R.id.TextView05,R.id.TextView06,R.id.TextView03,R.id.TextView02,R.id.TextView01,R.id.TextView04,R.id.imageView1};
		 SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(), R.layout.searchdata, c,cols,names);
		lv.setAdapter(adapter);*/
		
		 listView = (ListView) findViewById(R.id.listView1);
         CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.searchtable, rowItems);
         listView.setAdapter(adapter);
		
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
