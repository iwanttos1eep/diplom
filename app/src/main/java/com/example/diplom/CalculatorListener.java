package com.example.diplom;

public interface CalculatorListener {
    public void onReturnNullPointer();
    public void onReturnProducts (Integer Products);
    public void onReturnTogether (Integer Together);
    public void onReturnTransport (Integer Transport);
    public void onReturnFood (Integer Food);
    public void onReturnEntertainment(Integer Entertainment);
    public void onReturnClothes(Integer Clothes);
    public void onReturnOther (Integer Other);
}
