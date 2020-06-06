package com.example.bakingappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.ArrayList;

public class RecipeStepListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_list);

        ArrayList<String> shortDescription_array = new ArrayList<>();
        int position = getIntent().getIntExtra("position_clicked", 0);
        shortDescription_array = CardViewDishes.getShortDescription_array_all().get(position);

        RecipeStepListFragment recipeListFrag = new RecipeStepListFragment();
        recipeListFrag.setStepList(shortDescription_array, position);



        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.stepListContainer, recipeListFrag).commit();



    }


}
