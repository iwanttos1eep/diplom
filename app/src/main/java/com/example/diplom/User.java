package com.example.diplom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class User implements Serializable {

    public String Advice;
    public Integer Salary, UserID, Fifty, Twenty, Thirty, Products, Transport, Food, Entertainment, Other, Clothes, Together;
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
    public Integer getProducts() {return (this.Products);}
    public Integer getTransport() {return (this.Transport);}
    public Integer getFood() {return (this.Food);}
    public Integer getEntertainment() {return (this.Entertainment);}
    public Integer getOther() {return  (this.Other);}
    public Integer getClothes() {return (this.Clothes);}
    public Integer getTogether() {return (this.Together);}

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
    public void setProducts() {
        String select_string = "SELECT Products FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Products = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Products"))));
        }
    }
    public void  setTransport() {
        String select_string = "SELECT Transport FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Transport = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Transport"))));
        }
    }
    public void setFood() {
        String select_string = "SELECT Food FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Food = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Food"))));
        }
    }
    public void setEntertainment() {
        String select_string = "SELECT Entertainment FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Entertainment = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Entertainment"))));
        }
    }
    public void setOther() {
        String select_string = "SELECT Other FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Other = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Other"))));
        }
    }
    public void setClothes() {
        String select_string = "SELECT Clothes FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Clothes = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Clothes"))));
        }
    }
    public void setTogether() {
        String select_string = "SELECT Together FROM Calculator WHERE _id = '" + this.UserID + "'";
        Cursor c2 = db.rawQuery(select_string, null);
        while (c2.moveToNext()) {
            this.Together = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Together"))));
        }
    }
}