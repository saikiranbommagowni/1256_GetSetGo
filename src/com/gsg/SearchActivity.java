package com.gsg;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.data.ModelLocator;
import com.gsg.R;

public class SearchActivity extends Activity implements OnClickListener,OnItemSelectedListener {

	Spinner spinAreas=null;
	Spinner spinCategories=null;
	Button btnReset,btnSearch;
	String selectedArea,selectedCategory;
	List<String> areaLabels,categoryLabels;
	ImageView imgHeaderIcon=null;
	String s;
	DBHelper entry=null;;
	@Override
	   public void onCreate(Bundle savedInstanceState)
	
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		 spinAreas=(Spinner)findViewById(R.id.spinAreaHm);
	        spinCategories=(Spinner)findViewById(R.id.spinCategoryHm);
	        btnSearch=(Button)findViewById(R.id.btnSearchHm);
	        btnReset=(Button)findViewById(R.id.btnResetHm);
	        
			 imgHeaderIcon=new ImageView(getApplicationContext());
				imgHeaderIcon=(ImageView)findViewById(R.id.headericon);

	        
	         entry=new DBHelper(SearchActivity.this);
			 entry.openForRead();
			 
			 
			 			    
			    
			    
			    categoryLabels=entry.getCategories();
			    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnertext,categoryLabels);
			    categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			    spinCategories.setAdapter(categoryAdapter);
			  
			    areaLabels=entry.getAreas(); 
				ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnertext,areaLabels);
			    areaAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
			    spinAreas.setAdapter(areaAdapter);
	
	           
	        btnSearch.setOnClickListener(this);
	        btnReset.setOnClickListener(this);
			imgHeaderIcon.setOnClickListener(this);

	        
	        spinAreas.setOnItemSelectedListener(this);
	        spinCategories.setOnItemSelectedListener(this);
	
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSearchHm:
			Intent iSearch=new Intent(getApplicationContext(),SearchResultsActivity.class);
			startActivity(iSearch);
			break;
		case R.id.btnResetHm:
		         onCreate(null);
			break;
		case R.id.headericon:
			Intent ihome=new Intent(getApplicationContext(),HomePgActivity.class);
			startActivity(ihome);
			break;
		}
	}
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		
		case R.id.spinCategoryHm:
			
			String s=arg0.getSelectedItem().toString();
			ModelLocator.SPIN_CATEGORY=s;
			selectSpinner(s);
			
			break;
		case R.id.spinAreaHm:
			ModelLocator.SPIN_AREA=arg0.getSelectedItem().toString();
			break;
		}
		
	}
	
	
	public void selectSpinner(String s){
		
		entry.setCategory(s);
		
		/*modelLabels = entry.getAutoAreas();
		ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.spinnertext, modelLabels);
		modelAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinModel.setAdapter(modelAdapter);
*/
		areaLabels=entry.getAreas(); 
		
		ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnertext,areaLabels);
	    areaAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	    spinAreas.setAdapter(areaAdapter);
	    
	}

	
	
	
	
	
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}