package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.view.View;
import android.widget.TextView;

public class ExampleView {

	View view;

	public ExampleView(View view) {

		// store in the class the reference to the Android View
		this.view = view;

		TextView g1 = (TextView) view.findViewById(R.id.greeting_1);
		g1.setText("Welcome to Dinner Planner!");



		// Setup the rest of the view layout
	}

}
