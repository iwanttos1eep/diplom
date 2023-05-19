package com.example.diplom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class User implements Serializable {

    public String Advice, sal;
    public Integer Salary, UserID;
    public transient SQLiteDatabase db;

    public User() {

    }

    public Integer getSalary() {
        return (this.Salary);
    }

    public String getAdvice() {
        int a = (int) (Math.random() * (4)) + 1;
        String select_string = "SELECT AdviceText FROM ADVICES WHERE _idAdvice='" + a + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            Advice = c2.getString(c2.getColumnIndexOrThrow("AdviceText"));
            return Advice;
        }
        return null;
    }

    public void setSalary() {
        String select_string = "SELECT Salary FROM fifty_twenty_thirty WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Salary = Integer.valueOf(c2.getString(c2.getColumnIndexOrThrow("Salary")));
        }
    }
}