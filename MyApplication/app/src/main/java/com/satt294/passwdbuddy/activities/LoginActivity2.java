package com.satt294.passwdbuddy.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.satt294.passwdbuddy.R;

public class LoginActivity2 extends AppCompatActivity {

    /**
     * The PIN Lock View
     */
    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    /**
     * Listener function for the Pin Lock View
     */
    private PinLockListener mPinLockListener = new PinLockListener() {
        public String TAG = "[PIN LOCK VIEW]";

        @Override
        public void onComplete(String pin) {
            Log.i(TAG, "Info the user has entered the pin:" + pin);
        }

        @Override
        public void onEmpty() {
            Log.d(TAG, "Pin empty");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable full screen with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // PIN Lock View
        setContentView(R.layout.activity_login2);

        // Get tje PIN Lock view
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);

        // Set the listener and indicator
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);

        // Set other properties of the PIN Lock view
        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));

        // enable animations for the indicator dots
        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);

    }
}
