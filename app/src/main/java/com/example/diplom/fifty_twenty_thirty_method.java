package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fifty_twenty_thirty_method extends AppCompatActivity {
    Button advicebtn;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifty_twenty_thirty_method);
        advicebtn = findViewById(R.id.buttnadvice);
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();


        advicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knowDialog(v);
            }

        });
    }

    private void knowDialog(View v) {
        User user = new User();
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        AdviceDialog adviceDialog = AdviceDialog.newInstance(user);
        adviceDialog.show(fm, "dialog_advice");
    }

}

