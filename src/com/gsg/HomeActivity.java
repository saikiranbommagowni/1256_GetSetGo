package com.gsg;

import android.app.Activity;

public class HomeActivity extends Activity/* implements OnClickListener, OnItemSelectedListener*/ {
    /** Called when the activity is first created. */
	/*Spinner spinAreas=null;
	Spinner spinCategories=null;
	Button btnAbout,btnFindCabs,btnReset,btnSearch,btnView;
	//String arrCategories[]={"Select Category","Food Courts","Shopping Malls","Restaurants","Bakeries","Theatres","Food Courts","Shopping Malls","Restaurants","Bakeries","Theatres"};
	//String arrAreas[]={"Select Area","L.B.Nagar","Dilsukh Nagar","Karmanghat","Santosh Nagar","Malakpet","Banjara Hills"};
	String selectedArea,selectedCategory;
	List<String> areaLabels,categoryLabels;
	@SuppressWarnings({ "rawtypes" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        //GregorianCalendar date=new GregorianCalendar();
        
        //Date d=new Date();
        //Toast.makeText(getApplicationContext(), date, 3000).show();
        spinAreas=(Spinner)findViewById(R.id.spinAreaHm);
        spinCategories=(Spinner)findViewById(R.id.spinCategoryHm);
        
        
	    
       
        btnSearch=(Button)findViewById(R.id.btnSearchHm);
        btnReset=(Button)findViewById(R.id.btnResetHm);
        btnView=(Button)findViewById(R.id.btnViewHm);
        btnFindCabs=(Button)findViewById(R.id.btnFindCabsHm);
        btnAbout=(Button)findViewById(R.id.btnAboutHm);

        
        GsgHelper entry=new GsgHelper(HomeActivity.this);
		 entry.openForRead();
		 
		 
		 	areaLabels=entry.getAreas(); 
			
			ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnertext,areaLabels);
		    areaAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		    spinAreas.setAdapter(areaAdapter);
		    
		    
		    
		    categoryLabels=entry.getCategories();
		    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnertext,categoryLabels);
		    categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		    spinCategories.setAdapter(categoryAdapter);
		  
        
        
           
        btnSearch.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnFindCabs.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        
        
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
			//spinAreas.set
			break;
		case R.id.btnViewHm:
			Intent iView=new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(iView);
			break;
		case R.id.btnFindCabsHm:
			Intent iFindCabs=new Intent(getApplicationContext(),CabsActivity.class);
			startActivity(iFindCabs);
			break;
		case R.id.btnAboutHm:
			Intent iAbout=new Intent(getApplicationContext(),AboutActivity.class);
			startActivity(iAbout);
			break;	

		}
		
	}
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.spinAreaHm:
			ModelLocator.SPIN_AREA=arg0.getSelectedItem().toString();
			break;
		case R.id.spinCategoryHm:
			ModelLocator.SPIN_CATEGORY=arg0.getSelectedItem().toString();
			break;

		
		}
		
	}
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	*/
}
