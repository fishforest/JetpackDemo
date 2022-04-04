package com.fish.jetpackdemo.viewmodel;

public class MyViewModel {
    private int money;
    private String name = "我的ViewModel";

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
