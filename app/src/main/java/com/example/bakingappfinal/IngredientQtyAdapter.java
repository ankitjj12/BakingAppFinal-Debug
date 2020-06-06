package com.example.bakingappfinal;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientQtyAdapter extends RecyclerView.Adapter<IngredientQtyAdapter.IngredientQtyAdapterViewHolder> {

    public ArrayList<String> setQuantityIngredientList;

    public IngredientQtyAdapter(Context context, ArrayList<String> setQuantityIngredient) {
        setQuantityIngredientList = setQuantityIngredient;
    }

    @NonNull
    @Override
    public IngredientQtyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View myView = layoutInflater.inflate(R.layout.step_list_display, parent, false);
        return new IngredientQtyAdapterViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientQtyAdapterViewHolder holder, int position) {
        //holder.ingredientQtyList.setBackgroundColor();
        holder.ingredientQtyList.setText(setQuantityIngredientList.get(position));
    }

    @Override
    public int getItemCount() {
        if(setQuantityIngredientList.size()!=0){
            return setQuantityIngredientList.size();
        } else {
            return 0;
        }

    }

    public class IngredientQtyAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientQtyList;

        public IngredientQtyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientQtyList = itemView.findViewById(R.id.step_list_text);
        }
    }
}
