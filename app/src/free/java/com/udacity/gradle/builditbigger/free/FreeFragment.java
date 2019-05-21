package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.androidlibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.R;

import static com.example.javalibrary.Joke.getJoke;
import com.google.android.gms.ads.InterstitialAd;

/**
 * A placeholder free_fragment containing a simple view.
 */
public class FreeFragment extends Fragment {
    String joke;
    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;

    public FreeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.free_fragment, container, false);
        mProgressBar =  root.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);

        //Banner:
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        //Interstitial Ads:
        //https://developers.google.com/admob/android/interstitial
        Context context = this.getContext();
        MobileAds.initialize(context, getString(R.string.app_ad_id));
        mInterstitialAd =  new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed
                // due to the user tapping on the close icon or using the back button.
                mProgressBar.setVisibility(View.VISIBLE);
                tellJoke();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        // Set onClickListener for the button
        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    mProgressBar.setVisibility(View.VISIBLE);
                    tellJoke();}
            }
        });

        return root;
    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(this);
    }

    public void displayJoke() {
        Context context = getActivity();
        if (context != null) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, joke);
            startActivity(intent);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
