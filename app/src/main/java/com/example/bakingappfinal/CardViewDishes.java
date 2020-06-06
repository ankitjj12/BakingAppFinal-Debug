package com.example.bakingappfinal;

import java.util.ArrayList;

public class CardViewDishes {

    private static ArrayList<String> recipe_name_array;
    private static ArrayList<ArrayList<String>> quantity_array_all;
    private static ArrayList<ArrayList<String>> measure_array_all;
    private static ArrayList<ArrayList<String>> ingredient_array_all;

    //STEPS: shortDescription, description, videoURL, thumbnailURL

    private static ArrayList<ArrayList<String>> shortDescription_array_all;
    private static ArrayList<ArrayList<String>> description_array_all;
    private static ArrayList<ArrayList<String>> videoURL_array_all;
    private static ArrayList<ArrayList<String>> thumbnailURL_array_all;


    public CardViewDishes(){}

    public static ArrayList<ArrayList<String>> getShortDescription_array_all() {
        return shortDescription_array_all;
    }

    public static ArrayList<ArrayList<String>> getDescription_array_all() {
        return description_array_all;
    }

    public static ArrayList<ArrayList<String>> getVideoURL_array_all() {
        return videoURL_array_all;
    }

    public static ArrayList<ArrayList<String>> getThumbnailURL_array_all() {
        return thumbnailURL_array_all;
    }

    public void setShortDescription_array_all(ArrayList<ArrayList<String>> shortDescription_array_all) {
        this.shortDescription_array_all = shortDescription_array_all;
    }

    public void setDescription_array_all(ArrayList<ArrayList<String>> description_array_all) {
        this.description_array_all = description_array_all;
    }

    public void setVideoURL_array_all(ArrayList<ArrayList<String>> videoURL_array_all) {
        this.videoURL_array_all = videoURL_array_all;
    }

    public void setThumbnailURL_array_all(ArrayList<ArrayList<String>> thumbnailURL_array_all) {
        this.thumbnailURL_array_all = thumbnailURL_array_all;
    }

    public void setRecipe_name_array(ArrayList<String> recipe_name_array) {
        this.recipe_name_array = recipe_name_array;
    }

    public void setQuantity_array_all(ArrayList<ArrayList<String>> quantity_array_all) {
        this.quantity_array_all = quantity_array_all;
    }

    public void setMeasure_array_all(ArrayList<ArrayList<String>> measure_array_all) {
        this.measure_array_all = measure_array_all;
    }

    public void setIngredient_array_all(ArrayList<ArrayList<String>> ingredient_array_all) {
        this.ingredient_array_all = ingredient_array_all;
    }



    public static ArrayList<String> getRecipe_name() {
        return recipe_name_array;
    }

    public static ArrayList<ArrayList<String>> getQuantity_array_all() {
        return quantity_array_all;
    }

    public static ArrayList<ArrayList<String>> getMeasure_array_all() {
        return measure_array_all;
    }

    public static ArrayList<ArrayList<String>> getIngredient_array_all() {
        return ingredient_array_all;
    }

}
