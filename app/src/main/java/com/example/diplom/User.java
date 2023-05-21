package com.example.diplom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class User implements Serializable {

    public String Advice, sal;
    public Integer Salary, UserID, Fifty, Twenty, Thirty;
    public transient SQLiteDatabase db;

    public User() {

    }

    public Integer getSalary() {
        return (this.Salary);
    }

    public Integer getFifty() {
        return (this.Fifty);
    }

    public Integer getTwenty() {
        return (this.Twenty);
    }

    public Integer getThirty() {
        return (this.Thirty);
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

    public void setFifty() {
        String select_string = "SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Fifty = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Fifty"))));
        }
    }
    public void setTwenty() {
        String select_string = "SELECT Twenty FROM fifty_twenty_thirty WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Twenty = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Twenty"))));
        }
    }
    public void setThirty() {
        String select_string = "SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Thirty = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Thirty"))));
        }
    }
}