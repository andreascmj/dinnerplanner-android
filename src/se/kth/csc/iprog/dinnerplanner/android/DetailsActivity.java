package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by Erik on 2014-02-06.
 */
public class DetailsActivity  extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_screen);
        set_total_cost();
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
}
