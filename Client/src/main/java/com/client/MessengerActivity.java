package com.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

// Messenger 使用Handler和Message对象进行通信
// 适用于简单的单向或双向通信
public class MessengerActivity extends AppCompatActivity {

    private static TextView textView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_activity);
        textView = findViewById(R.id.txt_view);

        intent = new Intent();
        intent.setPackage("com.service");
        intent.setAction("com.service3.action");
    }



    public void bind (View view) {
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    Messenger messengerProxy;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messengerProxy = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    int i = 0;
    public void button2Click (View view) {
        i++;
        Message msg = Message.obtain();
        msg.arg1 = i;
        msg.arg2 = 2;
        msg.replyTo = clientMessenger;
        try {
            messengerProxy.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private final Messenger clientMessenger = new Messenger(new ClientHandler(this));

    // 使用[静态内部类+弱引用]避免内存泄漏
    private static class ClientHandler extends Handler {
        private final WeakReference<MessengerActivity> weakReference;

        ClientHandler(MessengerActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message serviceMsg) {
            MessengerActivity activity = weakReference.get();
            if (activity != null) {
                textView.setText("服务端返回结果：" + serviceMsg.arg1);
            }
        }
    }

}