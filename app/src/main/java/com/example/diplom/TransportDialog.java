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

public class TransportDialog extends BottomSheetDialogFragment {

    Button confirmbtn, cancelbtn;


    public static TransportDialog newInstance(Serializable user) {
        TransportDialog fragment = new TransportDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transport_dialog, container, false);
        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        confirmbtn = v.findViewById(R.id.buttonconfirm5);
        cancelbtn = v.findViewById(R.id.buttoncancel5);
        EditText transport_text = v.findViewById(R.id.transportedit);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorListener activity = (CalculatorListener) getActivity();
                String transport_string = String.valueOf(transport_text.getText());
                try {
                    if (Integer.parseInt(transport_string) > 100000) {
                        Toast toast = Toast.makeText(getActivity(), "Вы ввели слишком большое число", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else {
                        if (user.getTogether() == null) {
                            String update_together = "INSERT INTO Calculator (Together) VALUES ('" + transport_string + "')";
                            user.db.execSQL(update_together);

                            String update_transport = "UPDATE Calculator SET Transport = '" + transport_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_transport);

                        }
                        else {
                            if (user.getTransport() == null) {
                                String update_transport = "UPDATE Calculator SET Transport =  '" + transport_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_transport);
                            } else {
                                String update_transport = "UPDATE Calculator SET Transport = Transport + '" + transport_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_transport);
                            }
                            String update_together = "UPDATE Calculator SET Together = Together + '" + transport_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_together);
                        }
                        String select_transport = "SELECT Transport FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_transport, null);
                        while (c3.moveToNext()) {
                            user.Transport = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Transport"))));
                        }

                        String select_together = "SELECT Together FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c2 = user.db.rawQuery(select_together, null);
                        while (c2.moveToNext()) {
                            user.Together = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Together"))));
                        }
                        activity.onReturnTransport(user.Transport);
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