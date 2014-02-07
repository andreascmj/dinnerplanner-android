package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.apache.http.impl.conn.AbstractClientConnAdapter;

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
        set_details();
        set_total_cost();
        set_persons();
        populateImageButtons();
    }

    private void set_persons() {
        TextView persons = (TextView) findViewById(R.id.persons_detail);
        persons.setText(MenuActivity.dinner.getNumberOfGuests() + " pers");
    }

    public void set_details(){
        TextView details_text_box = (TextView)findViewById(R.id.details_text);
        TextView header = (TextView) findViewById(R.id.header_text);
        StringBuilder sb = new StringBuilder();
        for (Ingredient i : MenuActivity.dinner.getAllIngredients()){
            double amount = Math.ceil(i.getQuantity() * MenuActivity.dinner.getNumberOfGuests());
            sb.append(amount + " " + i.getUnit()+ " ");
            sb.append(i.getName() + " \n");
        }
        details_text_box.setText(sb.toString());
        header.setText("Ingredients");
    }
    public void set_details_dish(Dish dish){
        TextView details_text_box = (TextView)findViewById(R.id.details_text);
        details_text_box.setText(dish.getDescription());
        TextView header_details_text_box = (TextView)findViewById(R.id.header_text);
        int type = dish.getType();
        switch(type){
            case(1): header_details_text_box.setText("Starter"); break;
            case(2): header_details_text_box.setText("Main"); break;
            case(3): header_details_text_box.setText("Desert"); break;
            default: header_details_text_box.setText("Ingredients"); break;
        }
    }

    public void set_total_cost(){
        TextView total_cost_text_box = (TextView)findViewById(R.id.details_total_cost);
        total_cost_text_box.setText("Total cost: " + MenuActivity.dinner.getTotalMenuPrice() + " kr");
    }

    public void populateImageButtons() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.details_lin_layout);

        LinearLayout imageHolder = new LinearLayout(this);
        imageHolder.setOrientation(LinearLayout.VERTICAL);
        imageHolder.setPadding(5, 5, 5, 5);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(180, 180);
        lp.setMargins(7, 14, 20, 14);
        ImageButton ingredientsButton = new ImageButton(this);
        ingredientsButton.setImageResource(getResources().getIdentifier("ingredients", "drawable", getPackageName()));
        ingredientsButton.setLayoutParams(lp);
        ingredientsButton.setTag("ingredients");
        ingredientsButton.setOnClickListener(this);

        ll.addView(imageHolder);
        imageHolder.addView(ingredientsButton);

        TextView tv = new TextView(this);
        tv.setText("Ingredients");
        tv.setGravity(Gravity.CENTER);
        imageHolder.addView(tv);

        if(MenuActivity.dinner.getFullMenu().isEmpty()) {

        } else {
            Dish starter = MenuActivity.dinner.getSelectedDish(1);
            Dish main = MenuActivity.dinner.getSelectedDish(2);
            Dish desert = MenuActivity.dinner.getSelectedDish(3);

            if(starter != null) {
                createImageButton(starter, ll);
            }
            if(main != null) {
                createImageButton(main, ll);
            }
            if(desert != null) {
                createImageButton(desert, ll);
            }
        }
    }

    private void createImageButton(Dish dish, LinearLayout ll) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(180, 180);

        LinearLayout imageHolder = new LinearLayout(this);
        imageHolder.setOrientation(LinearLayout.VERTICAL);
        imageHolder.setPadding(5, 5, 5, 5);

        ImageButton ib = new ImageButton(this);
        int imageId = getResources().getIdentifier(dish.getImage(), "drawable", getPackageName());
        ib.setImageResource(imageId);
        lp.setMargins(7, 14, 7, 14);
        ib.setLayoutParams(lp);
        ib.setTag(dish);
        ib.setOnClickListener(this);

        ll.addView(imageHolder);
        imageHolder.addView(ib);

        TextView tv = new TextView(this);
        tv.setText(dish.getName());
        tv.setGravity(Gravity.CENTER);
        imageHolder.addView(tv);
    }

    @Override
    public void onClick(View view) {
        ImageButton pressed = (ImageButton)view;
        LinearLayout ll = (LinearLayout)pressed.getParent();
        LinearLayout ll2 = (LinearLayout)ll.getParent();
        TextView tv = (TextView) ll.getChildAt(1);
        tv.setTextColor(Color.rgb(226, 247, 206));
        for (int i = 0 ; i < ll2.getChildCount() ; i++){
            LinearLayout tempL = (LinearLayout)ll2.getChildAt(i);
            tempL.setBackgroundColor(Color.TRANSPARENT);
        }
        ll.setBackgroundColor(Color.rgb(145, 32, 77));
        if(pressed.getTag() == "ingredients") {
            set_details();
            return;
        }
        Dish details_of = (Dish)pressed.getTag();
        set_details_dish(details_of);
    }
}
