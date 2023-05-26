package com.example.diplom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;

public class OtherDialog extends BottomSheetDialogFragment {

    Button confirmbtn, cancelbtn;

    public static OtherDialog newInstance(Serializable user) {
        OtherDialog fragment = new OtherDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.other_dialog, container, false);
        return v;
    }
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        confirmbtn = v.findViewById(R.id.buttonconfirm9);
        cancelbtn = v.findViewById(R.id.buttoncancel9);
        EditText other_text = v.findViewById(R.id.otheredit);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorListener activity = (CalculatorListener) getActivity();
                String other_string = String.valueOf(other_text.getText());
                try {
                    if (Integer.parseInt(other_string) > 100000) {
                        Toast toast = Toast.makeText(getActivity(), "Вы ввели слишком большое число", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        if (user.getTogether() == null) {
                            String update_together = "INSERT INTO Calculator (Together) VALUES ('" + other_string + "')";
                            user.db.execSQL(update_together);

                            String update_other = "UPDATE Calculator SET Other = '" + other_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_other);

                        }
                        else {
                            if (user.getOther() == null) {
                                String update_other = "UPDATE Calculator SET Other =  '" + other_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_other);
                            } else {
                                String update_other = "UPDATE Calculator SET Other = Other + '" + other_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_other);
                            }
                            String update_together = "UPDATE Calculator SET Together = Together + '" + other_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_together);
                        }
                        String select_other = "SELECT Other FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_other, null);
                        while (c3.moveToNext()) {
                            user.Other = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Other"))));
                        }

                        String select_together = "SELECT Together FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c2 = user.db.rawQuery(select_together, null);
                        while (c2.moveToNext()) {
                            user.Together = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Together"))));
                        }
                        activity.onReturnOther(user.Other);
                        activity.onReturnTogether(user.Together);
                        dismiss();
                    }
                }
                catch (NumberFormatException e) {
                    activity.onReturnNullPointer();
                }
            }
        });
    }
}