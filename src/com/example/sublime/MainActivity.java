package com.example.sublime;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private static int TEMPO = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(i);
                finish();
            }
        }, TEMPO);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
