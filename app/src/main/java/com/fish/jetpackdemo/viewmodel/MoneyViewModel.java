package com.fish.jetpackdemo.viewmodel;

import androidx.lifecycle.ViewModel;

public class MoneyViewModel extends ViewModel {
    private int money;
    private String name;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
