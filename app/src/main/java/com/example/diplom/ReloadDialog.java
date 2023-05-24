package com.example.diplom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;

public class ReloadDialog extends BottomSheetDialogFragment {

Button distributebtn;
Button updatebtn;

    public static ReloadDialog newInstance(Serializable user) {
        ReloadDialog fragment = new ReloadDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reload_dialog, container, false);
        return v;
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        distributebtn = v.findViewById(R.id.distribute);
        updatebtn = v.findViewById(R.id.update);

        distributebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();

                String select_fifty = "SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c3 = user.db.rawQuery(select_fifty, null);
                int a = 0;
                while (c3.moveToNext()) {
                    a = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Fifty"))));
                }
                String select_thirty = "SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c2 = user.db.rawQuery(select_thirty, null);
                int b = 0;
                while (c2.moveToNext()) {
                    b = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Thirty"))));
                }
                int c = a + b;

                String update_fifty = "UPDATE fifty_twenty_thirty SET Fifty = '" + c + "' * '" + 0.5 + "'  WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_fifty);
                String update_twenty = "UPDATE fifty_twenty_thirty SET Twenty = Twenty + '" + c + "' * '" + 0.2 + "'  WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_twenty);
                String update_thirty = "UPDATE fifty_twenty_thirty SET Thirty = '" + c + "' * '" + 0.3 + "'  WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_thirty);
                String update_salary = "UPDATE fifty_twenty_thirty SET Salary = 0 WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_salary);

                //передаём число в кнопку 50%
                String select2_fifty ="SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c4 = user.db.rawQuery(select2_fifty, null);
                while(c4.moveToNext()) {
                    user.Fifty = Integer.parseInt(String.valueOf(c4.getInt(c4.getColumnIndexOrThrow("Fifty"))));
                    System.out.println(user.Fifty + "это число 50%");
                }

                //передаём число в кнопку 20%
                String select_twenty ="SELECT Twenty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c5 = user.db.rawQuery(select_twenty, null);
                while(c5.moveToNext()) {
                    user.Twenty = Integer.parseInt(String.valueOf(c5.getInt(c5.getColumnIndexOrThrow("Twenty"))));
                    System.out.println(user.Twenty + "это число 20%");
                }

                //передаём число в кнопку 30%
                String select2_thirty ="SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c6 = user.db.rawQuery(select2_thirty, null);
                while(c6.moveToNext()) {
                    user.Thirty = Integer.parseInt(String.valueOf(c6.getInt(c6.getColumnIndexOrThrow("Thirty"))));
                }
                //передаём число в кнопку дохода
                String select_salary ="SELECT Salary FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c7 = user.db.rawQuery(select_salary, null);
                while(c7.moveToNext()) {
                    user.Salary = Integer.parseInt(String.valueOf(c7.getInt(c7.getColumnIndexOrThrow("Salary"))));
                }

                activity.onReturnSalary(user.Salary);
                activity.onReturnFifty(user.Fifty);
                activity.onReturnTwenty(user.Twenty);
                activity.onReturnThirty(user.Thirty);
                dismiss();
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                String select_fifty = "SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c = user.db.rawQuery(select_fifty, null);
                int a = 0;
                while (c.moveToNext()) {
                    a = Integer.parseInt(String.valueOf(c.getInt(c.getColumnIndexOrThrow("Fifty"))));
                }
                String select_thirty = "SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c2 = user.db.rawQuery(select_thirty, null);
                int b = 0;
                while (c2.moveToNext()) {
                    b = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Thirty"))));
                }
                String select_twenty = "SELECT Twenty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c3 = user.db.rawQuery(select_twenty, null);
                int d = 0;
                while (c3.moveToNext()) {
                    d = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Twenty"))));
                }
                int e = a + b + d;

                String update_fifty = "UPDATE fifty_twenty_thirty SET Fifty = 0  WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_fifty);
                String update_twenty = "UPDATE fifty_twenty_thirty SET Twenty = 0  WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_twenty);
                String update_thirty = "UPDATE fifty_twenty_thirty SET Thirty = 0  WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_thirty);
                String update_salary = "UPDATE fifty_twenty_thirty SET Salary = 0 WHERE _id = '" + user.UserID + "'";
                user.db.execSQL(update_salary);

                //передаём число в кнопку 50%
                String select2_fifty ="SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c4 = user.db.rawQuery(select2_fifty, null);
                while(c4.moveToNext()) {
                    user.Fifty = Integer.parseInt(String.valueOf(c4.getInt(c4.getColumnIndexOrThrow("Fifty"))));
                    System.out.println(user.Fifty + "это число 50%");
                }

                //передаём число в кнопку 20%
                String select2_twenty ="SELECT Twenty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c5 = user.db.rawQuery(select2_twenty, null);
                while(c5.moveToNext()) {
                    user.Twenty = Integer.parseInt(String.valueOf(c5.getInt(c5.getColumnIndexOrThrow("Twenty"))));
                    System.out.println(user.Twenty + "это число 20%");
                }

                //передаём число в кнопку 30%
                String select2_thirty ="SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c6 = user.db.rawQuery(select2_thirty, null);
                while(c6.moveToNext()) {
                    user.Thirty = Integer.parseInt(String.valueOf(c6.getInt(c6.getColumnIndexOrThrow("Thirty"))));
                }
                //передаём число в кнопку дохода
                String select_salary ="SELECT Salary FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                Cursor c7 = user.db.rawQuery(select_salary, null);
                while(c7.moveToNext()) {
                    user.Salary = Integer.parseInt(String.valueOf(c7.getInt(c7.getColumnIndexOrThrow("Salary"))));
                }

                activity.onReturnSalary(user.Salary);
                activity.onReturnFifty(user.Fifty);
                activity.onReturnTwenty(user.Twenty);
                activity.onReturnThirty(user.Thirty);
                activity.onReturnUpdate(e);
                dismiss();
            }
        });
    }
}