package com.familybiz.greg.learncolors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ColorActivity extends Activity {

	private Pair mCurrentColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LinearLayout rootLayout = new LinearLayout(this);
		setTheme(android.R.style.Theme_Holo_Light);

		final Pair[] colorArray = {
				new Pair(Color.BLUE, "blue"),
				new Pair(Color.BLACK, "black"),
				new Pair(Color.RED, "red"),
				new Pair(Color.GREEN, "green"),
				new Pair(Color.YELLOW, "yellow"),
				new Pair(Color.WHITE, "white"),
				new Pair(Color.GRAY, "gray"),
				new Pair(Color.rgb(255, 102, 0), "orange"),
				new Pair(Color.rgb(128, 0, 128), "purple"),
				new Pair(Color.rgb(92, 51, 23), "brown"),
				new Pair(Color.rgb(255, 192, 203), "pink")
		};

		final RandomItemList<Pair> colors = new RandomItemList<Pair>(colorArray);

		final TextView screenText = new TextView(this);
		screenText.setText(R.string.screenText);
		screenText.setTextSize(20);
		screenText.setGravity(Gravity.CENTER);

		// Set initial color
		mCurrentColor = colors.nextItem();
		screenText.setBackgroundColor(mCurrentColor.key);

		screenText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentColor = colors.nextItem();
				screenText.setBackgroundColor(mCurrentColor.key);
				Log.i("Color", mCurrentColor.value);
			}
		});

		LinearLayout.LayoutParams screenTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		rootLayout.addView(screenText, screenTextParams);

		setContentView(rootLayout);
	}

	class Pair {
		Integer key;
		String value;
		public Pair(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
