package com.satt294.passwdbuddy.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.satt294.passwdbuddy.R;
import com.satt294.passwdbuddy.exceptions.PBAuthenticationException;
import com.satt294.passwdbuddy.services.Authenticator;

public class LoginActivity2 extends AppCompatActivity {

    /**
     * The PIN Lock View
     */
    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    private Authenticator authenticator;

    /**
     * Listener function for the Pin Lock View
     */
    private PinLockListener mPinLockListener = new PinLockListener() {
        public String TAG = "[PIN LOCK VIEW]";

        @Override
        /**
         * This function is called only when the user has entered the PIN of required length
         */
        public void onComplete(String pin) {
            // Process further only if the user has entered 4-digit PIN
            // TODO: Make the pin length variable
            Log.i(TAG, "Info the user has entered the pin:" + pin);
            // Attempt to check and load the view

            try {
                authenticator.authenticate(pin);

                // Show the list view
                Intent listIntent = new Intent(LoginActivity2.this, ListActivity.class);
                startActivity(listIntent);

            } catch (PBAuthenticationException e) {
                Log.e(TAG, "onComplete: Exception while trying to authenticate the PIN", e);

                // Update the view with error to the user
                mPinLockView.resetPinLockView();
                TextView tvMessage = (TextView) findViewById(R.id.tv_message);
                // TODO: P3: Wiggle animation for the message
                tvMessage.setText("Wrong PIN. Try again.");
                tvMessage.setTextSize(22);
            }
        }

        @Override
        public void onEmpty() {
            Log.d(TAG, "Pin empty");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            // Do not do anything while the user is entering the PIN
        }
    };

    /**
     * Function checks whether the entered pin is the right pin to proceed further
     *
     * @return
     */
    private boolean verifyPIN(String pin) {
        // TODO: Check the PIN from DB
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable full screen with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // PIN Lock View
        setContentView(R.layout.activity_login2);

        // Get the PIN Lock view
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

        // Initilise the services
        initServices();
    }

    /**
     * Function to initialise all the services used by the activity
     */
    private void initServices() {
        // authentication service
        authenticator = Authenticator.getInstance();
    }
}
