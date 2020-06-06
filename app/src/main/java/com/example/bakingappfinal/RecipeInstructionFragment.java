package com.example.bakingappfinal;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeInstructionFragment extends Fragment {

    public SimpleExoPlayerView mSimpleExoPlayerView;
    public SimpleExoPlayer mSimpleExoPlayer;
    public ArrayList<String> urlStringArrayFrag;
    public int videoUrlPosition;
    private Button mButtonPrev;
    private Button mButtonNext;
    private TextView textViewDescription;
    private String recipeDescription;
    ArrayList<String> recipeDescriptionArray = new ArrayList<>();
    String urlString = "";

    public RecipeInstructionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_instruction, container, false);

        mSimpleExoPlayerView = rootView.findViewById(R.id.simple_exoplayerView);
        textViewDescription = rootView.findViewById(R.id.fragment_text_recipe_description_ins);

        urlString = urlStringArrayFrag.get(videoUrlPosition);

        recipeDescription = recipeDescriptionArray.get(videoUrlPosition);
        textViewDescription.setText(recipeDescription);
        //stepScreen(videoUrlPosition);

        if(urlString != null) {
            initializePlayer(Uri.parse(urlString));
        } else {
            mSimpleExoPlayerView.setVisibility(View.INVISIBLE);
        }

        mButtonPrev = getActivity().findViewById(R.id.previous);
        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoUrlPosition = videoUrlPosition - 1 ;
                if(videoUrlPosition==urlStringArrayFrag.size()-2){
                    mButtonNext.setVisibility(View.VISIBLE);
                }
                if(videoUrlPosition==0){
                    mButtonPrev.setVisibility(View.INVISIBLE);
                } else {
                    mButtonPrev.setVisibility(View.VISIBLE);
                }
                stepScreen(videoUrlPosition);
            }
        });

        mButtonNext = getActivity().findViewById(R.id.next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoUrlPosition = videoUrlPosition + 1;
                if(videoUrlPosition==1){
                    mButtonPrev.setVisibility(View.VISIBLE);
                }
                if(videoUrlPosition == urlStringArrayFrag.size()-1){
                    mButtonNext.setVisibility(View.INVISIBLE);
                } else {
                    mButtonNext.setVisibility(View.VISIBLE);
                }
                stepScreen(videoUrlPosition);
            }
        });




        return rootView;
    }

    public void stepScreen(int videoUrlPosition_onClick){
        recipeDescription = recipeDescriptionArray.get(videoUrlPosition_onClick);
        textViewDescription.setText(recipeDescription);

        urlString = urlStringArrayFrag.get(videoUrlPosition_onClick);
       // Toast.makeText(getContext(), urlString + "Video Position", Toast.LENGTH_LONG).show();
        releasePlayer();
        initializePlayer(Uri.parse(urlString));
    }

    public void initializePlayer(Uri mediaUri){
        if(mSimpleExoPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mSimpleExoPlayerView.setPlayer(mSimpleExoPlayer);

            //Setting listner
            // Add Media
            String userAgent = Util.getUserAgent(getContext(), "RecipeVideo");

            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory
                    (getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mSimpleExoPlayer.prepare(mediaSource);
            mSimpleExoPlayer.setPlayWhenReady(true);

        }
    }

    public void releasePlayer (){
        mSimpleExoPlayer.stop();
        mSimpleExoPlayer.release();
        mSimpleExoPlayer = null;
    }

    public void setURlPostion(int videoPosition){
        videoUrlPosition = videoPosition-1;
    }

    public void setUrlStringArray(ArrayList<String> urlStringArray){
        urlStringArrayFrag = urlStringArray;
    }

    public void setDescriptionArray(ArrayList<String> urlStringArray){
        recipeDescriptionArray = urlStringArray;
    }


}
