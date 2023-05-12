package com.example.diplom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment  {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    static String description;
    static final int DIALOG_FIRST_ID = 1;
    static final int DIALOG_SECOND_ID = 2;
    static final int DIALOG_THIRD_ID = 3;

    static final int AdviceID = 4;




    public void Purpose_Description() {
         sqlHelper = new DatabaseHelper(getActivity());
         db = sqlHelper.open();

         Cursor cursor_description1 = db.rawQuery("SELECT Description FROM Purpose WHERE _idPurpose ='"+ "'", null);
        while (cursor_description1.moveToNext()) {
            description = cursor_description1.getString(cursor_description1.getColumnIndexOrThrow("Description"));
        }
    }

    public static AlertDialog getDialog(Activity activity, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        switch (id) {
            case DIALOG_FIRST_ID: // Диалоговое окно About
                return builder
                        .setTitle("50-20-30")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage(description)
                        .setPositiveButton("OK", null)
                        .create();
            case DIALOG_SECOND_ID:
                return builder
                        .setTitle("4 конверта")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage("Для закрытия окна нажмите ОК")
                        .setPositiveButton("OK", null)
                        .create();
            case DIALOG_THIRD_ID:
                return builder
                        .setTitle("6 кувшинов")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage("Для закрытия окна нажмите ОК")
                        .setPositiveButton("OK", null)
                        .create();
            default:
                return null;
        }
    }
}