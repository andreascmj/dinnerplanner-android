package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;

/**
 * Created by Jonas on 2014-02-06.
 */
public class MenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
        super.onCreate(savedInstanceState);

        ExampleView mainView = new ExampleView(findViewById(R.id.start_screen));

        setContentView(R.layout.activity_main);
    }
}
