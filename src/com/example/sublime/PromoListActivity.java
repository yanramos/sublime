package com.example.sublime;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sublime.layout.*;

import android.widget.AdapterView.OnItemClickListener;

public class PromoListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// list = (ListView) findViewById(R.id.promoList);
		ListView list = new ListView(this);
		list.setAdapter(null);
		Intent intent = getIntent();
		
		FileInputStream fIn = null;
		ArrayList<PromoListItem> items = new ArrayList<PromoListItem>();
		try {
			fIn = openFileInput("sublime_if.txt");
			InputStreamReader isr = new InputStreamReader(fIn);
			char c;
			int j;
			String readString = "";
			int field = 0;
			int pos = 0;
			String key = null;
			String title = null;
			String subtitle = null;
			String icon = null;
			String address = null;
			boolean status = false;
			while ((j = isr.read()) != -1) {
				c = (char) j;
				if (c != '\n') {
					readString += c;
				} else {
					switch (field) {
					case 0:
						key = readString;
						Log.i("Key", key);
						field++;
						break;
					case 1:
						title = readString;
						Log.i("Title", title);
						field++;
						break;
					case 2:
						subtitle = readString;
						Log.i("Subtitle", subtitle);
						field++;
						break;
					case 3:
						icon = readString;
						Log.i("Icon", icon);
						field++;
						break;
					case 4:
						address = readString;
						Log.i("Address", address);
						field++;
						break;
					case 5:
						status = Boolean.parseBoolean(readString);
						Log.i("Status", String.valueOf(status));
						items.add(new PromoListItem(key, title, subtitle, icon,
								address, status, pos));
						field = 0;
						pos++;
						break;
					}
					readString = "";
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (fIn != null) {
				try {
					fIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Log.i("Tamanho", String.valueOf(items.size()));
		CustomItemAdapter listAdapter = new CustomItemAdapter(this, items);
		list.setAdapter(listAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getBaseContext(), "You clicked on " + arg2,
						Toast.LENGTH_LONG).show();
			}
		});
		setContentView(list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promo_list, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(PromoListActivity.this, ScanActivity.class);
		startActivity(i);
		finish();
	}

}
