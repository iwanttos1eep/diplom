package com.example.diplom;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class AdviceDialog extends DialogFragment {
     SQLiteDatabase db;
    TextView textAdvice;

    public AdviceDialog() {

    }

    public static AdviceDialog newInstance(Serializable user) {
        AdviceDialog adviceDialog = new AdviceDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        adviceDialog.setArguments(args);
        return adviceDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_advice, container);
    }


    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        textAdvice = v.findViewById(R.id.textadvice);
        textAdvice.setText(user.getAdvice());

    }
}