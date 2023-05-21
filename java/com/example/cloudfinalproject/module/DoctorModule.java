package com.example.cloudfinalproject.module;

public class DoctorModule {
    String name;
    String image;
    String id;

    public DoctorModule(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
