package com.example.sabinmj.krafty_delivery_boy_app.model;

/**
 * Created by vinoth on 3/10/16.
 */


public class BlogPost  {

    private  String id;
    private String blogtitle;
    private String description;
    private  String status;
    private  String imageurl;
    public BlogPost() {
    }
    public  String  getId(){return  id;}
    public String getBlogtitle() {
        return blogtitle;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public String getImageurl() {
        return imageurl;
    }


    public BlogPost(String id,String fromLocation, String toLocation, String st,String imageurl) {
        this.id=id;
        this.blogtitle = fromLocation;
        this.description = toLocation;
        this.status = st;
        this.imageurl=imageurl;
    }





}