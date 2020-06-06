package com.example.bakingappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DishesNameAdapter.OnClickPassRValue, RecipeListFragment.OnFragmentInteractionListener{
    int postition_from_masterList;
    public String recipe_name_from_recy;
    public int position_clicked_master_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onFragmentInteraction(int position_masterlist) {
        postition_from_masterList = position_masterlist;
    }

    @Override
    public void onItemClickRValues(String recipe_name, int position_clicked) {
        recipe_name_from_recy = recipe_name;
        position_clicked_master_list = position_clicked;

        Intent intent_position = new Intent(this, RecipeStepListActivity.class);
        intent_position.putExtra("position_clicked", position_clicked);
        startActivity(intent_position);

    }
}
