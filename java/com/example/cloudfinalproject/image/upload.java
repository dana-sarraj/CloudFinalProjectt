package com.example.cloudfinalproject.image;

public class upload {
    String name;
    String detail;
    String image_uri;

    public upload(){

    }
    public upload(String nname,String ddetail , String iimage_uri){
        if(nname.trim().equals("") && ddetail.trim().equals("")){
            nname="no name";
            ddetail="no details";
        }
        name = nname;
        detail=ddetail;
        image_uri=iimage_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
