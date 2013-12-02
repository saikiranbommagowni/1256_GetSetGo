package com.gsg;

import com.gsg.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomePgActivity extends Activity implements OnClickListener{

	ImageView imgHeaderIcon,imgFinCab,imgAbout,imgSearch,imgRegister,imgLogin=null;
//	Drawable icon;
	
	// this context will use when we work with Alert Dialog
			final Context context = this;
			DBHelper helper=null;
			
			// just for test, when we click this button, we will see the alert dialog.
			private ImageView imgExit;
	@Override
	   public void onCreate(Bundle savedInstanceState)
	
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepg);
		
		helper=new DBHelper(getBaseContext());
		helper.openDataBase();
		
		//ImageView imgAbout =new ImageView(getApplicationContext());
		
		 
		
		
		imgLogin=new ImageView(getApplicationContext());
		 imgRegister=new ImageView(getApplicationContext());
		imgFinCab =new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());
		 imgAbout=new ImageView(getApplicationContext());
		 imgHeaderIcon=new ImageView(getApplicationContext());
	      imgExit=new ImageView(getApplicationContext());



			//mapping ids of images
			imgSearch =(ImageView)findViewById(R.id.imgsearch);
			imgLogin=(ImageView)findViewById(R.id.imglogin);
			imgRegister=(ImageView)findViewById(R.id.imgregister);
			imgFinCab=(ImageView)findViewById(R.id.imgcab);
			imgAbout=(ImageView)findViewById(R.id.imgabout);
			imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
			imgExit = (ImageView) findViewById(R.id.exitApp);
		
				//setting Listeners
				imgHeaderIcon.setOnClickListener(this);
				imgSearch.setOnClickListener(this);
				imgLogin.setOnClickListener(this);
				imgRegister.setOnClickListener(this);
				imgFinCab.setOnClickListener(this);
				imgAbout.setOnClickListener(this);
				imgExit.setOnClickListener(this);
		//imgHeaderIcon=(ImageView)findViewById(R.id.headericon);


		
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgsearch:
			Intent iSearch=new Intent(getApplicationContext(),SearchActivity.class);
			startActivity(iSearch);
			break;
		case R.id.imglogin:
			Intent ilogin=new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(ilogin);
			break;	
		case R.id.imgregister:
			Intent iregister=new Intent(getApplicationContext(),SignUpActivity.class);
			startActivity(iregister);
			break;
		case R.id.imgcab:
			Intent icab=new Intent(getApplicationContext(),CabsActivity.class);
			startActivity(icab);
			break;
		case R.id.imgabout:
			//Toast.makeText(getApplicationContext(),"hai",2000).show();
		Intent iabout=new Intent(getApplicationContext(),AboutActivity.class);
		startActivity(iabout);
			break;
		case R.id.headericon:
			//Toast.makeText(getApplicationContext(),"hai",2000).show();
		onCreate(null);
			//Intent iabout=new Intent(getApplicationContext(),AboutActivity.class);
			//startActivity(iabout);
			break;
		case R.id.exitApp:
			//Toast.makeText(getApplicationContext(),"hai",2000).show();
			 /* Alert Dialog Code Start*/ 	
        	AlertDialog.Builder alert = new AlertDialog.Builder(context);
        	alert.setTitle("Exit Application"); //Set Alert dialog title here
        	alert.setMessage("Do you want to Exit ?"); //Message here
        	//alert.setIcon(icon);
            // Set an EditText view to get user input 
          //  final EditText input = new EditText(context);
          //  alert.setView(input);

        	alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        	 //You will get as string input data in this variable.
        	 // here we convert the input to a string and show in a toast.
        	// String srt = input.getEditableText().toString();
        		helper.close();
        		HomePgActivity.this.finish();
        		android.os.Process.killProcess(android.os.Process.myPid());
        	// Toast.makeText(context,srt,Toast.LENGTH_LONG).show();        		
        	} // End of onClick(DialogInterface dialog, int whichButton)
        }); //End of alert.setPositiveButton
        	alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
        	  public void onClick(DialogInterface dialog, int whichButton) {
        	    // Canceled.
        		  dialog.cancel();
        	  }
        }); //End of alert.setNegativeButton
        	AlertDialog alertDialog = alert.create();
        	alertDialog.show();
       /* Alert Dialog Code End*/        
			break;
		}			
	}

}
