
// Pinch to zoom image.

package com.example.snippet.pinchtozoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CustomView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the display to custom view
        view = new CustomView(getApplicationContext());
        setContentView(view);
    }
}
