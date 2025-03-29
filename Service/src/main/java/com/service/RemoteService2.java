package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hanson.AidlInterface2;
import hanson.data.Book;
import hanson.data.Student;

public class RemoteService2 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends AidlInterface2.Stub {

        @Override
        public List<Book> getBooks(Student student) throws RemoteException {
            Map<Student, List<Book>> map = new HashMap<>();

            Student student1 = new Student("张三", 30);
            List<Book> bookList = new ArrayList<>();
            bookList.add(new Book("语文", 10));
            bookList.add(new Book("数学", 20));
            map.put(student1, bookList);

            return map.get(student1);
        }

        @Override
        public void getStudent(Student student) throws RemoteException {
            Log.i("tag", "服务器out： " + student);
            student.name = "王五";
            student.age = 50;
        }

        @Override
        public void getStudentWithInOutTag(Student student) throws RemoteException {
            Log.i("tag", "服务器inout： " + student);
            student.name = "张三";
            student.age = 30;
        }
    }

}