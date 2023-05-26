package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity implements CalculatorListener{
    User user;
    Button products, transport, food, entertainment, mobile, clothes;
    TextView togetherText;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    ArrayList barArrayList;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        user = (User) arguments.get("user");
        System.out.println(user.UserID + " Это айди юзера калькулятора");
        setContentView(R.layout.activity_calculator);

        products = findViewById(R.id.products);
        transport = findViewById(R.id.transport);
        food = findViewById(R.id.restraunt);
        entertainment = findViewById(R.id.entertainment);
        mobile = findViewById(R.id.mobile);
        clothes = findViewById(R.id.clothes);
        togetherText = findViewById(R.id.together);
        barChart = findViewById(R.id.idBarChart);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();


        if (user.getTogether()== null) {
            togetherText.setText("0 ₽");
        }
        else {
            products.setText(String.valueOf(user.getProducts()));
            transport.setText(String.valueOf(user.getTransport()));
            food.setText(String.valueOf(user.getFood()));
            entertainment.setText(String.valueOf(user.getEntertainment()));
            mobile.setText(String.valueOf(user.getMobile()));
            clothes.setText(String.valueOf(user.getClothes()));
            togetherText.setText(String.valueOf(user.getTogether() + " ₽"));
        }

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDialog(v);
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transportDialog(v);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodDialog(v);
            }
        });
    }

    @Override
    public void onReturnNullPointer() {
        Toast toast = Toast.makeText(Calculator.this, "Вы ничего не ввели", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onReturnProducts(Integer Products) {
        products.setText(String.valueOf(Products));
    }

    @Override
    public void onReturnTogether(Integer Together) {
        togetherText.setText(String.valueOf(Together) + " ₽");
    }

    @Override
    public void onReturnTransport(Integer Transport) {
        transport.setText(String.valueOf(Transport));
    }

    @Override
    public void onReturnFood(Integer Food) {
        food.setText(String.valueOf(Food));
    }

    public void productsDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        ProductsDialog productsDialog = ProductsDialog.newInstance(user);
        productsDialog.show(fm, "products_dialog");
    }

    public void transportDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        TransportDialog transportDialog = TransportDialog.newInstance(user);
        transportDialog.show(fm, "transport_dialog");
    }
    public void foodDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        FoodDialog foodDialog = FoodDialog.newInstance(user);
        foodDialog.show(fm, "transport_dialog");
    }
}
