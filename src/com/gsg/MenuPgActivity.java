package com.gsg;

import com.gsg.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MenuPgActivity extends Activity implements OnClickListener{
	
	//Button btnViewOwnPlaces,btnAddOwnPlace,btnUpdateOrDelete,btnChangePassword,btnLogout;
	//String usern,pswd;	
	ImageView imgHeaderIcon,imgLogout,imgSearch,imgViewPlace,imgAdd,imgUpdatedel,imgChPassword=null;

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	 {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menupg);
	    
	    
	    	    
	    imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());
			imgLogout=new ImageView(getApplicationContext());
			
			imgViewPlace=new ImageView(getApplicationContext());
			imgAdd=new ImageView(getApplicationContext());
			imgUpdatedel=new ImageView(getApplicationContext());
			imgChPassword=new ImageView(getApplicationContext());
		 
		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
			imgLogout=(ImageView)findViewById(R.id.imglogout);
			
			imgViewPlace=(ImageView)findViewById(R.id.imgview);
			imgAdd=(ImageView)findViewById(R.id.imgadd);
			imgUpdatedel=(ImageView)findViewById(R.id.imgupdel);
			imgChPassword=(ImageView)findViewById(R.id.imgchpassword);



			  imgHeaderIcon.setOnClickListener(this);
				imgSearch.setOnClickListener(this);
			   imgLogout.setOnClickListener(this);
			   
			   imgViewPlace.setOnClickListener(this);
			   imgAdd.setOnClickListener(this);
			   imgUpdatedel.setOnClickListener(this);
			   imgChPassword.setOnClickListener(this);


	   
	 }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.imgview:
			Intent iView=new Intent(getApplicationContext(),ViewResultsActivity.class);
			startActivity(iView);
			break;
		case R.id.imgadd:
			Intent iAddPlace=new Intent(getApplicationContext(),AddNewPlaceActivity.class);
			startActivity(iAddPlace);
			break;
		case R.id.imgupdel:
			Intent iUpdate=new Intent(getApplicationContext(),UpdateOrDeleteActivity.class);
			startActivity(iUpdate);
			break;
		case R.id.imgchpassword:
			
			Intent iChangePassword=new Intent(getApplicationContext(),ChangePasswordActivity.class);
			startActivity(iChangePassword);
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