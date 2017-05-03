package com.myonpause.mialmito.projekarbeit_syp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class AcceptThread extends Thread {
    private final BluetoothServerSocket mmServerSocket;

    public AcceptThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            //BluetoothAdapter mmBluetoothAdapter;
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(null, "Device does not support Bluetooth", Toast.LENGTH_LONG).show();
            }

            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(device.getName(), UUID.fromString(device.EXTRA_UUID));
        } catch (IOException e) {
            Toast.makeText(null, "Fehler:001", Toast.LENGTH_LONG).show();
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Toast.makeText(null, "Fehler:002", Toast.LENGTH_LONG).show();
                break;
            }

            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                manageMyConnectedSocket(socket);
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    Toast.makeText(null, "Fehler:003", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private void manageMyConnectedSocket(BluetoothSocket socket) {
        Toast.makeText(null, "manage BluetoothSocket", Toast.LENGTH_LONG).show();
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {

            Toast.makeText(null, "Could not close the connect socket", Toast.LENGTH_LONG).show();
        }
    }
}