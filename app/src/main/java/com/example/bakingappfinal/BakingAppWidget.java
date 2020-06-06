package com.example.bakingappfinal;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    public static final String ACTION_UPDATE_WIDGET = "com.example.bakingappfinal.action.update_bake_widget";
    public static String recipe_name_shared_pref= "";
    ArrayList ingredients_list = new ArrayList();


    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        RemoteViews views;
        if(recipe_name_shared_pref == null){
            views = bakingImageOnly(context);
        } else {
            views = getListView(context);

        }
        views.setTextViewText(R.id.text_list, recipe_name_shared_pref);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static RemoteViews bakingImageOnly(Context context){

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntentImageView = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        views.setOnClickPendingIntent(R.layout.baking_app_widget, pendingIntentImageView);

        return views;
    }

    private static RemoteViews getListView(Context context){

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.listview_widget);

        Intent intent = new Intent(context, ListViewWidgetService.class);
        views.setRemoteAdapter(R.id.listview_ingredient, intent);

       /* Intent openAppIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntentListView = PendingIntent.getActivity(context, 0, openAppIntent, 0);

        views.setOnClickPendingIntent(R.layout.listview_widget, pendingIntentListView); */
        views.setEmptyView(R.id.listview_ingredient, R.id.text_list);
        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        //IngredientListService.startActionUpdateBakeWidget(context);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }


    }

    public static void updateBakingAppWidget(Context context, AppWidgetManager appWidgetManager, ArrayList<String> wIngredientList,int[] appWidgetIds){
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, wIngredientList, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {



        if(intent.getAction().equals(BakingAppWidget.ACTION_UPDATE_WIDGET)){

            Bundle appExtra = intent.getExtras();
            AppWidgetManager appWidgetManager_BAW = AppWidgetManager.getInstance(context);

           // ComponentName componentName = new ComponentName(context, BakingAppWidget.class);

            int [] appWidgetIds = appExtra.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_ID);

            if(appWidgetIds!=null && appWidgetIds.length > 0){
                appWidgetManager_BAW.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.listview_ingredient);
                //BakingAppWidget.updateBakingAppWidget(context, appWidgetManager_BAW, appWidgetIds);
                SharedPreferences sharedPreferences = context.getSharedPreferences(StepListAdapter.SHARED_PREFERNCE, Context.MODE_PRIVATE);
                recipe_name_shared_pref = sharedPreferences.getString(StepListAdapter.RECIPE_NAME, "default");
                this.onUpdate(context, appWidgetManager_BAW, appWidgetIds);

            }

        }


        super.onReceive(context, intent);

    }
}

