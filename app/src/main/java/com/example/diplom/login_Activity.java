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

public class login_Activity extends AppCompatActivity {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Button log_button;

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
            String check_string = "SELECT * FROM USERS WHERE Name='" + username_text + "' AND Password = '"+ password_text +"'";
            Cursor c = db.rawQuery(check_string, null);
            //System.out.println(c.getCount());
            if (c.getCount()==0){
                System.out.println("Такого пользователя не существует!");
                Toast toast = Toast.makeText(login_Activity.this, "Такого пользователя не существует", Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                //Intent intent = new Intent(this, registration_Activity.class);
                //startActivity(intent);
            }
        }
    });
}
}