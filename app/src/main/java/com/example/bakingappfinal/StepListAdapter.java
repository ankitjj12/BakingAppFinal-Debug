package com.example.bakingappfinal;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepListAdapterViewholder> {
    static final String INGREDIENTS = "Ingredients";
    ArrayList<String> stepList_fromFragment = new ArrayList<>();
    public int recipePosition;
    public static final String SHARED_PREFERNCE = "shared_pref";
    public static final String RECIPE_NAME = "recipe_name";
    public Context ctx_sl;
    String recipe_name_clicked;



    public StepListAdapter(@NonNull Context context, ArrayList<String> stepList, int recipePostionClicked) {
        stepList_fromFragment.add(INGREDIENTS);
        ctx_sl = context;
        stepList_fromFragment.addAll(stepList);
        recipePosition = recipePostionClicked;
        recipe_name_clicked = CardViewDishes.getRecipe_name().get(recipePosition);

    }



    @NonNull
    @Override
    public StepListAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View myviewStepList = layoutInflater.inflate(R.layout.step_list_display,parent, false);
        return new StepListAdapterViewholder(myviewStepList);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapterViewholder holder, final int position) {
        holder.stepListText.setText(stepList_fromFragment.get(position));

        holder.stepListText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"position" + position, Toast.LENGTH_LONG).show();
                if(position==0){
                    Intent intent = new Intent(ctx_sl, IngredientQuantityActivity.class);
                    intent.putExtra("recipe_position", recipePosition);
                    ctx_sl.startActivity(intent);
                } else{
                    Intent intentSteps = new Intent(ctx_sl, RecipeInstructionActivity.class);
                    intentSteps.putExtra("recipe_position", recipePosition);
                    intentSteps.putExtra("step_position", position);
                    intentSteps.putExtra("steps_size", stepList_fromFragment.size());
                    ctx_sl.startActivity(intentSteps);
                }

                Intent intent = new Intent(ctx_sl, BakingAppWidget.class);
                intent.setAction(BakingAppWidget.ACTION_UPDATE_WIDGET);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(ctx_sl);
                int[] appWidgetId = appWidgetManager.getAppWidgetIds(new ComponentName(ctx_sl, BakingAppWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
                ctx_sl.sendBroadcast(intent);


                SharedPreferences sharedPreferences = ctx_sl.getSharedPreferences(SHARED_PREFERNCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(RECIPE_NAME, recipe_name_clicked);
                editor.apply();


            }
        });
    }

    @Override
    public int getItemCount() {
        if(stepList_fromFragment.size() != 0) {
            return (stepList_fromFragment.size());
        } else {
            return 0;
        }
    }

    public class StepListAdapterViewholder extends RecyclerView.ViewHolder {
        TextView stepListText;
        public StepListAdapterViewholder(@NonNull View itemView) {
            super(itemView);
            stepListText = itemView.findViewById(R.id.step_list_text);

            /*SharedPreferences sharedPreferences = ctx_sl.getSharedPreferences(SHARED_PREFERNCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();*/


        }
    }



}
