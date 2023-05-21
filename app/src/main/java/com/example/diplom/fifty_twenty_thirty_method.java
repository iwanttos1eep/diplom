package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class fifty_twenty_thirty_method extends AppCompatActivity implements MyDialogFragmentListener  {
    Button advicebtn;
    Button salarybtn;
    Button fiftybtn;
    Button twentybtn;
    Button thirtybtn;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.get("user");
        System.out.println(user.UserID + " Это айди юзера из фифти хуифти");

        setContentView(R.layout.activity_fifty_twenty_thirty_method);
        advicebtn = findViewById(R.id.buttnadvice);
        salarybtn = findViewById(R.id.dohodbtn);
        fiftybtn = findViewById(R.id.fifty_percent);
        twentybtn = findViewById(R.id.twenty_percent);
        thirtybtn = findViewById(R.id.thirty_percent);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();

            //если в таблице нет _id, то вставить новый
            if (user.UserID == null) {
                String insert_id = "INSERT INTO fifty_twenty_thirty (_id) VALUES ('" + user.UserID + "')";
                db.execSQL(insert_id);
            }

        if (user.getSalary() == null) {
            salarybtn.setText("0");
        }

        else {
            salarybtn.setText(String.valueOf(user.getSalary()));
            fiftybtn.setText(String.valueOf(user.getFifty()));
            twentybtn.setText(String.valueOf(user.getTwenty()));
            //thirtybtn.setText(String.valueOf(user.getThirty()));
        }

        advicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knowDialog(v);
            }

        });
        salarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salaryDialog(v);
            }
        });
    }

    public void onReturnSalary(Integer Salary) {
        salarybtn.setText(String.valueOf(Salary));
    }
    public void onReturnFifty(Integer Fifty) {
        fiftybtn.setText(String.valueOf(Fifty));
    }
    public void onReturnTwenty(Integer Twenty) {
        twentybtn.setText(String.valueOf(Twenty));
    }
    public void onReturnThirty(Integer Thirty) {
        //thirtybtn.setText(String.valueOf(Thirty));
    }

    private void knowDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        AdviceDialog adviceDialog = AdviceDialog.newInstance(user);
        adviceDialog.show(fm, "dialog_advice");
    }

    private void salaryDialog (View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        SalaryDialog salaryDialog = SalaryDialog.newInstance(user);
        salaryDialog.show(fm, "dialog_salary");
    }

}