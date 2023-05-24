package com.example.diplom;

import androidx.annotation.Nullable;

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

public class FiftyDialog extends BottomSheetDialogFragment {

    Button okbutton;
    Button buttoncancel;

    public static FiftyDialog newInstance(Serializable user) {
        FiftyDialog fragment = new FiftyDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fifty_dialog, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        okbutton = v.findViewById(R.id.buttonconfirm1);
        buttoncancel = v.findViewById(R.id.buttoncancel1);
        EditText fifty_text = v.findViewById(R.id.fiftyedit);

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                String fifty_string = String.valueOf(fifty_text.getText());

                try {
                int a = Integer.parseInt(fifty_string);
                    if (a > user.Fifty) {
                        activity.onReturnText();
                    } else {
                        String fifty_update = "UPDATE fifty_twenty_thirty SET Fifty = Fifty - '" + fifty_string + "' WHERE _id = '" + user.UserID + "'";
                        user.db.execSQL(fifty_update);

                        String select_fifty2 = "SELECT Fifty FROM fifty_twenty_thirty WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_fifty2, null);
                        while (c3.moveToNext()) {
                            user.Fifty = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Fifty"))));
                        }
                        activity.onReturnFifty(user.Fifty);
                        dismiss();
                    }
                }
                catch (NumberFormatException e) {
                    activity.onReturnNull();
                }
            }

        });
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
