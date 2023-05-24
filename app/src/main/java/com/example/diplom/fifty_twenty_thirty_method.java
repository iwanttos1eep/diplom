package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fifty_twenty_thirty_method extends AppCompatActivity implements MyDialogFragmentListener  {
    Button advicebtn;
    Button salarybtn;
    Button fiftybtn;
    Button twentybtn;
    Button thirtybtn;
    Button updatebtn;
    TextView update;
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
        updatebtn = findViewById(R.id.updatebutton);
        update = findViewById(R.id.savingmoney);

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
            thirtybtn.setText(String.valueOf(user.getThirty()));
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

        fiftybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiftyDialog(v);
            }
        });
        thirtybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            thirtyDialog(v);
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog(v);
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
        thirtybtn.setText(String.valueOf(Thirty));
    }

    @Override
    public void onReturnText() {
        Toast toast = Toast.makeText(fifty_twenty_thirty_method.this, "Число, которое вы ввели оказалось больше, чем у вас осталось средств", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onReturnNull() {
        Toast toast = Toast.makeText(fifty_twenty_thirty_method.this, "Вы ничего не ввели", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onReturnUpdate(Integer Update) {
        update.setText("Вы сэкономили: " + Update + " ₽    ");
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

    private void fiftyDialog (View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        FiftyDialog fiftyDialog = FiftyDialog.newInstance(user);
        fiftyDialog.show(fm, "activity_fifty_dialog");
    }

    private void thirtyDialog (View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        ThirtyDialog thirtyDialog = ThirtyDialog.newInstance(user);
        thirtyDialog.show(fm, "thirty_dialog");
    }

    private void updateDialog (View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        ReloadDialog reloadDialog = ReloadDialog.newInstance(user);
        reloadDialog.show(fm, "reload_dialog");
    }
}