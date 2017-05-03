package com.myonpause.mialmito.projekarbeit_syp;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.bluetooth.*;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Method;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btVerbinden: Toast.makeText(getApplicationContext(), "hallo du!", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresPermission(allOf = {
            Manifest.permission.BLUETOOTH,
    Manifest.permission.BLUETOOTH_ADMIN})
    public void myVerbinde(View view) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth is disabled", Toast.LENGTH_SHORT).show();
                mBluetoothAdapter.enable();
                Toast.makeText(getApplicationContext(), "Enable Bluetooth",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth is enabled", Toast.LENGTH_SHORT).show();
                mBluetoothAdapter.disable();
                Toast.makeText(getApplicationContext(), "Disable Bluetooth",Toast.LENGTH_SHORT).show();
            }
        }
        BluetoothDevice hallo;

        mBluetoothAdapter.startDiscovery();


    }
    @RequiresPermission(allOf = {
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN})
    public void getPaired(View view) throws NoSuchMethodException {



            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_LONG).show();
            }

            Set<BluetoothDevice> pairedDevices
                = mBluetoothAdapter.getBondedDevices();

            //BluetoothDevice[] mydevice = new BluetoothDevice[20];
            EditText myText = (EditText) this.findViewById(R.id.editText2);
            //BluetoothDevice[] deviceArray = new BluetoothDevice[50];
            myText.setText("Devices\n");
            BluetoothDevice myDevice = null;
            for(BluetoothDevice d : pairedDevices){
            System.out.println(d.toString());
            if(d.toString().equals("E3:28:E9:21:1C:04")){
                myDevice = d;
            }
            myText.append(d.getName()+"\n");
               // mBluetoothAdapter.getProfileProxy (context, listener, BluetoothProfile.A2DP);
            Method connect = BluetoothA2dp.class.getDeclaredMethod("connect", BluetoothDevice.class);
            //https://www.youtube.com/playlist?list=PLQrQKDQmvSfxEmYOugNkYLSEs5oLxs5u6
           // mBluetoothAdapter.listenUsingRfcommWithServiceRecord(d.getName(), d.getUuids());
        }

        //BluetoothDevice[] mydeviceArray = (BluetoothDevice[]) pairedDevices.toArray();
        //myDevice = mydeviceArray[1];
        System.out.println("-------------------------");
        if(myDevice!=null)
            System.out.println(myDevice.getName());
        else
            System.out.println("no Device");


    }

    public void hallodu(View view) {
        Toast.makeText(getApplicationContext(), "hallo du!", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.myonpause.mialmito.projekarbeit_syp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.myonpause.mialmito.projekarbeit_syp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    public void onConnect(){
        Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_LONG).show();

    }


}
