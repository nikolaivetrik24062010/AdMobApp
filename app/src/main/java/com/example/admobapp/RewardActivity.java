package com.example.admobapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardActivity extends AppCompatActivity {

    private RewardedAd rewardedAd;
    private Button btnRewarded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reward);

        // Set up Rewarded Ad Button
        btnRewarded = findViewById(R.id.buttonRewarded);
        btnRewarded.setOnClickListener(v -> {
            if (rewardedAd != null) {
                rewardedAd.show(this, rewardItem -> {
                    // Handle the reward
                    Log.d(TAG, "User earned reward: " + rewardItem.getType() + " amount: " + rewardItem.getAmount());
                });
            } else {
                Log.d(TAG, "Rewarded Ad not loaded yet.");
            }
        });

        // Load Rewarded Ad
        loadRewardedAd();
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, "Rewarded Ad Failed to Load: " + loadAdError.getMessage());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        Log.d(TAG, "Rewarded Ad Loaded.");
                    }
                });
    }
}