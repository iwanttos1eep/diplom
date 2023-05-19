package com.example.diplom;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class SalaryDialog extends DialogFragment {
    Button buttonok;
    Button dohod;
    Button cancelbutton;


    public static SalaryDialog newInstance(Serializable user) {
        SalaryDialog fragment = new SalaryDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_salary, container, false);

        return v;
    }


    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        System.out.println(user.UserID + " Это айди юзера из салари диалога");
        dohod = v.findViewById(R.id.dohodbtn);
        buttonok = v.findViewById(R.id.buttonconfirm);
        cancelbutton = v.findViewById(R.id.buttoncancel);
        EditText salary_text = v.findViewById(R.id.salaryedit);

        //проверкf значений в Salary, если там есть значение, то обновлять, если null, то вставлять.
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salary_string = String.valueOf(salary_text.getText());

                if (user.getSalary() == null) {
                    String insert_string = "INSERT INTO fifty_twenty_thirty (Salary) VALUES ('" + salary_string + "')";
                    user.db.execSQL(insert_string);
                }
                else {
                    String update_string = "UPDATE fifty_twenty_thirty SET Salary = Salary + '" + salary_string + "' WHERE _id = '" + user.UserID + "'";
                    user.db.execSQL(update_string);
                }

                String select_string ="SELECT Salary FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c = user.db.rawQuery(select_string, null);
                while(c.moveToNext()) {
                    user.Salary =Integer.valueOf(c.getString(c.getColumnIndexOrThrow("Salary")));
                }
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                activity.onReturnValue(user.Salary);
                dismiss();
            }
        });
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}