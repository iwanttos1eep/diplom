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

public class ClothesDialog extends BottomSheetDialogFragment {
    Button confirmbtn, cancelbtn;

    public static ClothesDialog newInstance(Serializable user) {
        ClothesDialog fragment = new ClothesDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.clothes_dialog, container, false);
        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        confirmbtn = v.findViewById(R.id.buttonconfirm8);
        cancelbtn = v.findViewById(R.id.buttoncancel8);
        EditText clothes_text = v.findViewById(R.id.clothesedit);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorListener activity = (CalculatorListener) getActivity();
                String clothes_string = String.valueOf(clothes_text.getText());
                try {
                    if (Integer.parseInt(clothes_string) > 100000) {
                        Toast toast = Toast.makeText(getActivity(), "Вы ввели слишком большое число", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        if (user.getTogether() == null) {
                            String update_together = "INSERT INTO Calculator (Together) VALUES ('" + clothes_string + "')";
                            user.db.execSQL(update_together);

                            String update_clothes = "UPDATE Calculator SET Clothes = '" + clothes_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_clothes);

                        }
                        else {
                            if (user.getClothes() == null) {
                                String update_clothes = "UPDATE Calculator SET Clothes =  '" + clothes_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_clothes);
                            } else {
                                String update_clothes = "UPDATE Calculator SET Clothes = Clothes + '" + clothes_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_clothes);
                            }
                            String update_together = "UPDATE Calculator SET Together = Together + '" + clothes_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_together);
                        }
                        String select_clothes = "SELECT Clothes FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_clothes, null);
                        while (c3.moveToNext()) {
                            user.Clothes = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Clothes"))));
                        }

                        String select_together = "SELECT Together FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c2 = user.db.rawQuery(select_together, null);
                        while (c2.moveToNext()) {
                            user.Together = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Together"))));
                        }
                        activity.onReturn();
                        activity.onReturnClothes(user.Clothes);
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
