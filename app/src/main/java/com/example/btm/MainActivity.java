package com.example.btm;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.btm.Singleton.graphDayList;
import static com.example.btm.Singleton.graphMonthList;
import static com.example.btm.Singleton.graphYearList;
import static com.example.btm.Singleton.newsList;
import static com.example.btm.Singleton.priceList;

public class MainActivity extends AppCompatActivity {

    public static Toolbar toolbar;
    public static BottomNavigationView btnNav;
    SelectedApi selectedApi;
    ArrayList<Call<GraphModel>> callGraphDay = new ArrayList<>();
    ArrayList<Call<GraphModel>> callGraphMonth = new ArrayList<>();
    ArrayList<Call<GraphModel>> callGraphYear = new ArrayList<>();
    ArrayList<Call<PriceModel>> callPrice = new ArrayList<>();
    ArrayList<Call<List<NewsModel>>> callNews = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        selectedApi = App.getApi();

        btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);
        btnNav.setOnNavigationItemReselectedListener(ravListener);
        addElements();
        allGetsUnPack();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new HomeFragment())
                .commit();
    }

    public void allGetsUnPack() {
        for (int i = 0; i <= 7; i++) {
            callGraphDay.get(i).enqueue(new Callback<GraphModel>() {
                @Override
                public void onResponse(Call<GraphModel> call, Response<GraphModel> response) {
                    graphDayList.add(response.body());
                }

                @Override
                public void onFailure(Call<GraphModel> call, Throwable t) {
                }
            });
            callGraphMonth.get(i).clone().enqueue(new Callback<GraphModel>() {
                @Override
                public void onResponse(Call<GraphModel> call, Response<GraphModel> response) {
                    graphMonthList.add(response.body());
                }

                @Override
                public void onFailure(Call<GraphModel> call, Throwable t) {
                }
            });
            callGraphYear.get(i).clone().enqueue(new Callback<GraphModel>() {
                @Override
                public void onResponse(Call<GraphModel> call, Response<GraphModel> response) {
                    graphYearList.add(response.body());
                }

                @Override
                public void onFailure(Call<GraphModel> call, Throwable t) {
                }
            });
            callNews.get(i).clone().enqueue(new Callback<List<NewsModel>>() {
                @Override
                public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                    newsList.add(response.body());
                }

                @Override
                public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                }
            });
            callPrice.get(i).clone().enqueue(new Callback<PriceModel>() {
                @Override
                public void onResponse(Call<PriceModel> call, Response<PriceModel> response) {
                    priceList.add(response.body());
                }

                @Override
                public void onFailure(Call<PriceModel> call, Throwable t) {
                }
            });
        }
    }

        public void addElements() {
            callGraphDay.add(0, selectedApi.getYndxGraphDayData()); callGraphDay.add(1, selectedApi.getAaplGraphDayData());
            callGraphDay.add(2, selectedApi.getGooglGraphDayData()); callGraphDay.add(3, selectedApi.getAmznGraphDayData());
            callGraphDay.add(4, selectedApi.getBacGraphDayData()); callGraphDay.add(5, selectedApi.getMsftGraphDayData());
            callGraphDay.add(6, selectedApi.getTslaGraphDayData()); callGraphDay.add(7, selectedApi.getMaGraphDayData());

            callGraphMonth.add(0, selectedApi.getYndxGraphMonthData()); callGraphMonth.add(1, selectedApi.getAaplGraphMonthData());
            callGraphMonth.add(2, selectedApi.getGooglGraphMonthData()); callGraphMonth.add(3, selectedApi.getAmznGraphMonthData());
            callGraphMonth.add(4, selectedApi.getBacGraphMonthData()); callGraphMonth.add(5, selectedApi.getMsftGraphMonthData());
            callGraphMonth.add(6, selectedApi.getTslaGraphMonthData()); callGraphMonth.add(7, selectedApi.getMaGraphMonthData());

            callGraphYear.add(0, selectedApi.getYndxGraphYearData()); callGraphYear.add(1, selectedApi.getAaplGraphYearData());
            callGraphYear.add(2, selectedApi.getGooglGraphYearData()); callGraphYear.add(3, selectedApi.getAmznGraphYearData());
            callGraphYear.add(4, selectedApi.getBacGraphYearData()); callGraphYear.add(5, selectedApi.getMsftGraphYearData());
            callGraphYear.add(6, selectedApi.getTslaGraphYearData()); callGraphYear.add(7, selectedApi.getMaGraphYearData());

            callNews.add(0, selectedApi.getYndxNewsData()); callNews.add(1, selectedApi.getAaplNewsData());
            callNews.add(2, selectedApi.getGooglNewsData()); callNews.add(3, selectedApi.getAmznNewsData());
            callNews.add(4, selectedApi.getBacNewsData()); callNews.add(5, selectedApi.getMsftNewsData());
            callNews.add(6, selectedApi.getTslaNewsData()); callNews.add(7, selectedApi.getMaNewsData());

            callPrice.add(0, selectedApi.getYndxPriceData()); callPrice.add(1, selectedApi.getAaplPriceData());
            callPrice.add(2, selectedApi.getGooglPriceData()); callPrice.add(3, selectedApi.getAmznPriceData());
            callPrice.add(4, selectedApi.getBacPriceData()); callPrice.add(5, selectedApi.getMsftPriceData());
            callPrice.add(6, selectedApi.getTslaPriceData()); callPrice.add(7, selectedApi.getMaPriceData());
        }

    private BottomNavigationView.OnNavigationItemReselectedListener ravListener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {

                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                       Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.item_home:
                                selectedFragment = new HomeFragment();
                            break;
                        case R.id.item_favourite:
                                selectedFragment = new FavouriteFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout, selectedFragment)
                            .commit();
                    return true;
                }
            };

    public static void hideNav() {
        btnNav.setVisibility(BottomNavigationView.GONE);
    }
    public static void showNav() {
        btnNav.setVisibility(BottomNavigationView.VISIBLE);
    }
}