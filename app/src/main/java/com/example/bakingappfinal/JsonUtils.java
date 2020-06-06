package com.example.bakingappfinal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String INGREDIENTS ="ingredients";
    public static final String QUANTITY_I ="quantity";
    public static final String MEASURE_I ="measure";
    public static final String INGREDIENTS_I ="ingredient";

    public static final String STEPS ="steps";
    public static final String SHORTDECRIPTION ="shortDescription";
    public static final String DESCRIPTION ="description";
    public static final String VIDEOURL ="videoURL";
    public static final String THUMBNAILURL ="thumbnailURL";




    public String name_recipe;


    public static String getJsonString (Context context, String filename){
        String json_string = null;
        String file_name_json = filename;


        try {
            InputStream is = context.getAssets().open(file_name_json);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json_string = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json_string;

    }

    public static CardViewDishes getRecipeDetails(String jsonString){
        String name_recipe;
        ArrayList<String> ingredients = new ArrayList<>();
        CardViewDishes cardViewDishes_Json = new CardViewDishes();
        ArrayList<String> recipe_name_array = new ArrayList<>(); //Collect all recipe_name


        //For all recipes
        ArrayList<ArrayList<String>> quantity_array_all = new ArrayList<>();
        ArrayList<ArrayList<String>> measure_array_all = new ArrayList<>();
        ArrayList<ArrayList<String>> ingredient_array_all = new ArrayList<>();
        //For all steps

        ArrayList<ArrayList<String>> shortDescription_array_all = new ArrayList<>();
        ArrayList<ArrayList<String>> description_array_all = new ArrayList<>();
        ArrayList<ArrayList<String>> videoURL_array_all = new ArrayList<>();
        ArrayList<ArrayList<String>> thumbnailURL_array_all = new ArrayList<>();


        try {
            JSONArray recipeDb = new JSONArray(jsonString);
            JSONObject recipeDb_obj = new JSONObject();

            JSONArray ingredientsArray = new JSONArray();

            for (int i =0 ; i< recipeDb.length(); i++){
                recipeDb_obj = recipeDb.getJSONObject(i);
                name_recipe = recipeDb_obj.getString(NAME);
                recipe_name_array.add(name_recipe);

                //Get Ingredients list of each recipe
                JSONArray ingredients_db = recipeDb_obj.getJSONArray(INGREDIENTS);
                JSONObject ingredient_obj = new JSONObject();

                //For one recipe
                ArrayList<String> quantity_array = new ArrayList<>(); //collect quantity of ingredient of recipe
                ArrayList<String> measure_array = new ArrayList<>(); //collect measure of ingredient
                ArrayList<String> ingredient_array = new ArrayList<>(); //collect name of ingredient

                for(int j = 0; j < ingredients_db.length(); j++){

                    ingredient_obj = ingredients_db.getJSONObject(j);
                    quantity_array.add(ingredient_obj.getString(QUANTITY_I));
                    measure_array.add(ingredient_obj.getString(MEASURE_I));
                    ingredient_array.add(ingredient_obj.getString(INGREDIENTS_I));
                }

                quantity_array_all.add(quantity_array);
                measure_array_all.add(measure_array);
                ingredient_array_all.add(ingredient_array);


                //Get STEPS: shortDescription, description, videoURL, thumbnailURL

                JSONArray steps_db = recipeDb_obj.getJSONArray(STEPS);
                JSONObject steps_obj = new JSONObject();

                //For one step
                ArrayList<String> shortDescription_array = new ArrayList<>();
                ArrayList<String> description_array = new ArrayList<>();
                ArrayList<String> videoURL_array = new ArrayList<>();
                ArrayList<String> thumbnailURL_array = new ArrayList<>();

                for(int k =0; k< steps_db.length(); k++){
                    steps_obj = steps_db.getJSONObject(k);
                    shortDescription_array.add(steps_obj.getString(SHORTDECRIPTION));
                    description_array.add(steps_obj.getString(DESCRIPTION));
                    videoURL_array.add(steps_obj.getString(VIDEOURL));
                    thumbnailURL_array.add(steps_obj.getString(THUMBNAILURL));
                }

                shortDescription_array_all.add(shortDescription_array);
                description_array_all.add(description_array);
                videoURL_array_all.add(videoURL_array);
                thumbnailURL_array_all.add(thumbnailURL_array);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        cardViewDishes_Json.setRecipe_name_array(recipe_name_array);
        cardViewDishes_Json.setIngredient_array_all(ingredient_array_all);
        cardViewDishes_Json.setMeasure_array_all(measure_array_all);
        cardViewDishes_Json.setQuantity_array_all(quantity_array_all);

        cardViewDishes_Json.setShortDescription_array_all(shortDescription_array_all);
        cardViewDishes_Json.setDescription_array_all(description_array_all);
        cardViewDishes_Json.setVideoURL_array_all(videoURL_array_all);
        cardViewDishes_Json.setThumbnailURL_array_all(thumbnailURL_array_all);

        return cardViewDishes_Json;

    }

}

