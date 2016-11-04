package com.aapbd.visiorace;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class SearchEngineEventsActivity extends Activity {
	private Context con;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchengineevents);
		con = this;
	}

	public void setBack(View v) {
		finish();
	}

}
