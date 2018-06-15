package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView alsoKnownAsTv;
    TextView ingredientTv;
    TextView placeOfOriginTv;
    TextView descriptionTv;
    Sandwich sandwich;
    LinearLayout also_known_linearLayout;
    LinearLayout ingredients_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       ImageView ingredientsIv =(ImageView)  findViewById(R.id.image_iv);
        alsoKnownAsTv=(TextView)findViewById(R.id.also_known_tv);
        ingredientTv=(TextView)findViewById(R.id.ingredients_tv);
        also_known_linearLayout= (LinearLayout) findViewById(R.id.also_known_layout);
        ingredients_linearLayout=(LinearLayout)findViewById(R.id.ingredients_layout);
        placeOfOriginTv=(TextView)findViewById(R.id.origin_tv);
        descriptionTv=(TextView)findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich=new Sandwich();
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI();
        Picasso.get()
                .load(sandwich.getImage())
                .placeholder(R.drawable.holder)
                .error(R.drawable.error)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        descriptionTv.setText(sandwich.getDescription());
        if(sandwich.getPlaceOfOrigin().isEmpty()){
            placeOfOriginTv.setText("NO data found");
        }else {
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        }
        if(sandwich.getIngredients().isEmpty()){
            ingredients_linearLayout.setVisibility(View.GONE);
            } else {
            for (int c = 0; c < sandwich.getIngredients().size(); c++) {
                ingredientTv.setText(TextUtils.join(" , ",sandwich.getIngredients()));
            }
        }
            if(sandwich.getAlsoKnownAs().isEmpty()){
                also_known_linearLayout.setVisibility(View.GONE);
              }else {
                for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
                    alsoKnownAsTv.setText(TextUtils.join(" , ", sandwich.getAlsoKnownAs()));
                }
            }
    }
}
