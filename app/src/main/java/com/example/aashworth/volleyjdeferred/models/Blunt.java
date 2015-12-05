package com.example.aashworth.volleyjdeferred.models;

import java.util.ArrayList;

/**
 * Created by aashworth on 12/4/15.
 */
public class Blunt {

    private String mMessage;
    private int mCode;
    private ArrayList<String> mImages;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void setImages(ArrayList<String> images) {
        mImages = images;
    }
}
