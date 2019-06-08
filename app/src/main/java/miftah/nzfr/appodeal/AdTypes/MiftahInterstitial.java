package miftah.nzfr.appodeal.AdTypes;

import android.app.Activity;
import android.util.Log;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;

public abstract class MiftahInterstitial {
    private Activity activity;
    private String app_key;
    private String TAG = "Miftah Customized Appodeal library";

    public MiftahInterstitial(Activity activity, String app_key) {
        this.activity = activity;
        this.app_key = app_key;
        Appodeal.initialize(activity, app_key, Appodeal.INTERSTITIAL, true);
    }
    public void createInterstitial() {
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean isPrecache) {
                Log.d(TAG, "onInterstitialLoaded: is precache : " + isPrecache);
                Appodeal.show(activity, Appodeal.INTERSTITIAL);
                interLoaded(isPrecache);
            }

            @Override
            public void onInterstitialFailedToLoad() {
                Log.d(TAG, "inter failed to load ... ");
                interFailedToLoad();
            }

            @Override
            public void onInterstitialShown() {
                Log.d(TAG, "inter has been shown ... ");
                interShown();
            }

            @Override
            public void onInterstitialClicked() {
                Log.d(TAG, "inter has been clicked ... ");
                interClicked();
            }

            @Override
            public void onInterstitialClosed() {
                Log.d(TAG, "inter has been closed ... ");
                interClosed();
            }

            @Override
            public void onInterstitialExpired() {
                Log.d(TAG, "inter has been expired ... ");
                interExpired();
            }
        });
    }
    public abstract void interLoaded(boolean isPrecache);
    public abstract void interFailedToLoad();
    public abstract void interShown();
    public abstract void interClicked();
    public abstract void interClosed();
    public abstract void interExpired();


}
