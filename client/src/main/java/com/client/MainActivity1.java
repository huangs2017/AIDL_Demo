package com.client;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;
import com.service.AidlInterface;

public class MainActivity1 extends AppCompatActivity {

    private TextView textView = null;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txt);

        intent = new Intent();
        intent.setPackage("com.service");
        intent.setAction("com.service1.action");
    }

    public void bind(View view) {
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AidlInterface aidlInterface = AidlInterface.Stub.asInterface(iBinder);
            try {
                String s = aidlInterface.getString();
                textView.setText(s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}