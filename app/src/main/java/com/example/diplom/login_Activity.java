package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login_Activity extends AppCompatActivity {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Button log_button;
    String UserID;
    String Purpose;
    User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log_button = findViewById(R.id.logbtn);
        TextInputEditText login = findViewById(R.id.emailLogin);
        TextInputEditText password_login = findViewById(R.id.passwordLogin);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_text = login.getText().toString();
                String password_text = String.valueOf(password_login.getText());
                String check_string = "SELECT * FROM USERS WHERE Name='" + username_text + "' AND Password = '" + password_text + "'";
                Cursor c = db.rawQuery(check_string, null);
                if (c.getCount() == 0) {
                    //System.out.println("Такого пользователя не существует!");
                    Toast toast = Toast.makeText(login_Activity.this, "Такого пользователя не существует", Toast.LENGTH_LONG);
                    toast.show();
                }

                else {
                    String this_string = "SELECT _id FROM USERS WHERE Name='" + username_text + "' AND Password = '" + password_text + "'";
                    Cursor c2 = db.rawQuery(this_string, null);
                    while (c2.moveToNext()) {
                        UserID = c2.getString(c2.getColumnIndexOrThrow("_id"));
                    }
                    String check_akk_string = "SELECT Purpose FROM USERS WHERE _id = '" + UserID + "'";
                    Cursor c3 = db.rawQuery(check_akk_string, null);
                    String check_purp = null;
                    while (c3.moveToNext())  {
                        Purpose = c3.getString(c3.getColumnIndexOrThrow("Purpose"));
                        check_purp = Purpose;
                    }
                    try {
                        switch (check_purp) {
                            case "1":
                                choice(v, FindUser(UserID));
                                break;
                            case "2": {
                                choice2(v, UserID);
                                break;
                            }
                            case "3": {
                                choice3(v, UserID);
                                break;
                            }
                        }
                    }
                    catch (NullPointerException e) {
                        logbtn(v, UserID);
                    }
                }
            }
        });
    }

    public User FindUser(String UserID) {
        User user = new User();
        user.UserID = Integer.valueOf(UserID);
        user.db = db;
        user.setSalary();
        user.setFifty();
        user.setTwenty();
        return (user);
    }

    public void logbtn(View v, String  UserID) {
        User user = new User();
        user.UserID = Integer.valueOf(UserID);
        Intent intent = new Intent(this, methodsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    public void choice(View v, User user) {
        System.out.println(UserID + "это юзер айди из логин активити");
        Intent intent = new Intent(this, fifty_twenty_thirty_method.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    public void choice2(View v, String  UserID) {
        User user = new User();
        user.UserID = Integer.valueOf(UserID);
        Intent intent = new Intent(this, four_converts_method.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    public void choice3(View v, String UserID) {
        User user = new User();
        user.UserID = Integer.valueOf(UserID);
        Intent intent = new Intent(this, six_jugs_method.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}