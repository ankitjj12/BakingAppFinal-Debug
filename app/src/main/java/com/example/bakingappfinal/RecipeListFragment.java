package com.example.bakingappfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;
import java.util.ArrayList;


public class RecipeListFragment extends Fragment  {

    private OnFragmentInteractionListener mListener;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManagerRview;
    public static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public String json_string_ntw = "";
    public DishesNameAdapter dishesNameAdapter;


    public RecipeListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.recipe_list_recycler_view);

        mLayoutManagerRview = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManagerRview);
        new FetchRecipe().execute();

        //mListener.onFragmentInteraction(position_clicked_master_list);

        return rootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position_clicked);
    }

    public class FetchRecipe extends AsyncTask<String, Void, ArrayList<CardViewDishes>> {


        @Override
        protected ArrayList<CardViewDishes> doInBackground(String... strings) {

            ArrayList<CardViewDishes> cardViewDishes_obj_array = new ArrayList<>();



            URL recipe_url = NetworkUtils.buildUrl(RECIPE_URL);
            json_string_ntw = NetworkUtils.httpsResponse(recipe_url);
            CardViewDishes cardViewDishes = JsonUtils.getRecipeDetails(json_string_ntw);
            cardViewDishes_obj_array.add(cardViewDishes);

            return cardViewDishes_obj_array;
        }

        @Override
        protected void onPostExecute(ArrayList<CardViewDishes> cardViewDishes) {
            if(cardViewDishes!=null) {
                dishesNameAdapter = new DishesNameAdapter(cardViewDishes, getContext());
                mRecyclerView.setAdapter(dishesNameAdapter);
            } else {

            }
        }
    }
}
