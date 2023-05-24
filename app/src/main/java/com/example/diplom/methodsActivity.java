package com.example.diplom;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class methodsActivity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Button choice_btn;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getIntent().getExtras();
        User user = (User) arguments.get("user");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods);
        choice_btn = findViewById(R.id.choice);
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.firstmethod:
                        String insert_string = "UPDATE USERS SET Purpose = 1 WHERE _id = '" + user.UserID + "'";
                        db.execSQL(insert_string);
                        break;
                    case R.id.secondmethod:
                        String insert_string2 = "UPDATE USERS SET Purpose = 2 WHERE _id = '" + user.UserID + "'";
                        db.execSQL(insert_string2);
                        break;
                    case R.id.thirdmethod:
                        String insert_string3 = "UPDATE USERS SET Purpose = 3 WHERE _id = '" + user.UserID + "'";
                        db.execSQL(insert_string3);
                        break;

                    default:
                        break;
                }
            }
        });

        choice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String this_string = "SELECT Purpose FROM USERS WHERE _id = '" + user.UserID + "'";
                Cursor c = db.rawQuery(this_string, null);
                String purpID = null;
                while (c.moveToNext()) {
                    purpID = c.getString(c.getColumnIndexOrThrow("Purpose"));
                    //System.out.println(purpID);
                }
                try {
                    switch (purpID) {
                        case "1":
                            choice(v, user);
                            break;
                        case "2":
                            choice2(v, user);
                            break;
                        case "3":
                            choice3(v, user);
                            break;
                    }
                }
                catch (NullPointerException e) {
                    Toast toast = Toast.makeText(methodsActivity.this, "Вы не выбрали метод", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }



    public void showDialog(View v) {
        AlertDialog dialog = CustomDialogFragment.getDialog(this, CustomDialogFragment.DIALOG_FIRST_ID);
        dialog.show();
    }

    public void showDialog2(View v) {
        AlertDialog dialog = CustomDialogFragment.getDialog(this, CustomDialogFragment.DIALOG_SECOND_ID);
        dialog.show();
    }
    public void showDialog3(View v) {
        AlertDialog dialog = CustomDialogFragment.getDialog(this, CustomDialogFragment.DIALOG_THIRD_ID);
        dialog.show();
    }

    public void choice(View v, User user) {
        Intent intent = new Intent(this, fifty_twenty_thirty_method.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    public void choice2(View v, User user) {
        Intent intent = new Intent(this, four_converts_method.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    public void choice3(View v, User user) {
        Intent intent = new Intent(this, six_jugs_method.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}