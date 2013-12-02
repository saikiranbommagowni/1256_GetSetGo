package com.gsg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.data.ModelLocator;
import com.gsg.R;

public class ChangePasswordActivity extends Activity implements OnClickListener {
	
	Button btnSave,btnReset;
	EditText edtOldPassword,edtNewPassword,edtConfirmPassword;
	String cnpswd,opswd;
	String  npswd;
	 ImageView imgHeaderIcon,imgLogout,imgSearch=null;

	@Override
 public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepasswordpg);
		
		btnSave=(Button)findViewById(R.id.btnSaveCP);
		btnReset=(Button)findViewById(R.id.btnResetCP);
		edtOldPassword=(EditText)findViewById(R.id.edtOldPasswordCP);
		edtNewPassword=(EditText)findViewById(R.id.edtNewPasswordCP);
		edtConfirmPassword=(EditText)findViewById(R.id.edtConfirmPasswordCP);
		
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
		
				
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btnSaveCP:
			cnpswd= edtConfirmPassword.getText().toString();
			npswd= edtNewPassword.getText().toString();
			opswd=edtOldPassword.getText().toString();
			if(opswd.equals(ModelLocator.PASSWORD)){
				
				if(npswd.equals(cnpswd)){
					
					DBHelper entry=new DBHelper(ChangePasswordActivity.this);
					entry.openForWrite();
					entry.setNewPassword(npswd);
					Intent i=new Intent(getApplicationContext(),MenuPgActivity.class);
					startActivity(i);
					Toast.makeText(getApplicationContext(), "Changed Successfully", 3000).show();
				}else{
					Toast.makeText(getApplicationContext(), "Password didn't match", 3000).show();
				}
			}else{
				Toast.makeText(getApplicationContext(), "Enter correct password", 3000).show();
			}
			
			break;

		case R.id.btnResetCP:
			edtOldPassword.setText("");
			edtNewPassword.setText("");
			edtConfirmPassword.setText("");
			
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
