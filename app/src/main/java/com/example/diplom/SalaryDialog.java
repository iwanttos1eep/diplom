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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;

public class SalaryDialog extends BottomSheetDialogFragment {
    Button buttonok;
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
        buttonok = v.findViewById(R.id.buttonconfirm);
        cancelbutton = v.findViewById(R.id.buttoncancel);
        EditText salary_text = v.findViewById(R.id.salaryedit);

        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                String salary_string = String.valueOf(salary_text.getText());
                try {
                    if (Integer.parseInt(salary_string) > 1000000) {
                        Toast toast = Toast.makeText(getActivity(), "Вы ввели слишком большое число", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        //проверка значений в Salary, если там есть значение, то обновлять, если null, то вставлять.
                        if (user.getSalary() == null) {
                            String insert_string = "INSERT INTO fifty_twenty_thirty (Salary) VALUES ('" + salary_string + "')";
                            user.db.execSQL(insert_string);
                            String update_fifty = "UPDATE fifty_twenty_thirty SET Fifty = '" + salary_string + "' * '" + 0.5 + "'  WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_fifty);
                            String update_twenty = "UPDATE fifty_twenty_thirty SET Twenty = '" + salary_string + "' * '" + 0.2 + "'  WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_twenty);
                            String update_thirty = "UPDATE fifty_twenty_thirty SET Thirty =  '" + salary_string + "' * '" + 0.3 + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_thirty);

                        } else {
                            String update_string = "UPDATE fifty_twenty_thirty SET Salary = Salary + '" + salary_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_string);
                            String update_fifty = "UPDATE fifty_twenty_thirty SET Fifty = '" + salary_string + "' * '" + 0.5 + "' + Fifty WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_fifty);
                            String update_twenty = "UPDATE fifty_twenty_thirty SET Twenty = '" + salary_string + "' * '" + 0.2 + "' + Twenty WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_twenty);
                            String update_thirty = "UPDATE fifty_twenty_thirty SET Thirty = '" + salary_string + "' * '" + 0.3 + "' + Thirty WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_thirty);
                        }

                        //передаём число в кнопку дохода
                        String select_string = "SELECT Salary FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                        Cursor c = user.db.rawQuery(select_string, null);
                        while (c.moveToNext()) {
                            user.Salary = Integer.valueOf(c.getString(c.getColumnIndexOrThrow("Salary")));
                        }


                        //передаём число в кнопку 50%
                        String select_fifty = "SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                        Cursor c2 = user.db.rawQuery(select_fifty, null);
                        while (c2.moveToNext()) {
                            user.Fifty = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Fifty"))));
                            System.out.println(user.Fifty + "это число 50%");
                        }

                        //передаём число в кнопку 20%
                        String select_twenty = "SELECT Twenty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_twenty, null);
                        while (c3.moveToNext()) {
                            user.Twenty = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Twenty"))));
                            System.out.println(user.Twenty + "это число 20%");
                        }

                        //передаём число в кнопку 30%
                        String select_thirty = "SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                        Cursor c4 = user.db.rawQuery(select_thirty, null);
                        while (c4.moveToNext()) {
                            user.Thirty = Integer.parseInt(String.valueOf(c4.getInt(c4.getColumnIndexOrThrow("Thirty"))));
                        }

                        activity.onReturnSalary(user.Salary);
                        activity.onReturnFifty(user.Fifty);
                        activity.onReturnTwenty(user.Twenty);
                        activity.onReturnThirty(user.Thirty);
                        dismiss();
                    }
                }
                catch (NumberFormatException e) {
                    activity.onReturnNull();
                }
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