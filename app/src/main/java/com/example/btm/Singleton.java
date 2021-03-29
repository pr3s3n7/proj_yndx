package com.example.btm;

import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    public static ArrayList<Item> itemList = new ArrayList<>();
    public static ArrayList<FavItem> itemListFav = new ArrayList<>();
    public static ArrayList<List<NewsModel>> newsList = new ArrayList<>();
    public static ArrayList<GraphModel> graphDayList = new ArrayList<>();
    public static ArrayList<GraphModel> graphMonthList = new ArrayList<>();
    public static ArrayList<GraphModel> graphYearList = new ArrayList<>();
    public static ArrayList<PriceModel> priceList = new ArrayList<>();
    public static Animation scaleUp, scaleDown;
    public static int selId;

    public static void setInitialData() {
        itemList.add(new Item ("0","YNDX", "Yandex, LLC", R.drawable.yandex, "0"));
        itemList.add(new Item ("1","AAPL", "Apple Inc.", R.drawable.apple, "0"));
        itemList.add(new Item ("2", "GOOGL", "Alphabet Class A", R.drawable.google, "0"));
        itemList.add(new Item ("3", "AMZN", "Amazon.com", R.drawable.amazon, "0"));
        itemList.add(new Item ("4", "BAC", "Bank of America corp", R.drawable.bac, "0"));
        itemList.add(new Item ("5", "MSFT", "Microsoft Corporation", R.drawable.microsoft, "0"));
        itemList.add(new Item ("6", "TSLA", "Tesla Motors", R.drawable.tesla, "0"));
        itemList.add(new Item ("7", "MA", "MasterCard", R.drawable.ma, "0"));
    }
}
