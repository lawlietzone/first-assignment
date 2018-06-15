package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_INGREDIENTS="ingredients";
    public static final String Key_DESCRIPTION="description";
    public static final String KEY_NAME="name";
    public static final String KEY_IMAGE="image";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich= new Sandwich();
        JSONObject sandwichDetail = new JSONObject(json);
        JSONObject name=sandwichDetail.getJSONObject(KEY_NAME);
        String sandwichMainName = name.getString(KEY_MAIN_NAME);

        String placeOfOrigin = sandwichDetail.getString(KEY_PLACE_OF_ORIGIN);
        String description=sandwichDetail.getString(Key_DESCRIPTION);
        JSONArray sandwichIngredients=sandwichDetail.getJSONArray(KEY_INGREDIENTS);
        ArrayList<String> currentSandwichIngredients=new ArrayList<>();
        for(int c=0;c<sandwichIngredients.length();c++){
            currentSandwichIngredients.add(sandwichIngredients.getString(c));
        }
        JSONArray alsoKnownAs=name.getJSONArray(KEY_ALSO_KNOW_AS);
        ArrayList<String> currentAlsoKnownAs=new ArrayList();
        for(int i=0;i<alsoKnownAs.length();i++){
            currentAlsoKnownAs.add(alsoKnownAs.getString(i));
        }
        String image=sandwichDetail.getString(KEY_IMAGE);
        sandwich=new Sandwich(sandwichMainName,currentAlsoKnownAs,placeOfOrigin,description,image,currentSandwichIngredients);
        return sandwich;
    }
}
