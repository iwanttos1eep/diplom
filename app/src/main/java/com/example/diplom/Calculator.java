package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity implements CalculatorListener {
    User user;
    Button products, transport, food, entertainment, other, clothes;
    TextView togetherText;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    ArrayList barArrayList;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntriesArrayList;

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
        clothes = findViewById(R.id.clothes);
        other = findViewById(R.id.others);
        togetherText = findViewById(R.id.together);
        barChart = findViewById(R.id.idBarChart);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.open();


        if (user.getTogether() == null) {
            togetherText.setText("0 ₽");
        } else {
            products.setText(String.valueOf(user.getProducts()));
            transport.setText(String.valueOf(user.getTransport()));
            food.setText(String.valueOf(user.getFood()));
            entertainment.setText(String.valueOf(user.getEntertainment()));
            clothes.setText(String.valueOf(user.getClothes()));
            other.setText(String.valueOf(user.getOther()));
            togetherText.setText(String.valueOf(user.getTogether() + " ₽"));
        }
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "Ваши траты");
        barData = new BarData(barDataSet);
        barChart.setData(barData);


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
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entertainmentDialog(v);
            }
        });
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothesDialog(v);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherDialog(v);
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

    @Override
    public void onReturnEntertainment(Integer Entertainment) {
        entertainment.setText(String.valueOf(Entertainment));
    }

    @Override
    public void onReturnClothes(Integer Clothes) {
        clothes.setText(String.valueOf(Clothes));
    }

    @Override
    public void onReturnOther(Integer Other) {
        other.setText(String.valueOf(Other));
    }

    @Override
    public void onReturn() {
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "Ваши траты");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
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

    public void entertainmentDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        EntertainmentDialog entertainmentDialog = EntertainmentDialog.newInstance(user);
        entertainmentDialog.show(fm, "entertainment_dialog");
    }

    public void clothesDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        ClothesDialog clothesDialog = ClothesDialog.newInstance(user);
        clothesDialog.show(fm, "clothes_dialog");
    }

    public void otherDialog(View v) {
        user.db = db;
        FragmentManager fm = getSupportFragmentManager();
        OtherDialog otherDialog = OtherDialog.newInstance(user);
        otherDialog.show(fm, "clothes_dialog");
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        barEntriesArrayList.add(new BarEntry(1, user.Products));
        barEntriesArrayList.add(new BarEntry(2, user.Transport));
        barEntriesArrayList.add(new BarEntry(3, user.Food));
        barEntriesArrayList.add(new BarEntry(4, user.Entertainment));
        barEntriesArrayList.add(new BarEntry(5, user.Clothes));
        barEntriesArrayList.add(new BarEntry(6, user.Other));
    }
}