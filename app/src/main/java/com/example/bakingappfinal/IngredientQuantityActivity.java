package com.example.bakingappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.security.CryptoPrimitive;
import java.util.ArrayList;



public class IngredientQuantityActivity extends AppCompatActivity {


    public static final String INGREDIENT_LIST = "ingredient_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_quantity);

        int recipePosition = getIntent().getIntExtra("recipe_position", 0);

        ArrayList<String> quantity_string = new ArrayList<>();
        ArrayList<String> quantity = new ArrayList<>();
        ArrayList<String> measure = new ArrayList<>();
        ArrayList<String> ingredient = new ArrayList<>();
        String recipe_name = "";
        Context context = getApplicationContext();


        quantity = CardViewDishes.getQuantity_array_all().get(recipePosition);
        measure = CardViewDishes.getMeasure_array_all().get(recipePosition);
        ingredient = CardViewDishes.getIngredient_array_all().get(recipePosition);
        recipe_name = CardViewDishes.getRecipe_name().get(recipePosition);

        String temp_string;

        for(int i=0; i< quantity.size(); i++){
            temp_string = quantity.get(i) + " " +measure.get(i) + " " +  ingredient.get(i);
            quantity_string.add(temp_string);
        }

        /*SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERNCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RECIPE_NAME, recipe_name);
        editor.apply();*/


        RecipeStepListFragment recipeStepListFragmentIngredient = new RecipeStepListFragment();
        recipeStepListFragmentIngredient.setquantityIngredient(quantity_string);

        FragmentManager fragmentManagerQuantity = getSupportFragmentManager();
        fragmentManagerQuantity.beginTransaction().add(R.id.quantityIngredientContainer, recipeStepListFragmentIngredient).commit();

    }


}
