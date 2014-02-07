package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Jonas on 2014-02-06.
 */
public class MenuActivity extends Activity implements View.OnClickListener {

    public static DinnerModel dinner = new DinnerModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
        super.onCreate(savedInstanceState);

        // Set the view for the main activity screen
        // it must come before any call to findViewById method

        setContentView(R.layout.planning_screen);
        fillDropdown();
        fillStarterView();
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

    private void fillStarterView() {

        LinearLayout starterlayout = (LinearLayout) findViewById(R.id.starterScrollView);
        starterlayout.removeAllViews();
        populateDishType(Dish.STARTER, starterlayout);
        LinearLayout mainlayout = (LinearLayout) findViewById(R.id.mainScrollView);
        mainlayout.removeAllViews();
        populateDishType(Dish.MAIN, mainlayout);
        LinearLayout desertlayout = (LinearLayout) findViewById(R.id.desertScrollView);
        desertlayout.removeAllViews();
        populateDishType(Dish.DESERT, desertlayout);
    }

    private void populateDishType(int type, LinearLayout layout) {
        for (Dish s : dinner.getDishesOfType(type)) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(150, 150);

            LinearLayout imageAndTextBox = new LinearLayout(this);
            imageAndTextBox.setOrientation(LinearLayout.VERTICAL);

            TextView tv = new TextView(this);
            tv.setText(s.getName());
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            tv.setTextSize(13);

            ImageButton ib = new ImageButton(this);
            int imageId = getResources().getIdentifier(s.getImage(), "drawable", getPackageName());
            ib.setImageResource(imageId);
            ib.setLayoutParams(lp);
            ib.setTag(s);
            ib.setOnClickListener(this);

            if (dinner.getSelectedDish(s.getType()) == s) {
                imageAndTextBox.setBackgroundColor(Color.rgb(45, 02, 178));
            }


            layout.addView(imageAndTextBox);
            imageAndTextBox.addView(ib);
            imageAndTextBox.addView(tv);
        }
    }

    private void fillDropdown() {
        Spinner spinner = (Spinner) findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void update_total_cost(){
        TextView totalcost_box = (TextView)findViewById(R.id.total_cost);
        //totalcost_box.setText("Total cost: " + dinner.getTotalMenuPrice() + " kr");
        totalcost_box.setText("Total cost: " + dinner.getNumberOfGuests() + " kr"); //TODO remove this
    }

    public void details_click(View view){
        Intent details_screen_navigation = new Intent(this, DetailsActivity.class);
        startActivity(details_screen_navigation);
    }

    @Override
    public void onClick(View view) {
        Dish s = (Dish)view.getTag();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(s.getName());

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.popup_layout, null);
        builder.setView(v);
        builder.setPositiveButton(R.string.choose, new PopupOnClickListener(s));
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        ImageView iv = (ImageView)v.findViewById(R.id.popupImage);
        int imageId = getResources().getIdentifier(s.getImage(), "drawable", getPackageName());
        iv.setImageResource(imageId);

        TextView tv = (TextView)v.findViewById(R.id.popupText);
        tv.setText(s.getDescription());


        dialog.show();
    }

    private class PopupOnClickListener implements DialogInterface.OnClickListener {

        private Dish d;

        public PopupOnClickListener(Dish d) {
            this.d = d;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (d.getType()) {
                case 1: dinner.setStarter(d); break;
                case 2: dinner.setMain(d); break;
                    default: dinner.setDesert(d);
            }

            fillStarterView();
            dialogInterface.cancel();
        }
    }


}
