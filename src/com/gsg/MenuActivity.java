package com.gsg;

import com.gsg.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends Activity implements OnClickListener{
	
	Button btnViewOwnPlaces,btnAddOwnPlace,btnUpdateOrDelete,btnChangePassword,btnLogout;
	String usern,pswd;	
	ImageView imgHeaderIcon,imgLogout,imgSearch=null;

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	 {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menupg);
	    
	    
	    btnViewOwnPlaces=(Button)findViewById(R.id.btnViewOwnPlacesWU);
	    btnAddOwnPlace=(Button)findViewById(R.id.btnAddOwnPlaceWU);
	    btnUpdateOrDelete=(Button)findViewById(R.id.btnUpdateOrDeleteWU);
	    btnChangePassword=(Button)findViewById(R.id.btnChangePasswordWU);
	    btnLogout=(Button)findViewById(R.id.btnLogoutWU);
	    
	    imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());
			imgLogout=new ImageView(getApplicationContext());

		 
		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
			imgLogout=(ImageView)findViewById(R.id.imglogout);

			  imgHeaderIcon.setOnClickListener(this);
				imgSearch.setOnClickListener(this);
			   imgLogout.setOnClickListener(this);

	    btnViewOwnPlaces.setOnClickListener(this);
        btnAddOwnPlace.setOnClickListener(this);
        btnUpdateOrDelete.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
	 }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btnViewOwnPlacesWU:
			Intent iView=new Intent(getApplicationContext(),ViewResultsActivity.class);
			startActivity(iView);
			break;
		case R.id.btnAddOwnPlaceWU:
			Intent iAddPlace=new Intent(getApplicationContext(),AddNewPlaceActivity.class);
			startActivity(iAddPlace);
			break;
		case R.id.btnUpdateOrDeleteWU:
			Intent iUpdate=new Intent(getApplicationContext(),UpdateOrDeleteActivity.class);
			startActivity(iUpdate);
			break;
		case R.id.btnChangePasswordWU:
			
			Intent iChangePassword=new Intent(getApplicationContext(),ChangePasswordActivity.class);
			startActivity(iChangePassword);
			break;
		case R.id.btnLogoutWU:
			Intent iLogout=new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(iLogout);
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
}
