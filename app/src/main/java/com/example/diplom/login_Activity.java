package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class login_Activity extends AppCompatActivity {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Button log_button;
    String UserID;
    String Purpose;

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
                        if (check_purp.equals("1")) {
                            choice(v, UserID);
                        } else if (check_purp.equals("2")) {
                            Toast toast = Toast.makeText(login_Activity.this, "Кабинет пользователя в процессе разработки!", Toast.LENGTH_LONG);
                            toast.show();
                        } else if (check_purp.equals("3")) {
                            Toast toast = Toast.makeText(login_Activity.this, "Кабинет пользователя в процессе разработки!", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    catch (NullPointerException e) {
                        logbtn(v, UserID);
                    }
                }
            }
        });
    }
    public void logbtn(View v, String UserID) {
        Intent intent = new Intent(this, methodsActivity.class);
        intent.putExtra("user", UserID);
        startActivity(intent);
    }
    public void choice(View v, String UserID) {
        Intent intent = new Intent(this, fifty_twenty_thirty_method.class);
        intent.putExtra("user", UserID);
        startActivity(intent);
    }
}