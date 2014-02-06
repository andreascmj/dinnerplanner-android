package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Jonas on 2014-02-06.
 */
public class MenuActivity extends Activity {

    public static DinnerModel dinner = new DinnerModel();


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
                update_total_cost();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                dinner.setNumberOfGuests(0);
                update_total_cost();
            }
            });


    }

    private void fillDropdown() {
        Spinner spinner = (Spinner) findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void update_total_cost(){
        TextView totalcost_box = (TextView)findViewById(R.id.total_cost);
        totalcost_box.setText("Total cost: " + dinner.getTotalMenuPrice() + " kr");
    }

    public void details_click(){
        if (dinner.getFullMenu().isEmpty()){

        }
        else{
            Intent details_screen_navigation = new Intent(this, DetailsActivity.class);
            startActivity(details_screen_navigation);
        }
    }


}
