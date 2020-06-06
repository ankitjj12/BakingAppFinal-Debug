package com.example.bakingappfinal;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class IngredientListService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static final String ACTION_UPDATE_BAKE_WIDGET = "com.example.bakingappfinal.action.update_bake_widget";
    public static ArrayList<String> ingredient_list = new ArrayList<>();
    String recipe_name;

    public static ArrayList<String> getIngredient_list(){
        return ingredient_list;
    }

    public IngredientListService() {
        super("IngredientListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            final String intentAction = intent.getAction();
            if(ACTION_UPDATE_BAKE_WIDGET.equals(intentAction)){
                updateWidgetIngredient();
            }
        }
    }

    public static void startActionUpdateBakeWidget(Context context){
        Intent intent = new Intent(context, IngredientListService.class);
        intent.setAction(ACTION_UPDATE_BAKE_WIDGET);
        context.startService(intent);
    }

    public void updateWidgetIngredient(){


        /*SharedPreferences sharedPreferences = getSharedPreferences(IngredientQuantityActivity.SHARED_PREFERNCE, Context.MODE_PRIVATE);
        recipe_name = sharedPreferences.getString(IngredientQuantityActivity.RECIPE_NAME, "default");
        if(recipe_name != "default") {
            ingredient_list.add(recipe_name);
        }*/

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetId = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.listview_ingredient);
        BakingAppWidget.updateBakingAppWidget(this, appWidgetManager, ingredient_list, appWidgetId);
    }


}
