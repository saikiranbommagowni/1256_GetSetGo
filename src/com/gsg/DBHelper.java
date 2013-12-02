package com.gsg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.data.ModelLocator;

public class DBHelper extends SQLiteOpenHelper{
	
	//USER_REGISTRATION TABLE COLUMNS
		public static final String KEY_USER_NAME="USER_NAME";
		public static final String KEY_PASSWORD="PASSWORD";
		public static final String KEY_EMAIL_ID="EMAIL_ID";
		public static final String KEY_PHONE_NO="PHONE_NO";	
		public static final String KEY_DOB="DOB";
		public static final String KEY_GENDER="GENDER";
		
		//ADD_NEW_PLACE TABLE COLUMNS
		public static final String KEY_PLACE_ID="_id";
		public static final String KEY_PLACE_NAME="PLACE_NAME";
		public static final String KEY_AREA="AREA";
		public static final String KEY_CATEGORY="CATEGORY";
		public static final String KEY_ADDRESS="ADDRESS";
		public static final String KEY_PHONE_NOS="PHONE_NO";
		public static final String KEY_DESCRIPTION="DESCRIPTION";
		public static final String KEY_IMAGE1="IMAGE1";
		public static final String KEY_PRIVACY_LEVEL="PRIVACY_LEVEL";
		public static final String KEY_USER_ID="USER_ID";
		
		
		
		//CAB_DETAILS TABLE COLUMNS
		public static final String KEY_CAB_ID="_id";
		public static final String KEY_CAB_NAME="CAB_NAME";
		public static final String KEY_CAB_AREA="CAB_AREA";
		public static final String KEY_CAB_ADDRESS="CAB_ADDRESS";
		public static final String KEY_PHONE1="PHONE1";
		public static final String KEY_CAB_IMAGE="CAB_IMAGE";
		public static final String KEY_CREATED_DATE="CREATED_DATE";
		public static final String KEY_CREATED_TIME="CREATED_TIME";
		
		
		//DATABASE DETAILS
	    private static final String DATABASE_NAME="GSG_DB.db";
		private static final String TABLE_USER_REGISTRATION="USER_REGISTRATION";
		private static final String TABLE_ADD_PLACE="ADD_NEW_PLACE";
		private static final String TABLE_CAB_DETAILS="CAB_DETAILS";
		private static final int DATABASE_VERSION=1;
		
		private DBHelper ourHelper;
		private final Context ourContext;
		private SQLiteDatabase ourDatabase;
		
		int userid;
		public String selection;
		boolean flag=false ;
	private static String DB_PATH = "/data/data/com.gsg/databases/";
	 
    private static String DB_NAME = "GSG_DB.db";
 
 

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DBHelper(Context context) {
 
    	super(context, DB_NAME, null, 3);
        this.ourContext = context;
        //Toast.makeText(ourContext, " Please Enter Correct ClientID", Toast.LENGTH_LONG).show();
    }	
    
    
    
    public DBHelper openForWrite(){
		ourHelper=new DBHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
	}
	
	public DBHelper openForRead(){
		ourHelper=new DBHelper(ourContext);
		ourDatabase=ourHelper.getReadableDatabase();
		return this;
	}
    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
    	Log.d("SOMETHING", "Content1");
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    		 String myPath = DB_PATH + DB_NAME;
    	    	ourDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	 
    		
    	}else{
 
    		//By calling this method an empty database will be created into the default system path
               //of your application so we  gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 boolean exist=false;
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    		
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
    		exist=true;
 
    	}
        
    	return exist;
    }
 
    /**
     * Copies our database from our local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = ourContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
    	Log.d("SOMETHING", "Content2");
    	//Open the database
    	try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String myPath = DB_PATH + DB_NAME;
    	ourDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
    
    
 
    @Override
	public synchronized void close() 
    {
 
    	    if(ourDatabase != null)
    		    ourDatabase.close();
	}
 
    public void createAccount(String uname,
			String pswd, String email, String pno,String dob,String gender) {
		// TODO Auto-generated method stub
	
		ContentValues cv =new ContentValues();
		
		cv.put(KEY_USER_NAME, uname);
		cv.put(KEY_PASSWORD, pswd);
		cv.put(KEY_GENDER,gender);
		cv.put(KEY_DOB, dob);
		cv.put(KEY_EMAIL_ID, email);
		cv.put(KEY_PHONE_NO, pno);
		try {
			ourDatabase.insert(TABLE_USER_REGISTRATION, null,cv);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	} 
	
    public Cursor getLoginData() {
		// TODO Auto-generated method stub
		
		String[] columns=new String[]{ KEY_USER_NAME, KEY_PASSWORD };
		Cursor c=ourDatabase.query(TABLE_USER_REGISTRATION, columns, null,null,null,null,null);
		return c;
	}

	public Cursor getForgotPassword() {
		// TODO Auto-generated method stub

		String[] columns=new String[]{KEY_PASSWORD,KEY_EMAIL_ID };
		Cursor c=ourDatabase.query(TABLE_USER_REGISTRATION, columns, null,null,null,null,null);
		return c;
	}

	public void setNewPassword(String npswd) {
		// TODO Auto-generated method stub
		ContentValues cv1= new ContentValues();
		cv1.put(KEY_PASSWORD, npswd);
		//cv1.put(KEY_USER_NAME, usern);
		ourDatabase.update(TABLE_USER_REGISTRATION, cv1,KEY_USER_NAME+"='"+ModelLocator.USER_NAME+"'", null);
		
		
	}
	
	
	
	
	
	
	//ADD_NEW_PLACE TABLE CODE
	
	/*public void addPlace(String placename, String area, String category,
			String address, String phoneno, String landlineno,
			String description,String privacy ,byte[] byteArray) {
	
		// TODO Auto-generated method stub
		String[] column=new String[]{ KEY_USER_ID};
		
		Cursor c=ourDatabase.query(TABLE_USER_REGISTRATION, column,KEY_USER_NAME+"='"+ModelLocator.USER_NAME+"'", null, null, null, null);
		 // Cursor c=ourDatabase.execSQL("SELECT USER_ID FROM USER_REGISTRATION WHERE USER_NAME='"+usern+"'");
		//for(c.moveToFirst();c.isAfterLast(); c.moveToNext())
		 //  {
				c.moveToNext();
			   userid = c.getInt(c.getColumnIndex(KEY_USER_ID));
		  // }
		ContentValues cv =new ContentValues();
		
		cv.put(KEY_PLACE_NAME, placename);
		cv.put(KEY_AREA, area);
		cv.put(KEY_CATEGORY, category);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_PHONE_NOS,phoneno);
		cv.put(KEY_DESCRIPTION, description);
		cv.put(KEY_USER_ID, userid);
		cv.put(KEY_PRIVACY_LEVEL,privacy);
		cv.put(KEY_IMAGE1,byteArray);
		try {
			ourDatabase.insert(TABLE_ADD_PLACE, null,cv);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}*/

	public Cursor getViewResults() {
		// TODO Auto-generated method stub
		
		String[] column=new String[]{ KEY_USER_ID};
		Cursor c1=ourDatabase.query(TABLE_USER_REGISTRATION, column,KEY_USER_NAME+"='"+ModelLocator.USER_NAME+"'", null, null, null, null);
		c1.moveToNext();
		userid = c1.getInt(c1.getColumnIndex(KEY_USER_ID));
		
		String[] column1=new String[]{KEY_PLACE_ID,KEY_PLACE_NAME,KEY_AREA,KEY_CATEGORY,KEY_ADDRESS, KEY_PHONE_NOS,KEY_DESCRIPTION,KEY_IMAGE1,KEY_PRIVACY_LEVEL};
		Cursor c=ourDatabase.query(TABLE_ADD_PLACE, column1,KEY_USER_ID+"='"+userid+"'", null, null, null, null);
		
		return c;
	}

	public Cursor getPlaceDetails() {
		// TODO Auto-generated method stub
		
		
		String[] col2=new String[]{KEY_PLACE_NAME,KEY_AREA,KEY_CATEGORY,KEY_ADDRESS, KEY_PHONE_NOS,KEY_DESCRIPTION,KEY_IMAGE1,KEY_PRIVACY_LEVEL};
		Cursor c2=ourDatabase.query(TABLE_ADD_PLACE, col2,KEY_PLACE_ID+"="+ModelLocator.P_ID, null, null, null, null);
		
		
		
		
		return c2;
	}

	public void updatePlace(String placename, String area, String category,
			String address, String phoneno,
			String description, byte[] byteArray, String privacy) {
		// TODO Auto-generated method stub
		String[] column=new String[]{ KEY_USER_ID};
		Cursor c1=ourDatabase.query(TABLE_USER_REGISTRATION, column,KEY_USER_NAME+"='"+ModelLocator.USER_NAME+"'", null, null, null, null);
		c1.moveToNext();
		userid = c1.getInt(c1.getColumnIndex(KEY_USER_ID));
		
      ContentValues cv =new ContentValues();
		
		cv.put(KEY_PLACE_NAME, placename);
		cv.put(KEY_AREA, area);
		cv.put(KEY_CATEGORY, category);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_PHONE_NOS,phoneno);
		cv.put(KEY_DESCRIPTION, description);
		cv.put(KEY_USER_ID, userid);
		cv.put(KEY_PRIVACY_LEVEL,privacy);
		cv.put(KEY_IMAGE1,byteArray);
		//ContentValues values[]={placename,area,category,landmark,phoneno,landlineno,description,privacy};
		ourDatabase.update(TABLE_ADD_PLACE, cv,KEY_PLACE_ID+"="+ModelLocator.P_ID,null);
		
		
		
		
		
	}

	public void deletePlace() {
		// TODO Auto-generated method stub
		ourDatabase.delete(TABLE_ADD_PLACE,KEY_PLACE_ID+"="+ModelLocator.P_ID, null);
	}

	public List<String> getAreas() {
		// TODO Auto-generated method stub
		List<String> areaLabels = new ArrayList<String>();
		String[] columns=new String[]{KEY_AREA};
		Cursor c2;
		if(flag)
		{
			
		//	c2=ourDatabase.query(true, TABLE_ADD_PLACE, columns,KEY_PRIVACY_LEVEL+"='public'",null, null,null,null,null);
		 c2=ourDatabase.query(true, TABLE_ADD_PLACE, columns,KEY_PRIVACY_LEVEL+"='public' AND "+  KEY_CATEGORY +"='"+ selection +"'", null,null,null,null,null);
		}
		 else {
			 c2=ourDatabase.query(true, TABLE_ADD_PLACE, columns,KEY_PRIVACY_LEVEL+"='public'",null, null,null,null,null);
		 }
		 if (c2.moveToFirst()) 
        {	
			 areaLabels.add("Select Area");
            do 
            {
                areaLabels.add(c2.getString(c2.getColumnIndex(KEY_AREA)));
            } 
            while (c2.moveToNext());
        }
		return areaLabels;
	}	
	
	
	public void setCategory(String selection) {
		this.selection = selection;
		 flag = true;
	}
	
	

	public List<String> getCategories() {
		// TODO Auto-generated method stub
		List<String> categoryLabels = new ArrayList<String>();
		String[] columns=new String[]{KEY_CATEGORY};
		Cursor c2=ourDatabase.query(true, TABLE_ADD_PLACE, columns,KEY_PRIVACY_LEVEL+"='public'", null, null,null,null,null);
		if (c2.moveToFirst()) 
        {
        	categoryLabels.add("Select Category");
        	
            do 
            {
                categoryLabels.add(c2.getString(c2.getColumnIndex(KEY_CATEGORY)));
            } 
            while (c2.moveToNext());
        }
		
		return categoryLabels;
	}

	public Cursor getSearchResults() {
		// TODO Auto-generated method stub
		byte[] byteArray=null;
		Bitmap bitmap;
		String[] column1=new String[]{KEY_PLACE_ID,KEY_PLACE_NAME,KEY_AREA,KEY_CATEGORY,KEY_ADDRESS, KEY_PHONE_NOS,KEY_PRIVACY_LEVEL,KEY_DESCRIPTION};
		//Cursor c=ourDatabase.query(TABLE_ADD_PLACE, column1+KEY_IMAGE1,KEY_PRIVACY_LEVEL+"='public' AND " +KEY_AREA+"='"+ModelLocator.SPIN_AREA+"' AND "+KEY_CATEGORY+"='"+ModelLocator.SPIN_CATEGORY+"'", null, null, null, null);
		//String sql="SELECT rowid,PLACE_NAME,AREA,CATEGORY,LANDMARK,PHONE_NO,LANDLINE_NO,PRIVACY_LEVEL FROM "+TABLE_ADD_PLACE;
		//Cursor c=ourDatabase.rawQuery(sql, null);
		Cursor c=ourDatabase.query(TABLE_ADD_PLACE, null,KEY_PRIVACY_LEVEL+"='public' AND " +KEY_AREA+"='"+ModelLocator.SPIN_AREA+"' AND "+KEY_CATEGORY+"='"+ModelLocator.SPIN_CATEGORY+"'", null, null, null, null);
		return c;
	}

	

	public Cursor getCategoryNArea() {
		// TODO Auto-generated method stub
		String[] column1=new String[]{KEY_CATEGORY,KEY_AREA};
		Cursor c=ourDatabase.query(TABLE_ADD_PLACE, column1,null, null, null, null, null);
		return c;
	}

	/*public Cursor getCabDetails() {
		// TODO Auto-generated method stub
		
		
		String[] column1=new String[]{KEY_CAB_NAME,KEY_CAB_AREA,KEY_CAB_ADDRESS,KEY_PHONE1};
		Cursor c=ourDatabase.query(TABLE_CAB_DETAILS, column1,null, null, null, null, null);
		return c;
	}
*/
	public List getAutoAreas() {
		// TODO Auto-generated method stub
		List<String> areaLabels = new ArrayList<String>();
		String[] columns=new String[]{KEY_AREA};
		Cursor c2=ourDatabase.query(true, TABLE_ADD_PLACE, columns,KEY_PRIVACY_LEVEL+"='public'", null, null,null,null,null);
		if (c2.moveToFirst()) 
        {
            do 
            {
                areaLabels.add(c2.getString(c2.getColumnIndex(KEY_AREA)));
            } 
            while (c2.moveToNext());
        }
		return areaLabels;
	}		 
			


	public List<String> getAutoCategories() {
		// TODO Auto-generated method stub
		List<String> categoryLabels = new ArrayList<String>();
		String[] columns=new String[]{KEY_CATEGORY};
		Cursor c2=ourDatabase.query(true, TABLE_ADD_PLACE, columns,KEY_PRIVACY_LEVEL+"='public'", null, null,null,null,null);
		if (c2.moveToFirst()) 
        {
            do 
            {
                categoryLabels.add(c2.getString(c2.getColumnIndex(KEY_CATEGORY)));
            } 
            while (c2.moveToNext());
        }
		
		return categoryLabels;
	}
	
	
	
	
	
	
	
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}



	public void addPlace(String placename, String area, String category,
			String address, String phoneno, String description,
			byte[] byteArray, String privacy) {
		// TODO Auto-generated method stub
		String[] column=new String[]{ KEY_USER_ID};
		
		Cursor c=ourDatabase.query(TABLE_USER_REGISTRATION, column,KEY_USER_NAME+"='"+ModelLocator.USER_NAME+"'", null, null, null, null);
		 // Cursor c=ourDatabase.execSQL("SELECT USER_ID FROM USER_REGISTRATION WHERE USER_NAME='"+usern+"'");
		//for(c.moveToFirst();c.isAfterLast(); c.moveToNext())
		 //  {
				c.moveToNext();
			   userid = c.getInt(c.getColumnIndex(KEY_USER_ID));
		  // }
		ContentValues cv =new ContentValues();
		
		cv.put(KEY_PLACE_NAME, placename);
		cv.put(KEY_AREA, area);
		cv.put(KEY_CATEGORY, category);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_PHONE_NOS,phoneno);
		cv.put(KEY_DESCRIPTION, description);
		cv.put(KEY_USER_ID, userid);
		cv.put(KEY_IMAGE1,byteArray);
		cv.put(KEY_PRIVACY_LEVEL,privacy);
		try {
			ourDatabase.insert(TABLE_ADD_PLACE, null,cv);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		

	}



	public Cursor getCabResults() {
		// TODO Auto-generated method stub
		String[] column1=new String[]{KEY_CAB_NAME,KEY_CAB_AREA,KEY_CAB_ADDRESS,KEY_PHONE1,KEY_CAB_IMAGE};
		Cursor c=ourDatabase.query(TABLE_CAB_DETAILS, column1,null, null, null, null, null);
		return c;
	}

	public Cursor getSelectedItemID() {
		// TODO Auto-generated method stub
		String[] column1=new String[]{KEY_PLACE_ID};
		Cursor c=ourDatabase.query(TABLE_ADD_PLACE, column1,KEY_PLACE_NAME+"='"+ModelLocator.PLACE+"' AND "+KEY_AREA+"='"+ModelLocator.AREA+"'", null, null, null, null);	
		
		return c;
	}

	

}
