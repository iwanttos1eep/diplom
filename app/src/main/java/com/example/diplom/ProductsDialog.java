package com.example.diplom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;

public class ProductsDialog extends BottomSheetDialogFragment {

    Button confirmbtn, cancelbtn;

    public static ProductsDialog newInstance(Serializable user) {
        ProductsDialog fragment = new ProductsDialog();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.products_dialog, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Bundle arguments = getArguments();
        User user = (User) arguments.get("user");
        confirmbtn = v.findViewById(R.id.buttonconfirm4);
        cancelbtn = v.findViewById(R.id.buttoncancel4);
        EditText products_text = v.findViewById(R.id.productsedit);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorListener activity = (CalculatorListener) getActivity();
                String products_string = String.valueOf(products_text.getText());
                System.out.println(products_string + " это число продуктов");
                try {
                    if (Integer.parseInt(products_string) > 100000) {
                        Toast toast = Toast.makeText(getActivity(), "Вы ввели слишком большое число", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        if (user.getTogether() == null) {
                            String update_together = "INSERT INTO Calculator (Together) VALUES ('" + products_string + "')";
                            user.db.execSQL(update_together);

                            String update_products = "UPDATE Calculator SET Products = '" + products_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_products);

                        } else {
                            if (user.getProducts() == null) {
                                String update_products = "UPDATE Calculator SET Products =  '" + products_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_products);
                            } else {
                                String update_products = "UPDATE Calculator SET Products = Products + '" + products_string + "' WHERE _id = '" + user.UserID + "'";
                                user.db.execSQL(update_products);
                            }
                            String update_together = "UPDATE Calculator SET Together = Together + '" + products_string + "' WHERE _id = '" + user.UserID + "'";
                            user.db.execSQL(update_together);
                        }


                        String select_products = "SELECT Products FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c3 = user.db.rawQuery(select_products, null);
                        while (c3.moveToNext()) {
                            user.Products = Integer.parseInt(String.valueOf(c3.getInt(c3.getColumnIndexOrThrow("Products"))));
                        }

                        String select_together = "SELECT Together FROM Calculator WHERE _id = '" + user.UserID + "'";
                        Cursor c2 = user.db.rawQuery(select_together, null);
                        while (c2.moveToNext()) {
                            user.Together = Integer.parseInt(String.valueOf(c2.getInt(c2.getColumnIndexOrThrow("Together"))));
                        }
                        System.out.println(user.Products + " это прокудты и айди юзера в диалоге продуктов " + user.Together);

                        activity.onReturnProducts(user.Products);
                        activity.onReturnTogether(user.Together);
                        dismiss();

                    }
                }
                    catch (NumberFormatException e) {
                    activity.onReturnNullPointer();
                    }
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}