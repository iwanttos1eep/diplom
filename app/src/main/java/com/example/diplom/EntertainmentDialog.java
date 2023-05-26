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

public class EntertainmentDialog extends BottomSheetDialogFragment {

    Button confirmbtn, cancelbtn;


    public static EntertainmentDialog newInstance(Serializable user) {
        EntertainmentDialog fragment = new EntertainmentDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.entertainment_dialog, container, false);
        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        confirmbtn = v.findViewById(R.id.buttonconfirm7);
        cancelbtn = v.findViewById(R.id.buttoncancel7);
        EditText entertainment_text = v.findViewById(R.id.entertainmentedit);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorListener activity = (CalculatorListener) getActivity();
                String entertainment_string = String.valueOf(entertainment_text.getText());
                try {
                    if (Integer.parseInt(entertainment_string) > 100000) {
                        Toast toast = Toast.makeText(getActivity(), "Вы ввели слишком большое число", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        if (user.getTogether() == null) {
                            String update_together = "INSERT INTO Calculator (Together) VALUES ('" + entertainment_string + "')";
                            user.db.execSQL(update_together);

                            String update_entertainment = "UPDATE Calculator SET Entertainment = '" + entertainment_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_entertainment);

                        }
                        else {
                            if (user.getEntertainment() == null) {
                                String update_entertainment = "UPDATE Calculator SET Entertainment =  '" + entertainment_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_entertainment);
                            } else {
                                String update_entertainment = "UPDATE Calculator SET Entertainment = Entertainment + '" + entertainment_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_entertainment);
                            }
                            String update_together = "UPDATE Calculator SET Together = Together + '" + entertainment_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_together);
                        }
                        String select_entertainment = "SELECT Entertainment FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_entertainment, null);
                        while (c3.moveToNext()) {
                            user.Entertainment = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Entertainment"))));
                        }

                        String select_together = "SELECT Together FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c2 = user.db.rawQuery(select_together, null);
                        while (c2.moveToNext()) {
                            user.Together = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Together"))));
                        }
                        activity.onReturn();
                        activity.onReturnEntertainment(user.Entertainment);
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