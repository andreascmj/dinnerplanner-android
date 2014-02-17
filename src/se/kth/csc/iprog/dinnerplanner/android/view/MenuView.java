package se.kth.csc.iprog.dinnerplanner.android.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.dinnerplanner.android.MenuActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Jonas on 2014-02-17.
 */
public class MenuView implements  Observer {
    View view;
    Activity menu;

    public MenuView(View view, Activity menu) {

        // store in the class the reference to the Android View
        this.menu = menu;
        this.view = view;
        MenuActivity.dinner.addObserver(this);
        fillDropdown();
        fillStarterView();
    }

    private void fillStarterView() {

        LinearLayout starterlayout = (LinearLayout) menu.findViewById(R.id.starterScrollView);
        starterlayout.removeAllViews();
        populateDishType(Dish.STARTER, starterlayout);
        LinearLayout mainlayout = (LinearLayout) menu.findViewById(R.id.mainScrollView);
        mainlayout.removeAllViews();
        populateDishType(Dish.MAIN, mainlayout);
        LinearLayout desertlayout = (LinearLayout) menu.findViewById(R.id.desertScrollView);
        desertlayout.removeAllViews();
        populateDishType(Dish.DESERT, desertlayout);
    }

    private void populateDishType(int type, LinearLayout layout) {
        for (Dish s :  MenuActivity.dinner.getDishesOfType(type)) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(150, 150);;

            LinearLayout imageAndTextBox = new LinearLayout(menu);
            imageAndTextBox.setOrientation(LinearLayout.VERTICAL);
            imageAndTextBox.setPadding(5, 5, 5, 5);

            TextView tv = new TextView(menu);
            tv.setText(s.getName());
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            tv.setTextSize(13);

            ImageButton ib = new ImageButton(menu);
            int imageId = menu.getResources().getIdentifier(s.getImage(), "drawable", menu.getPackageName());
            ib.setImageResource(imageId);

            ib.setLayoutParams(lp);
            ib.setTag(s);
            ib.setOnClickListener((View.OnClickListener) menu);

            if ( MenuActivity.dinner.getSelectedDish(s.getType()) == s) {
                imageAndTextBox.setBackgroundColor(Color.rgb(145, 32, 77));
                tv.setTextColor(Color.rgb(226, 247, 206));
            }


            layout.addView(imageAndTextBox);
            imageAndTextBox.addView(ib);
            imageAndTextBox.addView(tv);
        }
    }

    private void fillDropdown() {
        Spinner spinner = (Spinner) menu.findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(menu, R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void update_total_cost(){
        TextView totalcost_box = (TextView)menu.findViewById(R.id.total_cost);
        totalcost_box.setText("Total cost: " +  MenuActivity.dinner.getTotalMenuPrice() + " kr");
    }


    @Override
    public void update(Observable observable, Object o) {
        update_total_cost();
        fillStarterView();
    }
}
