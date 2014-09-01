package com.familybiz.greg.learncolors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


public class ColorActivity extends Activity {

	private ColorList colors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View rootLayout = new View(this);
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
				Color.rgb(139, 69, 19),		// brown
				Color.rgb(255, 192, 203)	// pink
		};

		colors = new ColorList(colorArray);

		// Set initial color
		rootLayout.setBackgroundColor(colors.nextColor());

		rootLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				rootLayout.setBackgroundColor(colors.nextColor());
			}
		});

		setContentView(rootLayout);
	}
}
