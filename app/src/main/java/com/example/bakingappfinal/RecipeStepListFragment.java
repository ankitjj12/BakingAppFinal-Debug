package com.example.bakingappfinal;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepListFragment extends Fragment {

    private RecyclerView recyclerViewStepList;
    private LinearLayoutManager linearLayoutStepList;
    private ArrayList<String> stepList_array_fragment;
    private ArrayList<String> setQuantityIngredient;
    private int recipe_position_clicked_fragment;

    public RecipeStepListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_recipe_step_list, container, false);

        recyclerViewStepList = rootView.findViewById(R.id.recipeSteps_list_recycler_view);
        linearLayoutStepList = new LinearLayoutManager(getContext());
        recyclerViewStepList.setLayoutManager(linearLayoutStepList);


        if(setQuantityIngredient!=null){
            IngredientQtyAdapter ingredientQtyAdapter = new IngredientQtyAdapter(getContext(), setQuantityIngredient);
            recyclerViewStepList.setAdapter(ingredientQtyAdapter);
        }

        if(stepList_array_fragment!=null) {
            StepListAdapter stepListAdapter = new StepListAdapter(getContext(), stepList_array_fragment, recipe_position_clicked_fragment);
            recyclerViewStepList.setAdapter(stepListAdapter);
        }



        return rootView;
    }

    public void  setStepList(ArrayList<String> getStepListFromActivity, int recipe_position_clicked){
        stepList_array_fragment = getStepListFromActivity;
        recipe_position_clicked_fragment = recipe_position_clicked;

    }

    public void  setquantityIngredient(ArrayList<String> setQuantityIngredientfromActivity){
        setQuantityIngredient = setQuantityIngredientfromActivity;

    }

}
