package com.gsg;

import com.gsg.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUpActivity extends Activity implements OnClickListener{
   
	EditText edtFName,edtLName,edtUserName,edtPassword,edtConfirmPassword,edtEmailID,edtPhoneNo;
	DatePicker dps;
	Button submit,reset;
	RadioButton rbMale,rbFemale,rbOthers;
	String fname,lname,uname,pswd,cpswd,email,pno,dob,gender=null;
	int date,month,year,userid;
 	 ImageView imgHeaderIcon,imgSearch=null;

	@Override
   public void onCreate(Bundle savedInstanceState)
	{
      super.onCreate(savedInstanceState);
      setContentView(R.layout.signup);
      
      
      edtUserName=(EditText)findViewById(R.id.edtUserNamesu);
      edtPassword=(EditText)findViewById(R.id.edtPasswordsu);
      edtConfirmPassword=(EditText)findViewById(R.id.edtConfirmPasswordsu);
      edtEmailID=(EditText)findViewById(R.id.edtEmailIDsu);
      edtPhoneNo=(EditText)findViewById(R.id.edtPhoneNosu);
      dps=(DatePicker)findViewById(R.id.dpSignup);
      rbFemale=(RadioButton)findViewById(R.id.rbtnFemale);
      rbMale=(RadioButton)findViewById(R.id.rbtnMale);
      rbOthers=(RadioButton)findViewById(R.id.rbtnOthers);
      submit=(Button)findViewById(R.id.btnSubmitsu);
      reset=(Button)findViewById(R.id.btnResetsu);
      

		imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());

		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);
		imgHeaderIcon.setOnClickListener(this);
		imgSearch.setOnClickListener(this);

    
      submit.setOnClickListener(this);
      reset.setOnClickListener(this);
     // dps.setOnClickListener(this);
      
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		switch (v.getId()) {
		case R.id.btnSubmitsu:
		      uname=edtUserName.getText().toString();
		      pswd=edtPassword.getText().toString();
		      cpswd=edtConfirmPassword.getText().toString();
		      email=edtEmailID.getText().toString();
		      pno=edtPhoneNo.getText().toString();
		      
		       date=dps.getDayOfMonth();
		       month=dps.getMonth()+1;
		       year=dps.getYear();
		      dob=date+"/"+month+"/"+year;
		      if(validation()){
    		    DBHelper entry=new DBHelper(SignUpActivity.this);
				entry.openForWrite();
				entry.createAccount(uname,pswd,email,pno,dob,gender);
				entry.close();
				Intent i=new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
				 Toast.makeText(getApplicationContext(),uname+" Registered successfuly",2000).show();
		      }
			break;
		case R.id.btnResetsu :
				onCreate(null);
			break;
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
	
	private boolean validation() {
		// TODO Auto-generated method stub
 	    if(checked())
 	    {
 	    	//Toast.makeText(getApplicationContext(), "please select gender", Toast.LENGTH_LONG).show();
 	    }
 		else if(dps.getYear()>1995)
 	    {
 	    	Toast.makeText(getApplicationContext(), "your age should be of atleast 18 years", Toast.LENGTH_LONG).show();
 	    }
		/*else if(email.length()>=10  && email.contains("@") && email.contains("."))
        {
			Toast.makeText(getApplicationContext(), "Email_Id should be in the format abc@gmail.com", Toast.LENGTH_LONG).show();
        }*/
		else if(pno.length()!=10)
	    {
	    	Toast.makeText(getApplicationContext(), "Phone no should be of 10 digits", Toast.LENGTH_LONG).show();
	    }else 
	    	return true;
		return false;
		
	}
	
		
	public boolean checked(){
		
		if(rbFemale.isChecked()){
			gender="female";
		}else
			if(rbMale.isChecked()){
				gender="male";
			}else
				if(rbOthers.isChecked()){
				gender="other";
				}else{
					Toast.makeText(getApplicationContext(), "please select gender",2000).show();
					return true;	
				}
		return false;
	}
}
   
	
	