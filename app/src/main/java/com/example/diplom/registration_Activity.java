package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.textfield.TextInputLayout;

public class registration_Activity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Button reg_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reg_button = findViewById(R.id.registr);
        EditText email_text = findViewById(R.id.Email);
        String Email_string = String.valueOf(email_text.getText());
        EditText password_text = findViewById(R.id.Password);
        String Password_string = String.valueOf(password_text.getText());

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insert_string = "INSERT INTO USERS (Name, Password) VALUES ('" +Email_string + "','" + Password_string + "')";
                db.execSQL(insert_string);
                reg_button.setText("я работаю");
            }
        });
    }

}