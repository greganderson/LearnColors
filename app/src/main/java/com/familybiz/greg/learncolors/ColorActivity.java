package com.familybiz.greg.learncolors;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ColorActivity extends Activity {

    private static final String TAG = "LearnColorsSTT";

    private Pair mCurrentColor;
    private SpeechRecognizer sr;
    private Intent intent;
    private TextView screenText;

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

		screenText = new TextView(this);
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

        startListening();

        setContentView(rootLayout);
	}

    private void startListening() {
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "voice.recognition.test");

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        sr.startListening(intent);

    }

	class Pair {
		Integer key;
		String value;
		public Pair(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
	}


    class listener implements RecognitionListener {
        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
        }
        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
        }
        public void onRmsChanged(float rmsdB) {
            Log.d(TAG, "onRmsChanged");
        }
        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }
        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
        }
        public void onError(int error) {
            Log.d(TAG,  "error " +  error);
            sr.destroy();
            sr = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
            sr.setRecognitionListener(new listener());
            sr.startListening(intent);
        }
        public void onResults(Bundle results) {
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (data.contains(mCurrentColor.value)) {
                screenText.callOnClick();
                startListening();
            }
            else {
                startListening();
            }
        }
        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults");
        }
        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }

}
