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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.gsg.CustomListViewAdapter;
import com.gsg.R;
import com.gsg.RowItem;

import com.data.ModelLocator;

public class UpdateOrDeleteActivity extends Activity  implements OnClickListener, OnItemLongClickListener{
	
	Button btnEdit,btnDelete;
	EditText edtSelectNameUD;
	GridView gv =null;
	String placename,address=null;
	ImageView imgHeaderIcon,imgLogout,imgSearch=null;
	DBHelper entry=null; 
	ListView listView;
	List<RowItemUpdate> rowItems;
	    
	    byte[] byteArray=null;
		Bitmap bitmap;
	@Override
 public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateordeletepg);
		
		btnEdit=(Button)findViewById(R.id.btnEditUD);
	    btnDelete=(Button)findViewById(R.id.btnDeleteUD);
		edtSelectNameUD=(EditText)findViewById(R.id.edtSelectNameUD);
	   
		listView = (ListView) findViewById(R.id.listView1);
		
		imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());
			imgLogout=new ImageView(getApplicationContext());

		 
		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
			imgLogout=(ImageView)findViewById(R.id.imglogout);
			  
			imgHeaderIcon.setOnClickListener(this);
				imgSearch.setOnClickListener(this);
			   imgLogout.setOnClickListener(this);


		btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
     
        
        
         entry=new DBHelper(UpdateOrDeleteActivity.this);
		 entry.openForRead();
		 Cursor c=entry.getViewResults();
		
		 	 int placeno =1;
		 	rowItems = new ArrayList<RowItemUpdate>();
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
				bitmap=BitmapFactory.decodeByteArray(byteArray, 0,byteArray.length);
	           /* BitmapFactory.Options options = new BitmapFactory.Options();
	            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options); //Convert bytearray to bitmap
				*/
	            
	            
				RowItemUpdate item =new RowItemUpdate(placeName, category, area, address, phoneno, description, bitmap);
				 rowItems.add(item);
	          
		 }
		 listView = (ListView) findViewById(R.id.listView1);
         CustomListViewAdapterUpdate adapter = new CustomListViewAdapterUpdate(this,R.layout.searchtable, rowItems);
         listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(this);
      /*  gv= (GridView)findViewById(R.id.gridview);
	 DBHelper entry=new DBHelper(UpdateOrDeleteActivity.this);
	 entry.openForRead();
	 Cursor c=entry.getViewReults();
	 
	 
	 String cols[]={"PLACE_NAME","CATEGORY","AREA","LANDMARK","PHONE_NO","LANDLINE_NO","DESCRIPTION"};
	// int names[]={R.id.txtname,R.id.txtCategory,R.id.txtArea,R.id.txtLandmark,R.id.txtPhoneno,R.id.txtLandline,R.id.txtDescription};
	 
	 int[] names=new int[]{R.id.TextView08,R.id.TextView05,R.id.TextView06,R.id.TextView03,R.id.TextView02,R.id.TextView01,R.id.TextView04};

	 
	
	SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.searchdata,c,cols,names);
	gv.setAdapter(adapter); 
	gv.setOnItemLongClickListener(this);
       */
        
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btnEditUD:
			Intent iOK=new Intent(getApplicationContext(),UpdateFieldsActivity.class);
			startActivity(iOK);
			break;
			
		case R.id.btnDeleteUD:
			DBHelper entry=new DBHelper(UpdateOrDeleteActivity.this);
			 entry.openForWrite();
			entry.deletePlace();
			Toast.makeText(getApplicationContext(),ModelLocator.PLACE+" Deleted Successfuly",2000).show();
			onCreate(null);
			break;
		case R.id.headericon:
			Intent ihome=new Intent(getApplicationContext(),HomePgActivity.class);
			startActivity(ihome);
			break;
		case R.id.imgsearch:
			Intent isearch=new Intent(getApplicationContext(),SearchActivity.class);
			startActivity(isearch);

			break;
		case R.id.imglogout:
			Intent ilogout=new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(ilogout);
			break;
		
		}
	}



	/*@Override
	public boolean onItemLongClick(AdapterView arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Cursor c=(Cursor)arg0.getItemAtPosition(arg2);
		//Cursor c=(Cursor)((AdapterView<ListAdapter>) arg1).getItemAtPosition(arg2);
		placename=c.getString(c.getColumnIndex("PLACE_NAME"));
		ModelLocator.P_ID=c.getString(c.getColumnIndex("_id"));
		edtSelectNameUD.setText(placename);
		return false;
	}
*/
	

		
	

@Override
public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
		long arg3) {
	// TODO Auto-generated method stub
	CustomListViewAdapterUpdate adapterUpdate=new CustomListViewAdapterUpdate(this,R.layout.searchtable, rowItems);
	String selectedItem =adapterUpdate.getSelectedItem(arg2, arg1, arg0);
	edtSelectNameUD.setText(selectedItem);




	 Cursor c=entry.getSelectedItemID();

	 c.moveToFirst();
	 ModelLocator.P_ID=c.getString(c.getColumnIndex("_id"));


	return false;
}

}
