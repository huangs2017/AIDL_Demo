package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class RemoteService1 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    static class MyBinder extends AidlInterface.Stub {
        @Override
        public String getString() throws RemoteException {
            return "777";
        }
    }

}