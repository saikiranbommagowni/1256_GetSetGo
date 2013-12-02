package com.gsg;

import com.gsg.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ExitAlertBox extends Activity {
	 /** Called when the activity is first created. */
    
		// this context will use when we work with Alert Dialog
		final Context context = this;
		
		// just for test, when we click this button, we will see the alert dialog.
		private ImageView imgExit;
			
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.homepg);
	        
	        imgExit = (ImageView) findViewById(R.id.exitApp);
	        
	        //Create onclick listener class
	        imgExit.setOnClickListener(new View.OnClickListener() {
	        //When you click the button, Alert dialog will be showed
	        public void onClick(View v) {
	       /* Alert Dialog Code Start*/ 	
	        	AlertDialog.Builder alert = new AlertDialog.Builder(context);
	        	alert.setTitle("Alert Dialog With EditText"); //Set Alert dialog title here
	        	alert.setMessage("Enter Your Name Here"); //Message here

	            // Set an EditText view to get user input 
	            final EditText input = new EditText(context);
	            alert.setView(input);

	        	alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        	 //You will get as string input data in this variable.
	        	 // here we convert the input to a string and show in a toast.
	        	 String srt = input.getEditableText().toString();
	        	 Toast.makeText(context,srt,Toast.LENGTH_LONG).show();        		
	        	} // End of onClick(DialogInterface dialog, int whichButton)
	        }); //End of alert.setPositiveButton
	        	alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    // Canceled.
	        		  dialog.cancel();
	        	  }
	        }); //End of alert.setNegativeButton
	        	AlertDialog alertDialog = alert.create();
	        	alertDialog.show();
	       /* Alert Dialog Code End*/        
	     }// End of onClick(View v)
	  }); //button.setOnClickListener
	 }//End of onCreate
	}//class AlertDialogExample extends Activity