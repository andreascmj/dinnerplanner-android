package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

/**
 * Created by Jonas on 2014-02-06.
 */
public class MenuActivity extends Activity {

    DinnerModel dinner = new DinnerModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
        super.onCreate(savedInstanceState);

        // Set the view for the main activity screen
        // it must come before any call to findViewById method

        setContentView(R.layout.planning_screen);
        fillDropdown();
        Spinner spinner = (Spinner) findViewById(R.id.dropdown);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selected = (int) l+1;
                dinner.setNumberOfGuests(selected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                dinner.setNumberOfGuests(0);
            }
            });


    }

    private void fillDropdown() {
        Spinner spinner = (Spinner) findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
