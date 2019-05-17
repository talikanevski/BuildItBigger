package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.androidlibrary.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;

import static com.example.javalibrary.Joke.getJoke;


/**
 * A placeholder free_fragment containing a simple view.
 */
public class PaidFragment extends Fragment {
    String joke;

    public PaidFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.paid_fragment, container, false);
        Context context = this.getContext();
    // Set onClickListener for the button
        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {tellJoke();}
        });
        return root;
    }

    public void tellJoke() {new com.udacity.gradle.builditbigger.paid.EndpointsAsyncTask().execute(this);}

    public void displayJoke() {
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, joke);
            startActivity(intent);
        }
    }
}
