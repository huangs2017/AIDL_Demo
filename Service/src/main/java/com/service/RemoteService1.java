package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import hanson.AidlInterface;

public class RemoteService1 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    static class MyBinder extends AidlInterface.Stub {
        @Override
        public String getString() throws RemoteException {
            return "服务端返回结果";
        }

        @Override
        public int callAdd(int a, int b) throws RemoteException {
            return add(a, b);
        }
    }

    public static int add(int a, int b) {
        return a + b;
    }

}