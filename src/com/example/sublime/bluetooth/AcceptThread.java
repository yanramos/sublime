package com.example.sublime.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class AcceptThread extends Thread {
	
	private final BluetoothServerSocket mmServerSocket;
	final int REQUEST_ENABLE_BT = 1;
	 
    public AcceptThread(BluetoothAdapter adapter) {
        BluetoothServerSocket tmp = null;
        try {
            tmp = adapter.listenUsingRfcommWithServiceRecord("Sublime", UUID.fromString("dac13e74-de49-43bb-b255-50725fbbface"));
        } catch (IOException e) { }
        mmServerSocket = tmp;
    }

	@Override
	public void run() {
        BluetoothSocket socket = null;
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            if (socket != null) {
                try {
                	manageConnectedSocket(socket);
					mmServerSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                break;
            }
        }
    }
 
    private void manageConnectedSocket(BluetoothSocket socket) {
		// TODO Auto-generated method stub
		
	}

	public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }

}
