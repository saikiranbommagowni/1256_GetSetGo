package com.gsg;

import com.gsg.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordRecoveryActivity extends Activity {
	EditText edTxtEmail = null;
	Button btnEnter = null, btnClear = null;
	TextView txtRPassword = null;
	boolean result=false;
  	 ImageView imgHeaderIcon,imgSearch=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passwordrecovery);
		edTxtEmail = (EditText) findViewById(R.id.edtTxtEmailPR);
		btnEnter = (Button) findViewById(R.id.btnEnterPR);
		btnClear = (Button) findViewById(R.id.btnClearPR);
		txtRPassword = (TextView) findViewById(R.id.txtRecoveredPassword);
		
		
		 imgHeaderIcon=new ImageView(getApplicationContext());
		 imgSearch=new ImageView(getApplicationContext());

		imgHeaderIcon=(ImageView)findViewById(R.id.headericon);
		imgSearch=(ImageView)findViewById(R.id.imgsearch);

		View.OnClickListener listener = new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String emailId = edTxtEmail.getText().toString();
				switch (v.getId()) {
				case R.id.btnEnterPR:
					if (emailId.equals("")) {
						Toast.makeText(getApplicationContext(),
								"please enter email-id", Toast.LENGTH_LONG)
								.show();
					} else {
						
						DBHelper entry = new DBHelper(PasswordRecoveryActivity.this);
						entry.openForRead();
						Cursor c = entry.getForgotPassword();
						for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
							
							String email = c.getString(c.getColumnIndex("EMAIL_ID"));
							if (email.equals(emailId)) {
								String password = "Your Password is:\n"+ c.getString(c.getColumnIndex("PASSWORD"));
								txtRPassword.setText(password);
								result=true;
							} 
						}
						if(result==false){
							Toast.makeText(getApplicationContext(),"please enter valid email-id",Toast.LENGTH_LONG).show();
						result=false;
						}
					}
					break;
					
					
				   case R.id.btnClearPR:
					edTxtEmail.setText("");
					txtRPassword.setText("");
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
		};
		btnEnter.setOnClickListener(listener);
		btnClear.setOnClickListener(listener);
		imgHeaderIcon.setOnClickListener(listener);
		imgSearch.setOnClickListener(listener);

	}
}
