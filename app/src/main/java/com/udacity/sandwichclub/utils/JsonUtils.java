package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich= new Sandwich();
        JSONObject sandwichDetail = new JSONObject(json);
        JSONObject name=sandwichDetail.getJSONObject("name");
        String sandwichMainName = name.getString("mainName");

        String placeOfOrigin = sandwichDetail.getString("placeOfOrigin");
        String description=sandwichDetail.getString("description");
        JSONArray sandwichIngredients=sandwichDetail.getJSONArray("ingredients");
        ArrayList<String> currentSandwichIngredients=new ArrayList<>();
        for(int c=0;c<sandwichIngredients.length();c++){
            currentSandwichIngredients.add(sandwichIngredients.getString(c));
        }
        JSONArray alsoKnownAs=name.getJSONArray("alsoKnownAs");
        ArrayList<String> currentAlsoKnownAs=new ArrayList();
        for(int i=0;i<alsoKnownAs.length();i++){
            currentAlsoKnownAs.add(alsoKnownAs.getString(i));
        }
        String image=sandwichDetail.getString("image");
        sandwich=new Sandwich(sandwichMainName,currentAlsoKnownAs,placeOfOrigin,description,image,currentSandwichIngredients);
        return sandwich;
    }
}
