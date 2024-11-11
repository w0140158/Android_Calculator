package com.example.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Build;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

// PatrickButton is a custom ImageButton that displays device version and screen information when clicked
public class PatrickButton extends androidx.appcompat.widget.AppCompatImageButton implements ImageButton.OnClickListener {

    // Constructor that initializes the button and sets the click listener
    public PatrickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("PatrickButton", "Patrick - Get my version Button Clicked!");
        getMyVersion(view); // Call method to display version info
    }

    // Method to retrieve and display device version and screen information
    public void getMyVersion(View view) {
        String versionNum = Integer.toString(Build.VERSION.SDK_INT);
        Boolean afterKitKat = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);

        // Get the parent view of the button
        View parent = (View) getParent();
        if (parent != null) {
            // Find the TextView to display the information
            TextView t = parent.findViewById(R.id.textView);

            if (t != null) {
                // Display version information
                t.setText("Version Number is " + versionNum);
                t.append("\nAfter KitKat = " + afterKitKat.toString());
                t.append("\nBuild.VERSION.RELEASE = " + Build.VERSION.RELEASE);
                t.append("\nBuild.VERSION.INCREMENTAL = " + Build.VERSION.INCREMENTAL);

                // Display build and screen metrics
                t.append("\nBuild.DISPLAY = " + Build.DISPLAY);
                t.append("\nScreen Size = " + getSizeName(view.getContext()));
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                t.append("\nDensity DPI = " + metrics.densityDpi);
                t.append("\nDensity = " + metrics.density);
                t.append("\nX DPI = " + metrics.xdpi);
                t.append("\nY DPI = " + metrics.ydpi);
                t.append("\nHeight (px) = " + metrics.heightPixels);
                t.append("\nWidth (px) = " + metrics.widthPixels);

                // Display screen layout information
                Configuration config = getResources().getConfiguration();
                int screenLayout = config.screenLayout;
                t.append("\n\nScreen Layout (HEX) = " + Integer.toHexString(screenLayout));
                t.append("\nSize Mask (HEX) = " + Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_MASK));
                t.append("\nLong Mask (HEX) = " + Integer.toHexString(Configuration.SCREENLAYOUT_LONG_MASK));

                // Extract and display size and long bits from screen layout
                screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
                t.append("\nScreenLayout & Size Mask (HEX) = " + Integer.toHexString(screenLayout));

                screenLayout &= Configuration.SCREENLAYOUT_LONG_MASK;
                t.append("\nScreenLayout & Long Mask (HEX) = " + Integer.toHexString(screenLayout));
            }
        }
    }

    // Method to determine the screen size category of the device
    private static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        int screenSize = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        Log.d("ScreenSizeDetection", "Screen layout value: " + screenSize);

        // Determine screen size category based on screenSize value
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return "xlarge";
            default:
                return "unidentified";
        }
    }
}
