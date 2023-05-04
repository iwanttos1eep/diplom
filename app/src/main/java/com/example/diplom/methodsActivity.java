package com.example.diplom;


import android.app.AlertDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getIntent().getExtras();
        String UserID = (String) arguments.get("user");
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

                        String insert_string = "UPDATE USERS SET Purpose = 1 WHERE _id = '" + UserID + "'";
                        db.execSQL(insert_string);
                        break;
                    case R.id.secondmethod:
                        String insert_string2 = "UPDATE USERS SET Purpose = 2 WHERE _id = '" + UserID + "'";
                        db.execSQL(insert_string2);
                        break;
                    case R.id.thirdmethod:
                        String insert_string3 = "UPDATE USERS SET Purpose = 3 WHERE _id = '" + UserID + "'";
                        db.execSQL(insert_string3);
                        break;

                    default:
                        break;
                }
            }
        });

                /*choice_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }*/
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

    /*private void choice_methods (RadioButton radbtn) {
        RadioButton first_int = findViewById(R.id.firstmethod);
        RadioButton second_int = findViewById(R.id.secondmethod);
        RadioButton third_int = findViewById(R.id.thirdmethod);

        if (radbtn == first_int) {
            String insert_string = "INSERT INTO USERS (Purpose) VALUES ('" + 1 + "')";
            db.execSQL(insert_string);
        }
        else if (radbtn == second_int) {
            String insert_string = "INSERT INTO USERS (Purpose) VALUES ('" + 2 + "')";
            db.execSQL(insert_string);
        }
        if (radbtn == third_int) {
            String insert_string = "INSERT INTO USERS (Purpose) VALUES ('" + 3 + "')";
            db.execSQL(insert_string);
        }
    }*/
}
