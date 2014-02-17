package se.kth.csc.iprog.dinnerplanner.android.view;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import se.kth.csc.iprog.dinnerplanner.android.MenuActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by Andreas on 2014-02-17.
 */
public class DetailsView {

    View view;
    Activity details;

    public DetailsView(View view, Activity details) {

        // store in the class the reference to the Android View
        this.details = details;
        this.view = view;
        set_details();
        set_total_cost();
        set_persons();
        populateImageButtons();
    }

    private void set_persons() {
        TextView persons = (TextView) details.findViewById(R.id.persons_detail);
        persons.setText(MenuActivity.dinner.getNumberOfGuests() + " pers");
    }

    public void set_details(){
        TextView details_text_box = (TextView)details.findViewById(R.id.details_text);
        TextView header = (TextView) details.findViewById(R.id.header_text);
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
        TextView details_text_box = (TextView)details.findViewById(R.id.details_text);
        details_text_box.setText(dish.getDescription());
        TextView header_details_text_box = (TextView)details.findViewById(R.id.header_text);
        int type = dish.getType();
        switch(type){
            case(1): header_details_text_box.setText("Starter"); break;
            case(2): header_details_text_box.setText("Main"); break;
            case(3): header_details_text_box.setText("Desert"); break;
            default: header_details_text_box.setText("Ingredients"); break;
        }
    }

    public void set_total_cost(){
        TextView total_cost_text_box = (TextView)details.findViewById(R.id.details_total_cost);
        total_cost_text_box.setText("Total cost: " + MenuActivity.dinner.getTotalMenuPrice() + " kr");
    }

    public void populateImageButtons() {
        LinearLayout ll = (LinearLayout) details.findViewById(R.id.details_lin_layout);

        LinearLayout imageHolder = new LinearLayout(details);
        imageHolder.setOrientation(LinearLayout.VERTICAL);
        imageHolder.setPadding(5, 5, 5, 5);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(180, 180);
        lp.setMargins(7, 14, 20, 14);
        ImageButton ingredientsButton = new ImageButton(details);
        ingredientsButton.setImageResource(details.getResources().getIdentifier("ingredients", "drawable", details.getPackageName()));
        ingredientsButton.setLayoutParams(lp);
        ingredientsButton.setTag("ingredients");
        ingredientsButton.setOnClickListener((View.OnClickListener) details);

        ll.addView(imageHolder);
        imageHolder.addView(ingredientsButton);

        TextView tv = new TextView(details);
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

        LinearLayout imageHolder = new LinearLayout(details);
        imageHolder.setOrientation(LinearLayout.VERTICAL);
        imageHolder.setPadding(5, 5, 5, 5);

        ImageButton ib = new ImageButton(details);
        int imageId = details.getResources().getIdentifier(dish.getImage(), "drawable", details.getPackageName());
        ib.setImageResource(imageId);
        lp.setMargins(7, 14, 7, 14);
        ib.setLayoutParams(lp);
        ib.setTag(dish);
        ib.setOnClickListener((View.OnClickListener) details);

        ll.addView(imageHolder);
        imageHolder.addView(ib);

        TextView tv = new TextView(details);
        tv.setText(dish.getName());
        tv.setGravity(Gravity.CENTER);
        imageHolder.addView(tv);
    }

}
