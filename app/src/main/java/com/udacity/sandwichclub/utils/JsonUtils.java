package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich= new Sandwich();
        JSONObject sandwichDetail = new JSONObject(json);
        String sandwichName = sandwichDetail.getString("name");
        String sandwichMainName = sandwichDetail.getString("mainName");
        Log.d("adam",String.valueOf(sandwichMainName));
        String sandwichPlace = sandwichDetail.getString("placeOfOrigin");
        String sandwichDescription = sandwichDetail.getString("description");
        sandwich=new Sandwich(sandwichMainName,sandwichName,sandwichPlace,sandwichDescription);
        return sandwich;
    }
}
