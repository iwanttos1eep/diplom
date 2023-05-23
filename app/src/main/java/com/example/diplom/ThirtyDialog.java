package com.example.diplom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;

public class ThirtyDialog extends BottomSheetDialogFragment {

    Button okbutton1;
    Button buttoncancel1;

    public static ThirtyDialog newInstance(Serializable user) {
        ThirtyDialog fragment = new ThirtyDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.thirty_dialog, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        okbutton1 = v.findViewById(R.id.buttonconfirm2);
        buttoncancel1 = v.findViewById(R.id.buttoncancel2);
        EditText thirty_text = v.findViewById(R.id.thirtyedit);

        okbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                String thirty_string = String.valueOf(thirty_text.getText());

                int a = Integer.parseInt(thirty_string);
                if (a > user.Thirty) {
                    activity.onReturnText();
                }

                else {
                    String fifty_update = "UPDATE fifty_twenty_thirty SET Thirty = Thirty - '" + thirty_string + "' WHERE _id = '" + user.UserID + "'";
                    user.db.execSQL(fifty_update);

                    String select_fifty2 = "SELECT Thirty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                    Cursor c3 = user.db.rawQuery(select_fifty2, null);
                    while (c3.moveToNext()) {
                        user.Thirty = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Thirty"))));
                    }
                }

                activity.onReturnThirty(user.Thirty);
                dismiss();
            }
        });
        buttoncancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}