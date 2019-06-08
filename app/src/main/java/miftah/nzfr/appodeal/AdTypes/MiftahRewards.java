package miftah.nzfr.appodeal.AdTypes;

import android.app.Activity;
import android.util.Log;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;

public abstract class MiftahRewards
{
    private Activity activity;
    private String app_key;
    private String TAG = "Miftah Customized Appodeal library";

    public MiftahRewards(Activity activity, String app_key) {
        this.activity = activity;
        this.app_key = app_key;
        Appodeal.initialize(activity, app_key, Appodeal.REWARDED_VIDEO, true);
    }

    public void createRewardVid() {
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            @Override
            public void onRewardedVideoLoaded(boolean isPrecache) {
                Log.d(TAG, "onRewardedVideoLoaded: is preCache : "+isPrecache);
                Appodeal.show(activity,Appodeal.REWARDED_VIDEO);
                rewardLoaded(isPrecache);
            }

            @Override
            public void onRewardedVideoFailedToLoad() {
                Log.d(TAG, "reward failed to load ... ");
                rewardFailedToLoad();
            }

            @Override
            public void onRewardedVideoShown() {
                Log.d(TAG, "reward has been shown ... ");
                rewardShown();
            }

            @Override
            public void onRewardedVideoFinished(double amount, String name) {
                Log.d(TAG, "onRewardedVideoFinished: amount = "+amount+" name = "+name);
                rewardFinished(amount,name);
            }

            @Override
            public void onRewardedVideoClosed(boolean finished) {
                Log.d(TAG, "reward video closed,finished = "+finished);
                rewardClosed(finished);
            }

            @Override
            public void onRewardedVideoExpired() {
                Log.d(TAG, "reward has been expired ... ");
                rewardExpired();
            }
        });
    }

    public abstract void rewardLoaded(boolean isPrecache);
    public abstract void rewardFailedToLoad();
    public abstract void rewardShown();
    public abstract void rewardFinished(double amount,String name);
    public abstract void rewardClosed(boolean finished);
    public abstract void rewardExpired();
}
