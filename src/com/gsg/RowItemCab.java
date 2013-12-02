package com.gsg;

import android.graphics.Bitmap;

public class RowItemCab {
    private Bitmap image;
    private String name;
    private String area;
    private String address;
    private String phone1;
 
   
    public RowItemCab(String cabName, String cabArea, String cabAddress,
			String cabPhoneno, Bitmap bitmap) {
		// TODO Auto-generated constructor stub
    	this.name=cabName;
    	this.area=cabArea;
        this.address=cabAddress;
        this.phone1=cabPhoneno;
        this.image=bitmap;
	}

	public String getName() {
        return name;
    }
    public void setPlaceName(String cabName) {
        this.name = cabName;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String cabArea) {
        this.area = cabArea;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String cabAddress) {
        this.address = cabAddress;
    }
   
    public String getPhone1() {
        return phone1;
    }
    public void setPhoneno(String phone1) {
        this.phone1 = phone1;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap cabImage) {
        this.image = cabImage;
    }
}
   