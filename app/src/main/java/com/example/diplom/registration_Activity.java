package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class registration_Activity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Button reg_button;
    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reg_button = findViewById(R.id.registr);
        TextInputEditText email_text = findViewById(R.id.Email);
        TextInputEditText password_text = findViewById(R.id.Password);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email_string = String.valueOf(email_text.getText());
                String Password_string = String.valueOf(password_text.getText());

                if (!Email_string.equals("") || !Password_string.equals("")) {

                    System.out.println(Email_string);
                    System.out.println(Password_string);
                    String insert_string = "INSERT INTO USERS (Name, Password) VALUES ('" + Email_string + "','" + Password_string + "')";

                    try {
                        db.execSQL(insert_string);
                        //System.out.println("Всё сработало");
                        String this_string = "SELECT _id FROM USERS WHERE Name='" + Email_string + "' AND Password = '" + Password_string + "'";
                        Cursor c2 = db.rawQuery(this_string, null);
                        while (c2.moveToNext()) {
                            UserID = c2.getString(c2.getColumnIndexOrThrow("_id"));
                        }
                        registr(v, UserID);

                    } catch (SQLiteConstraintException e) {
                        //System.out.println("такой мейл уже существует");
                        Toast toast = Toast.makeText(registration_Activity.this, "Такой email уже существует", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else {
                    Toast toast = Toast.makeText(registration_Activity.this, "Заполнены не все поля", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
    public void registr (View v, String UserID) {
        User user = new User();
        user.UserID = Integer.valueOf(UserID);
        Intent intent = new Intent(this, methodsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}