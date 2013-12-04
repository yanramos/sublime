package com.example.sublime;

import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.dm.zbar.android.scanner.ZBarConstants;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ScanActivity extends Activity {
	
	protected static final int ZBAR_SCANNER_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);

		ImageButton scan = (ImageButton) findViewById(R.id.scanBtn);
		scan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScanActivity.this, ZBarScannerActivity.class);
				startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
			}
		});
		ImageButton list = (ImageButton) findViewById(R.id.listBtn);
		list.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ScanActivity.this, PromoListActivity.class);
                startActivity(i);
                finish();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{    
	    if (resultCode == RESULT_OK) 
	    {
	        Intent i = new Intent(ScanActivity.this, LoadingActivity.class);
	        i.putExtra("updating", false);
	        i.putExtra("id", data.getStringExtra(ZBarConstants.SCAN_RESULT));
            startActivity(i);
            finish();
	    } else if(resultCode == RESULT_CANCELED) {
	        Toast.makeText(this, "Camera unavailable", Toast.LENGTH_SHORT).show();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan, menu);
		return true;
	}

}
