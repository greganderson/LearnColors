package com.familybiz.greg.learncolors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class ColorActivity extends Activity {

	private int color = 0;
	private int[] colors = {
			Color.BLUE,
			Color.BLACK,
			Color.RED,
			Color.GREEN,
			Color.YELLOW,
			Color.WHITE,
			Color.GRAY,
			Color.rgb(255, 102, 0),
			Color.rgb(128, 0, 128),
			Color.rgb(135, 194, 175),
			Color.rgb(255, 20, 147)
	};

	private LinearLayout leftLayout;
	private LinearLayout rightLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LinearLayout rootLayout = new LinearLayout(this);
		rootLayout.setOrientation(LinearLayout.HORIZONTAL);
		setTheme(android.R.style.Theme_Black);

		leftLayout = new LinearLayout(this);
		rightLayout = new LinearLayout(this);
		setLayoutColor();

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);

		leftLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				color--;
				// Reset color counter
				if (color < 0)
					color = colors.length - 1;
				setLayoutColor();
			}
		});

		rightLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				color++;
				// Reset color counter
				if (color == colors.length)
					color = 0;
				setLayoutColor();
			}
		});

		rootLayout.addView(leftLayout, layoutParams);
		rootLayout.addView(rightLayout, layoutParams);

		setContentView(rootLayout);
	}

	private void setLayoutColor() {
		leftLayout.setBackgroundColor(colors[color]);
		rightLayout.setBackgroundColor(colors[color]);
	}
}
