package com.gsg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.data.ModelLocator;
import com.gsg.R;

public class LoginActivity extends Activity {
   EditText edTxtUserName=null; 
   EditText edTxtPassword=null;
   Button btnLogin=null;
   Button btnClear=null,btnCreateAccount=null,btnForgotPassword=null;
 	 ImageView imgHeaderIcon,imgSearch=null;

   	@Override
 public void onCreate(Bundle savedInstanceState)
 {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    btnClear=(Button)findViewById(R.id.btnClearLogin);
    btnLogin=(Button)findViewById(R.id.btnLoginL);
    btnCreateAccount=(Button)findViewById(R.id.btnCreateAccountLogin);
    btnForgotPassword=(Button)findViewById(R.id.btnForgotPswdLogin);
    edTxtUserName=(EditText)findViewById(R.id.edTxtUserNameLogin);
    edTxtPassword=(EditText)findViewById(R.id.edtxtPasswordLogin);
    
    imgHeaderIcon=new ImageView(getApplicationContext());
	 imgSearch=new ImageView(getApplicationContext());

	imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
	imgSearch=(ImageView)findViewById(R.id.imgsearch);

	
    
    View.OnClickListener listener=new View.OnClickListener() {
		
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
				
			String uname= edTxtUserName.getText().toString();
			String pswd= edTxtPassword.getText().toString();
			
			//Push value into 
			ModelLocator.USER_NAME = uname;
			ModelLocator.PASSWORD=pswd;
			
				switch  (v.getId())
				{
				case R.id.btnLoginL:
					if(ModelLocator.USER_NAME.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter Username",2000 ).show();
					}else
					if(ModelLocator.PASSWORD.equals(""))
						Toast.makeText(getApplicationContext(), "Enter Password",2000 ).show();
						if(ModelLocator.USER_NAME.equals(""))
							Toast.makeText(getApplicationContext(), "Enter Username",2000 ).show();
		            else{
		            	
					DBHelper entry=new DBHelper(LoginActivity.this);
					entry.openForRead();
					Cursor c=entry.getLoginData();
					
					for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						String usern=c.getString(c.getColumnIndex("USER_NAME"));
						if(usern.equals(ModelLocator.USER_NAME)){
							String password=c.getString(c.getColumnIndex("PASSWORD"));
							if(password.equals(ModelLocator.PASSWORD)){
								Intent imenu=new Intent(getApplicationContext(),MenuPgActivity.class);
								startActivity(imenu);
							}
							else{
								Toast.makeText(getApplicationContext(), "Enter valid username & password", 5000).show();
							}
						}else{
							//Toast.makeText(getApplicationContext(), "Enter valid username & password", 5000).show();
						}
						
					}
					entry.close();
					
		            }
					   break;
				case R.id.btnClearLogin:
					edTxtUserName.setText("");
					edTxtPassword.setText("");
					break;
				case R.id.imgsearch:
					Intent isearch=new Intent(getApplicationContext(),SearchActivity.class);
					startActivity(isearch);

					break;
				case R.id.headericon:
					Intent ihome=new Intent(getApplicationContext(),HomePgActivity.class);
					startActivity(ihome);
					break;
				case R.id.btnCreateAccountLogin:
					Intent isignup=new Intent(getApplicationContext(),SignUpActivity.class);
					startActivity(isignup);
					break;
				case R.id.btnForgotPswdLogin:
					Intent ipasswordrecovery=new Intent(getApplicationContext(),PasswordRecoveryActivity.class);
					startActivity(ipasswordrecovery);
					break;		
				}
			}
	};
	btnClear.setOnClickListener(listener);
	btnLogin.setOnClickListener(listener);
	btnCreateAccount.setOnClickListener(listener);
	btnForgotPassword.setOnClickListener(listener); 
	imgHeaderIcon.setOnClickListener(listener);
	imgSearch.setOnClickListener(listener);

 }

	 }
 
								
		       