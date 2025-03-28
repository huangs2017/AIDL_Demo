package com.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import hanson.AidlInterface2;
import hanson.data.Book;
import hanson.data.Student;

public class MainActivity2 extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        intent = new Intent();
        intent.setPackage("com.service");
        intent.setAction("com.service2.action");
    }

    public void bind(View view) {
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AidlInterface2 aidlInterface = AidlInterface2.Stub.asInterface(service);
            try {
                Student student = new Student("张三", 21);
                List<Book> bookList = aidlInterface.getBooks(student);
                Log.e("tag", "客户端in： " + bookList);

                aidlInterface.getStudent(student);
                Log.e("tag", "客户端out： " + student);

                aidlInterface.getStudentWithInOutTag(student);
                Log.e("tag", "客户端inout： " + student);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}