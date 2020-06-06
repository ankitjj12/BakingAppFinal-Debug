package com.example.bakingappfinal;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

public class ListViewWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        //return new ListeViewFactory(getApplicationContext(), intent);
        return new ListViewFactory(this.getApplicationContext(), intent);
    }

    class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context mcontext;
        private int mappWidgetId;
        ArrayList<String> ingredients = new ArrayList<>();


        public String recipe_name= "";



        public ListViewFactory(Context context, Intent intent) {
            mcontext = context;
            mappWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        @Override
        public void onCreate() {
           // ingredients = IngredientListService.getIngredient_list();


        }


        //Called when remoteViewsFactory is created and every time it's notified to update the data
        //called on start and when notifyAppWidgetViewDataChanged is called
        @Override
        public void onDataSetChanged() {
            //ingredients = IngredientListService.getIngredient_list();
            SharedPreferences sharedPreferences = getSharedPreferences(StepListAdapter.SHARED_PREFERNCE, Context.MODE_PRIVATE);

            recipe_name = sharedPreferences.getString(StepListAdapter.RECIPE_NAME, "default");
            ingredients.add(recipe_name);
            //ingredients.add("Two");
            //ingredients.add("Three");

           // ingredients = ingredients_temp;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if(ingredients != null) {
                return ingredients.size();
            } else {
                return 0;
            }
            //return 1;
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews remoteViews = new RemoteViews(mcontext.getPackageName(), R.layout.listview_widget);
            //remoteViews.setTextViewText(R.id.text_list, ingredients.get(position));
                remoteViews.setTextViewText(R.id.listview_ingredient, ingredients.get(position));


            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
