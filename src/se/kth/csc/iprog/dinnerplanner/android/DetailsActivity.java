package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashSet;

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
    public void set_details_dish(Dish dish){
        TextView details_text_box = (TextView)findViewById(R.id.details_text);
        details_text_box.setText(dish.getDescription());
        TextView header_details_text_box = (TextView)findViewById(R.id.header_text);
        int type = dish.getType();
        switch(type){
            case(1): header_details_text_box.setText("Starter");
            case(2): header_details_text_box.setText("Main");
            case(3): header_details_text_box.setText("Desert");
            default: header_details_text_box.setText("Ingredients");
        }
    }

    public void set_total_cost(){
        TextView total_cost_text_box = (TextView)findViewById(R.id.details_total_cost);
        total_cost_text_box.setText("Total cost: " + MenuActivity.dinner.getTotalMenuPrice() + " kr");
    }

    public void populateImageButtons() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(150, 150);
        ImageButton ingredientsButton = (ImageButton) findViewById(R.id.ingredient_image);
        ingredientsButton.setImageResource(getResources().getIdentifier("ingredients", "drawable", getPackageName()));
        ingredientsButton.setLayoutParams(lp);

        if(MenuActivity.dinner.getFullMenu().isEmpty()) {

        } else {
            Dish starter = MenuActivity.dinner.getSelectedDish(1);
            Dish main = MenuActivity.dinner.getSelectedDish(2);
            Dish desert = MenuActivity.dinner.getSelectedDish(3);

            if(starter != null) {
                createImageButton(starter);
            }
            if(main != null) {
                createImageButton(main);
            }
            if(desert != null) {
                createImageButton(desert);
            }
        }
    }

    private void createImageButton(Dish dish) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(150, 150);

        ImageButton ib = new ImageButton(this);
        int imageId = getResources().getIdentifier(dish.getImage(), "drawable", getPackageName());
        ib.setImageResource(imageId);
        ib.setLayoutParams(lp);
        ib.setTag(dish);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ImageButton pressed = (ImageButton)view;
        Dish details_of = (Dish)pressed.getTag();
        set_details_dish(details_of);
    }
}
