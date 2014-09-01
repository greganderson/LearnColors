package com.familybiz.greg.learncolors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
			Color.rgb(255, 102, 0),	// bad orange
			Color.rgb(128, 0, 128),	// purple
			Color.rgb(139, 69, 19),	// brown
			Color.rgb(255, 192, 203)	// pink
	};

	private LinearLayout leftLayout;
	private LinearLayout rightLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LinearLayout rootLayout = new LinearLayout(this);
		rootLayout.setOrientation(LinearLayout.HORIZONTAL);
		setTheme(android.R.style.Theme_Holo_Light);

		leftLayout = new LinearLayout(this);
		rightLayout = new LinearLayout(this);
		setLayoutColor();

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);


		ImageView leftArrow = new ImageView(this);
		ImageView rightArrow = new ImageView(this);
		leftArrow.setImageResource(R.drawable.ic_action_previous_item);
		rightArrow.setImageResource(R.drawable.ic_action_next_item);

		LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
		imageParams.gravity = Gravity.CENTER;
		leftLayout.addView(leftArrow, imageParams);
		rightLayout.addView(rightArrow, imageParams);


		leftArrow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				color--;
				// Reset color counter
				if (color < 0)
					color = colors.length - 1;
				setLayoutColor();
			}
		});

		rightArrow.setOnClickListener(new View.OnClickListener() {
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

	/**
	 * Sets the colors for both layouts, essentially changing the whole background.
	 */
	private void setLayoutColor() {
		leftLayout.setBackgroundColor(colors[color]);
		rightLayout.setBackgroundColor(colors[color]);
	}
}
