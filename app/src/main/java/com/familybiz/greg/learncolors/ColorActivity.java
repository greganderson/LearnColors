package com.familybiz.greg.learncolors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ColorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LinearLayout rootLayout = new LinearLayout(this);
		setTheme(android.R.style.Theme_Holo_Light);

		final int[] colorArray = {
				Color.BLUE,
				Color.BLACK,
				Color.RED,
				Color.GREEN,
				Color.YELLOW,
				Color.WHITE,
				Color.GRAY,
				Color.rgb(255, 102, 0),		// orange
				Color.rgb(128, 0, 128),		// purple
				Color.rgb(92, 51, 23),		// brown
				Color.rgb(255, 192, 203)	// pink
		};

		final ColorList colors = new ColorList(colorArray);

		final TextView screenText = new TextView(this);
		screenText.setText(R.string.screenText);
		screenText.setTextSize(20);
		screenText.setGravity(Gravity.CENTER);

		// Set initial color
		screenText.setBackgroundColor(colors.nextColor());

		screenText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				screenText.setBackgroundColor(colors.nextColor());
			}
		});

		LinearLayout.LayoutParams screenTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		rootLayout.addView(screenText, screenTextParams);

		setContentView(rootLayout);
	}
}
