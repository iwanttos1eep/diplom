package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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
        EditText password_text = findViewById(R.id.Password);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email_string = String.valueOf(email_text.getText());
                String Password_string = String.valueOf(password_text.getText());
                System.out.println(Email_string);
                System.out.println(Password_string);
                String insert_string = "INSERT INTO USERS (Name, Password) VALUES ('" + Email_string + "','" + Password_string + "')";

                try {
                    db.execSQL(insert_string);
                    System.out.println("Всё сработало");
                }
                catch (SQLiteConstraintException e) {
                    System.out.println("такой мейл уже существует");
                    Toast toast = Toast.makeText(registration_Activity.this, "Такой email уже существует", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

}