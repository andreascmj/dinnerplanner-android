package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.widget.TextView;



import se.kth.csc.iprog.dinnerplanner.android.view.DetailsView;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Erik on 2014-02-06.
 */
public class DetailsActivity  extends Activity implements View.OnClickListener{

    DetailsView view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_screen);
        this.view = new DetailsView(findViewById(R.id.details_screen), this);
    }

    @Override
    public void onClick(View view) {
        ImageButton pressed = (ImageButton)view;
        LinearLayout ll = (LinearLayout)pressed.getParent();
        LinearLayout ll2 = (LinearLayout)ll.getParent();
        TextView tv = (TextView) ll.getChildAt(1);
        for (int i = 0 ; i < ll2.getChildCount() ; i++){
            LinearLayout tempL = (LinearLayout)ll2.getChildAt(i);
            tempL.setBackgroundColor(Color.TRANSPARENT);

            TextView text = (TextView) tempL.getChildAt(1);
            text.setTextColor(Color.rgb(0, 0, 0));
        }
        tv.setTextColor(Color.rgb(226, 247, 206));
        ll.setBackgroundColor(Color.rgb(145, 32, 77));
        if(pressed.getTag() == "ingredients") {
            this.view.set_details();
            return;
        }
        Dish details_of = (Dish)pressed.getTag();
        this.view.set_details_dish(details_of);
    }
}
