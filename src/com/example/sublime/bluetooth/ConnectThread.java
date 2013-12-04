package com.example.sublime.bluetooth;
import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;


public class ConnectThread extends Thread{

	private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private final BluetoothAdapter adapter;
 
    public ConnectThread(BluetoothAdapter adapter, BluetoothDevice device) {
        BluetoothSocket tmp = null;
        mmDevice = device;
         try {
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("dac13e74-de49-43bb-b255-50725fbbface"));
        } catch (IOException e) { }
        mmSocket = tmp;
        
        this.adapter = adapter;
    }
 
    @Override
	public void run() {
        adapter.cancelDiscovery();
 
        try {
            mmSocket.connect();
        } catch (IOException connectException) {
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }
         manageConnectedSocket(mmSocket);
    }
 
    private void manageConnectedSocket(BluetoothSocket mmSocket2) {
		
	}

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
	
}
