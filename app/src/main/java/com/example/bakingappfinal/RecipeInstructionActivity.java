package com.example.bakingappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RecipeInstructionActivity extends AppCompatActivity {

    ArrayList<String> video_url_list = new ArrayList<>();
    ArrayList<String> description_list = new ArrayList<>();
    Button mButtonPrevious;
    Button mButtonNext;

    private int step_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instruction);

        mButtonPrevious = findViewById(R.id.previous);
        mButtonNext = findViewById(R.id.next);

        int recipe_position = getIntent().getIntExtra("recipe_position", 0);
        step_position = getIntent().getIntExtra("step_position", 0);
        int steps_size = getIntent().getIntExtra("steps_size", 0);


        video_url_list = CardViewDishes.getVideoURL_array_all().get(recipe_position);
        description_list = CardViewDishes.getDescription_array_all().get(recipe_position);

        if(step_position == 1){
            mButtonPrevious.setVisibility(View.INVISIBLE);
        }

        RecipeInstructionFragment rif = new RecipeInstructionFragment();
        rif.setURlPostion(step_position);
        rif.setUrlStringArray(video_url_list);
        rif.setDescriptionArray(description_list);



        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.instruction_video_container, rif).commit();



    }
}
