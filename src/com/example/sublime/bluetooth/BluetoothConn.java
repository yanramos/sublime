package com.example.sublime.bluetooth;

import java.util.ArrayList;
import java.util.Set;

import com.example.sublime.R;
import android.os.Bundle;
import android.bluetooth.*;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class BluetoothConn extends Activity {
	
	final int REQUEST_ENABLE_BT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promo_list);
		
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if(adapter != null)
		{
			if (!adapter.isEnabled()) {
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			} else {
				connect(adapter);
			}
		}
	}
	
	private void connect(BluetoothAdapter adapter) {
		Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>(pairedDevices);
		    //Exibir lista de nomes de dispositivos e obter endereco MAC
			int chosen = 0;
			
		} else {
			Toast.makeText(getApplicationContext(), "Nao ha dispositivos pareados.", Toast.LENGTH_LONG).show();
		}
		
	}

	public void onActivityResult(){
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bluetooth_conn, menu);
		return true;
	}

}
