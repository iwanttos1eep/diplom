package com.example.diplom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class User implements Serializable {

    public String Advice, sovet;
    public Integer idAdvice;

    public transient SQLiteDatabase db;


    public String getAdvice() {
        int a =  (int) (Math.random()*(4)) + 1;
        String select_string = "SELECT AdviceText FROM ADVICES WHERE _idAdvice='" + a + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while(c2.moveToNext()) {
            sovet = c2.getString(c2.getColumnIndexOrThrow("AdviceText"));
            return sovet;
        }
                return null;
    }
}
