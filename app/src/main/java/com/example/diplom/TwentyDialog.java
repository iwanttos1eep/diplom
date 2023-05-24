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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;

public class TwentyDialog extends BottomSheetDialogFragment {
    Button okbutton3;
    Button buttoncancel3;

    public static TwentyDialog newInstance(Serializable user) {
        TwentyDialog fragment = new TwentyDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.twenty_dialog, container, false);
        return v;
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        okbutton3 = v.findViewById(R.id.buttonconfirm3);
        buttoncancel3 = v.findViewById(R.id.buttoncancel3);
        EditText twenty_text = v.findViewById(R.id.twentyedit);

        okbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                String twenty_string = String.valueOf(twenty_text.getText());
                try {
                    int a = Integer.parseInt(twenty_string);
                    if (a > user.Twenty) {
                        activity.onReturnText();
                    }
                    else {
                        String twenty_update = "UPDATE fifty_twenty_thirty SET Twenty = Twenty - '" + twenty_string + "' WHERE _id = '" + user.UserID + "'";
                        user.db.execSQL(twenty_update);

                        String select_twenty = "SELECT Twenty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_twenty, null);
                        while (c3.moveToNext()) {
                            user.Twenty = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Twenty"))));
                        }
                        activity.onReturnTwenty(user.Twenty);
                        dismiss();
                    }
                }
                catch (NumberFormatException e) {
                    activity.onReturnNull();
                }
            }
        });
        buttoncancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}