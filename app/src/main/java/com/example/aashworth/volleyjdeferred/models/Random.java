package com.example.aashworth.volleyjdeferred.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aashworth on 12/5/15.
 */
public class Random {

    @SerializedName("one")
    private String mOne;

    @SerializedName("key")
    private String mKey;

    public String getOne() {
        return mOne;
    }

    public void setOne(String one) {
        mOne = one;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }
}
