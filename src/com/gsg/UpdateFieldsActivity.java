package com.gsg;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gsg.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class UpdateFieldsActivity extends Activity implements OnClickListener {
	EditText edtArea,edtCategory,edtName,edtAddress,edtPhoneNo,edtDescription;
	Button btnUpdate,btnReset,btnTakeSnap,btnGallery;
	ImageView image1;
	String  placename, area, category,address, phoneno,description,inserteddate,insertedtime;
	String date,usern,privacy;
	RadioButton rbtnPublic,rbtnPrivate;
	ImageView imgHeaderIcon,imgLogout,imgSearch=null;
	
	AutoCompleteTextView autoCategory,autoArea=null;
	String areaItems[],categoryItems[]=null;
	
	
	private static final String TAG = "CallCamera";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 1;
	private static final int RESULT_LOAD_IMAGE = 2;
	private Bitmap bitmap;
	private ImageView imageView;
	private Button btn = null;	
	private byte[] byteArray=null;
	 
	SQLiteDatabase db = null;
		
	Uri fileUri = null;
	ImageView photoImage = null;
	Button  callTakeSnapButton;
	DBHelper entry=null;
	
	
	//String pid=null;
	@Override
 public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatefields);
		edtArea=(EditText)findViewById(R.id.edtAreaUF);
		edtCategory=(EditText)findViewById(R.id.edtCategoryUF);
		edtName=(EditText)findViewById(R.id.edtNameUF);
		edtAddress=(EditText)findViewById(R.id.edtAddressUF);
		edtPhoneNo=(EditText)findViewById(R.id.edtPhonenoUF);
		edtDescription=(EditText)findViewById(R.id.edtDescriptionUF);
		btnUpdate=(Button)findViewById(R.id.btnUpdateUF);
		btnReset=(Button)findViewById(R.id.btnResetUF);
	//	btnGallery=(Button)findViewById(R.id.btnGalleryUF);
	//	btnTakeSnap=(Button)findViewById(R.id.btnTakeSnapUF);
		image1=(ImageView)findViewById(R.id.imgView_1);
		rbtnPrivate=(RadioButton)findViewById(R.id.rbtnPrivateUF);
		rbtnPublic=(RadioButton)findViewById(R.id.rbtnPublicUF);
		
		imgHeaderIcon=new ImageView(getApplicationContext());
		imgSearch=new ImageView(getApplicationContext());
		imgLogout=new ImageView(getApplicationContext());

		 
		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
		imgLogout=(ImageView)findViewById(R.id.imglogout);

		imgHeaderIcon.setOnClickListener(this);
		imgSearch.setOnClickListener(this);
		imgLogout.setOnClickListener(this);

		btnUpdate.setOnClickListener(this);
		btnReset.setOnClickListener(this);
		   
		   
		  entry=new DBHelper(UpdateFieldsActivity.this);
		 entry.openForRead();
		 Cursor c=entry.getPlaceDetails();
		 
		 if(c.moveToFirst()){
			 
		 
			 
		 		//String id=c.getString(c.getColumnIndex("PLACE_ID"));
				 placename=c.getString(c.getColumnIndex("PLACE_NAME"));
				 category=c.getString(c.getColumnIndex("CATEGORY"));
				 area=c.getString(c.getColumnIndex("AREA"));
				 address=c.getString(c.getColumnIndex("ADDRESS"));
				 phoneno=c.getString(c.getColumnIndex("PHONE_NO"));
				 description=c.getString(c.getColumnIndex("DESCRIPTION"));
				 privacy=c.getString(c.getColumnIndex("PRIVACY_LEVEL"));
				 
				 
				 byteArray = c.getBlob(c.getColumnIndex("IMAGE1")); //This line gets the image's blob data
				 bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length); //Convert bytearray to bitmap
		            
		 }
		 
		 edtName.setText(placename);
		 edtCategory.setText(category);
		 edtArea.setText(area);
		 edtAddress.setText(address);
		 edtDescription.setText(description);
		 edtPhoneNo.setText(phoneno);
		 image1.setImageBitmap(bitmap);
		 if(privacy.equalsIgnoreCase("public")){
			 rbtnPublic.setChecked(true);
		 }else{
			 rbtnPrivate.setChecked(true);
		 }
		 
		 
		/* List<String> areaList=new ArrayList<String>();
		   areaList=entry.getAutoAreas();
		   Collections.sort(areaList);
		   ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, areaList);
		   areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   edtName.setThreshold(1);
		   edtName.setAdapter(areaAdapter);
		   
		   
		   List<String> categoryList=new ArrayList<String>();
		   categoryList=entry.getAutoCategories();
		   Collections.sort(categoryList);
		   ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, categoryList);
		   areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   autoCategory.setThreshold(1);
		   autoCategory.setAdapter(categoryAdapter);
	
		 */
		 
	}
		 
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnUpdateUF:
			placename = edtName.getText().toString();
			area = edtArea.getText().toString();
			category = edtCategory.getText().toString();
			address = edtAddress.getText().toString();
			phoneno = edtPhoneNo.getText().toString();
			description=edtDescription.getText().toString();
			privacy=checked();
			
	        
		
			 entry.updatePlace(placename,area,category,address,phoneno,description,byteArray,privacy);
			 Intent i=new Intent(getApplicationContext(),MenuPgActivity.class);
			 startActivity(i);
			 Toast.makeText(getApplicationContext(),placename+" addded successfuly",2000).show();
		
				
				break;
		case R.id.btnResetUF:
			super.onCreate(null);
			break;
		case R.id.btnTakeSnapUF:
			Intent intImg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intImg, CAPTURE_IMAGE_ACTIVITY_REQ);
			break;
		case R.id.btnGalleryUF:
			
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
	public String checked(){
		
		if(rbtnPrivate.isChecked()){
			privacy="private";
		}else
			if(rbtnPublic.isChecked()){
				privacy="public";
			}	
		return privacy;
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.rbtnPrivate:
			if(arg1==true)
			Toast.makeText(getApplicationContext(),"The added place will be visible only to you",4000).show();
			break;
		case R.id.rbtnPublic:
			if(arg1==true)
			Toast.makeText(getApplicationContext(),"The added place will be visible to all",3000).show();
			break;
	
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
			    bitmap = (Bitmap) data.getExtras().get("data"); 
		       	photoImage.setImageBitmap(bitmap);
		       	
		       	//Converting bitmap data into bytearray
		       	ByteArrayOutputStream stream = new ByteArrayOutputStream();
		       	bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		        byteArray = stream.toByteArray();
		  }
	}
}