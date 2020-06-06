package com.example.bakingappfinal;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishesNameAdapter extends RecyclerView.Adapter<DishesNameAdapter.DishesNameAdapterViewHolder> {

    ArrayList<CardViewDishes> cardViewDishes = new ArrayList<>();
    CardViewDishes cardViewDishes_obj;
    ArrayList<String> recipe_list;
    Context ctx;
    OnClickPassRValue onClickPassValue_local;



    public DishesNameAdapter(ArrayList<CardViewDishes> cardViewDishes_list, Context context){
        cardViewDishes= cardViewDishes_list;
        ctx = context;
        recipe_list = new ArrayList<>();
        onClickPassValue_local = (OnClickPassRValue) ctx;
        for(int i=0; i < cardViewDishes.size(); i++) {
            cardViewDishes_obj = cardViewDishes.get(i);
        }

        recipe_list = cardViewDishes_obj.getRecipe_name();

    }

    public interface OnClickPassRValue{
        public void onItemClickRValues(String recipe_name, int postiton_clicked);
    }

    @NonNull
    @Override
    public DishesNameAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View myViewdishname = layoutInflater.inflate(R.layout.cardview_dishes_list,parent, false);
        return new DishesNameAdapterViewHolder(myViewdishname);
    }

    @Override
    public void onBindViewHolder(@NonNull DishesNameAdapterViewHolder holder, final int position) {
        holder.recipe_name.setText(recipe_list.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ite_clicked = recipe_list.get(position);
                Toast.makeText(v.getContext(), "Pressed" + ite_clicked, Toast.LENGTH_LONG ).show();
                onClickPassValue_local.onItemClickRValues(recipe_list.get(position), position);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(recipe_list.size()>0){
            return  recipe_list.size();
        } else {
            return 0;
        }

    }

    public class DishesNameAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView recipe_name;
        LinearLayout linearLayout;

        public DishesNameAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            linearLayout = itemView.findViewById(R.id.linearLayoutRecipeList);

        }
    }

}
