package com.gsg;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.gsg.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNewPlaceActivity extends Activity implements OnClickListener, OnCheckedChangeListener{
	EditText edtName,edtAddress,edtPhoneNo;
	Button btnSave,btnReset,btnTakeSnap,btnGallery;
	ImageView image1,imgHeaderIcon,imgLogout,imgSearch=null;;
	String  placename, area, category, address,description,phoneno,inserteddate,insertedtime;
	String date,usern,privacy,areai,categoryi;
	RadioButton rbtnPublic,rbtnPrivate;
	RadioGroup rbgPrivacy;
	AutoCompleteTextView autoCategory,autoArea=null;
	String areaItems[],categoryItems[]=null;
	
	
	
	
	
	//CAMERA
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
	@Override
 public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnewplace);
		 
		autoArea=(AutoCompleteTextView)findViewById(R.id.autoCompAreaANP);
		autoCategory=(AutoCompleteTextView)findViewById(R.id.autoCompCategoryANP);
		edtName=(EditText)findViewById(R.id.edtNameANP);
		edtAddress=(EditText)findViewById(R.id.edtAddressANP);
		edtPhoneNo=(EditText)findViewById(R.id.edtPhonenoANP);
		btnSave=(Button)findViewById(R.id.btnSaveANP);
		btnReset=(Button)findViewById(R.id.btnResetANP);
		
		rbtnPrivate=(RadioButton)findViewById(R.id.rbtnPrivate);
		rbtnPublic=(RadioButton)findViewById(R.id.rbtnPublic);
		rbgPrivacy=(RadioGroup)findViewById(R.id.rbtnGroup);
		
		//camera
		photoImage =(ImageView)findViewById(R.id.imgView_1);
		callTakeSnapButton =(Button)findViewById(R.id.btnTakeSnapANP);
		callTakeSnapButton.setOnClickListener(this);
		
		btnGallery=(Button)findViewById(R.id.btnGalleryANP);
		btnGallery.setOnClickListener(this);

		imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());
		imgLogout=new ImageView(getApplicationContext());

		 
		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
			imgLogout=(ImageView)findViewById(R.id.imglogout);

			  imgHeaderIcon.setOnClickListener(this);
				imgSearch.setOnClickListener(this);
			   imgLogout.setOnClickListener(this);


		   btnSave.setOnClickListener(this);
		   btnReset.setOnClickListener(this);
		   rbtnPrivate.setOnCheckedChangeListener(this);
		   rbtnPublic.setOnCheckedChangeListener(this);
		   
		   List<String> areaList=new ArrayList<String>();
		   DBHelper entry=new DBHelper(AddNewPlaceActivity.this);
		   entry.openForRead();
		   areaList=entry.getAutoAreas();
		   Collections.sort(areaList);
		   ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, areaList);
		   areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   autoArea.setThreshold(1);
		   autoArea.setAdapter(areaAdapter);
		   
		   
		   List<String> categoryList=new ArrayList<String>();
		   categoryList=entry.getAutoCategories();
		   Collections.sort(categoryList);
		   ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, categoryList);
		   areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   autoCategory.setThreshold(1);
		   autoCategory.setAdapter(categoryAdapter);
	
	
		  //camera
		   
		   
		   
	}
	     
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnSaveANP:
			placename = edtName.getText().toString();
			area = autoArea.getText().toString();
			category = autoCategory.getText().toString();
			address = edtAddress.getText().toString();
			phoneno = edtPhoneNo.getText().toString();
			privacy=checked();
			
	        
		
			 DBHelper entry=new DBHelper(AddNewPlaceActivity.this);
			 entry.openForWrite();
			 entry.addPlace(placename,area,category,address,phoneno,description,byteArray,privacy);
			 entry.close();
			 Intent i=new Intent(getApplicationContext(),MenuPgActivity.class);
			 startActivity(i);
			 Toast.makeText(getApplicationContext(),placename+" addded successfuly",2000).show();
		
			
			
			
			break;
		case R.id.btnResetANP:
			onCreate(null);
			break;
		case R.id.btnTakeSnapANP:
			
			Intent intImg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			/*fileUri = Uri.fromFile(getOutputPhotoFile());
			intImg.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
			startActivityForResult(intImg, CAPTURE_IMAGE_ACTIVITY_REQ);
		
			break;
		case R.id.btnGalleryANP:

			Intent iGallery = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			
			startActivityForResult(iGallery, RESULT_LOAD_IMAGE);
			
			
			
			
			
			/*Intent iGallery = new Intent();
			iGallery.setAction(Intent.ACTION_VIEW);
			iGallery.setData(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			
			startActivityForResult(iGallery, RESULT_LOAD_IMAGE);
*/
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
		  
		  else if (requestCode == RESULT_LOAD_IMAGE) {
				
			  super.onActivityResult(requestCode, resultCode, data);
		    	
				if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
					Uri selectedImage = data.getData();
					String[] filePathColumn = { MediaStore.Images.Media.DATA };

					Cursor cursor = getContentResolver().query(selectedImage,
							filePathColumn, null, null, null);
					cursor.moveToFirst();

					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					String picturePath = cursor.getString(columnIndex);
					cursor.close();
					bitmap=BitmapFactory.decodeFile(picturePath);
					photoImage.setImageBitmap(bitmap);
					
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
			       	bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			        byteArray = stream.toByteArray();
				}

			  
			  
			  
			  
			  
			  /*Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				
				ImageView imageView = (ImageView) findViewById(R.id.imgView_1);
				imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			*/
			}

		  
	}
	
	/*private void showPhoto(String photoUri) {
		  File imageFile = new File (photoUri);
		  if (imageFile.exists()){
		     Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		     BitmapDrawable drawable = new BitmapDrawable(this.getResources(), bitmap);
		     photoImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
		     photoImage.setImageDrawable(drawable);
		  }       
	}
	
	private File getOutputPhotoFile() {
		
		  File directory = new File(Environment.getExternalStoragePublicDirectory(
		                Environment.DIRECTORY_PICTURES), getPackageName());
		  
		  if (!directory.exists()) {
		    if (!directory.mkdirs()) {
		      Log.e(TAG, "Failed to create storage directory.");
		      return null;
		    }
		  }
		  
		  String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.US).format(new Date());
		  
		  return new File(directory.getPath() + File.separator + "IMG_"  
		                    + timeStamp + ".jpg");
	}
	*/

}