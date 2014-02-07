package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by Erik on 2014-02-06.
 */
public class DetailsActivity  extends Activity implements View.OnClickListener{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_screen);
        set_total_cost();
        set_details_ingredients();
        populatePics();
    }

    public void set_details_ingredients(){
        TextView details_text_box = (TextView)findViewById(R.id.details_text);
        String ingredients = "";
        for (Ingredient i :MenuActivity.dinner.getAllIngredients()){
            ingredients = ingredients + i.getName() + " \n";
        }
        details_text_box.setText(ingredients);
    }
    public void set_details_dish(Dish dish){
        TextView details_text_box = (TextView)findViewById(R.id.details_text);
        details_text_box.setText(dish.getDescription());
    }

    public void set_total_cost(){
        TextView total_cost_text_box = (TextView)findViewById(R.id.details_total_cost);
        total_cost_text_box.setText("Total cost: " + MenuActivity.dinner.getTotalMenuPrice() + " kr");
    }

    public void populatePics() {
        if(MenuActivity.dinner.getFullMenu().isEmpty()) {

        } else {
            Dish starter = MenuActivity.dinner.getSelectedDish(1);
            Dish main = MenuActivity.dinner.getSelectedDish(2);
            Dish desert = MenuActivity.dinner.getSelectedDish(3);

            ImageButton starterButton = (ImageButton) findViewById(R.id.starter_image);
            ImageButton mainButton = (ImageButton) findViewById(R.id.main_image);
            ImageButton desertButton = (ImageButton) findViewById(R.id.desert_image);

            starterButton.setImageResource(getResources().getIdentifier(starter.getImage(), "drawable", getPackageName()));
            mainButton.setImageResource(getResources().getIdentifier(main.getImage(), "drawable", getPackageName()));
            desertButton.setImageResource(getResources().getIdentifier(desert.getImage(), "drawable", getPackageName()));
        }
    }

    @Override
    public void onClick(View view) {
        ImageButton pressed = (ImageButton)view;
        Dish details_of = (Dish)pressed.getTag();
        set_details_dish(details_of);
    }
}
