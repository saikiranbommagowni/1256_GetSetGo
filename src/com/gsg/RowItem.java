package com.gsg;

import android.graphics.Bitmap;

public class RowItem {
    private Bitmap imageId;
    private String placeName;
    private String category;
    private String area;
    private String address;
    private String phoneno;
    private String description;
 
    public RowItem(String placeName, String category, String area, String address, String phoneno, String description, Bitmap bitmap ) {
        this.imageId = bitmap;
        this.placeName=placeName;
        this.category=category;
        this.area=area;
        this.address=address;
        this.phoneno=phoneno;
        this.description=description;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
   
    public String getPhoneno() {
        return phoneno;
    }
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
   
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Bitmap getImageId() {
        return imageId;
    }
    public void setImageId(Bitmap imageId) {
        this.imageId = imageId;
    }
}