package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by Erik on 2014-02-06.
 */
public class DetailsActivity  extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_screen);
        set_total_cost();
        populateImageButtons();
    }

    public void set_details(){
        TextView details_text_box = (TextView)findViewById(R.id.details_text);
        String ingredients = "";
        for (Ingredient i :MenuActivity.dinner.getAllIngredients()){
            ingredients = ingredients + i.getName() + " \n";
        }
        details_text_box.setText(ingredients);
    }

    public void set_total_cost(){
        TextView total_cost_text_box = (TextView)findViewById(R.id.details_total_cost);
        total_cost_text_box.setText("Total cost: " + MenuActivity.dinner.getTotalMenuPrice() + " kr");
    }

    public void populateImageButtons() {
        if(MenuActivity.dinner.getFullMenu().isEmpty()) {

        } else {
            Dish starter = MenuActivity.dinner.getSelectedDish(1);
            Dish main = MenuActivity.dinner.getSelectedDish(2);
            Dish desert = MenuActivity.dinner.getSelectedDish(3);

            if(starter != null) {
                ImageButton starterButton = (ImageButton) findViewById(R.id.starter_image);
                starterButton.setImageResource(getResources().getIdentifier(starter.getImage(), "drawable", getPackageName()));
                starterButton.setTag(starter);
                starterButton.setOnClickListener(this);
            }
            if(main != null) {
                ImageButton mainButton = (ImageButton) findViewById(R.id.main_image);
                mainButton.setImageResource(getResources().getIdentifier(main.getImage(), "drawable", getPackageName()));
                mainButton.setTag(main);
                mainButton.setOnClickListener(this);
            }
            if(desert != null) {
                ImageButton desertButton = (ImageButton) findViewById(R.id.desert_image);
                desertButton.setImageResource(getResources().getIdentifier(desert.getImage(), "drawable", getPackageName()));
                desertButton.setTag(main);
                desertButton.setOnClickListener(this);
            }

        }
    }

