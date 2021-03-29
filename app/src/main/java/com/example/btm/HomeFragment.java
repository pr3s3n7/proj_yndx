package com.example.btm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.btm.MainActivity.btnNav;
import static com.example.btm.MainActivity.hideNav;
import static com.example.btm.MainActivity.showNav;
import static com.example.btm.MainActivity.toolbar;
import static com.example.btm.Singleton.itemList;
import static com.example.btm.Singleton.setInitialData;


public class HomeFragment extends Fragment implements UselessAdapter.SelectedItem {

    UselessAdapter uadapter;
    RecyclerView recyclerViewHome;
    Animation scaleUp, scaleDown;

    public HomeFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);
                AppCompatActivity activity = (AppCompatActivity) getActivity();

                if (itemList.size() == 0) {
                    setInitialData();
                }

                scaleUp = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up_small);
                scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);

                toolbar = view.findViewById(R.id.toolbar);
                activity.setSupportActionBar(toolbar);
                activity.getSupportActionBar().setTitle("");

                uadapter = new UselessAdapter(activity, itemList, this);
                recyclerViewHome = view.findViewById(R.id.list_recycle_home);
                recyclerViewHome.setAdapter(uadapter);
                recyclerViewHome.setHasFixedSize(true);
                recyclerViewHome.setLayoutManager(new LinearLayoutManager(activity));
                recyclerViewHome.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
                recyclerViewHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dy > 0 && btnNav.getVisibility() == BottomNavigationView.VISIBLE) {
                            btnNav.startAnimation(scaleDown);
                            hideNav();
                        } else if (dy < 0 && btnNav.getVisibility() == BottomNavigationView.GONE) {
                            btnNav.startAnimation(scaleUp);
                            showNav();
                        }
                    }
                });
                for (int i = itemList.size(); i <= 6; ) {
                    if (i <= 6) {
                        recyclerViewHome.setVisibility(RecyclerView.GONE);
                        Log.d("dasds", String.valueOf(recyclerViewHome.getVisibility()));
                    } else {
                        recyclerViewHome.setVisibility(RecyclerView.VISIBLE);
                    }
                }

                uadapter.notifyDataSetChanged();

                setHasOptionsMenu(true);
                return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                uadapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.search_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void selectedItem(Item Item) {
        Intent extras = new Intent(getActivity(), SelectItemActivity.class);
        extras.putExtra("posid", Item.getItemId());
        extras.putExtra("fl", Item.getFullname());
        startActivity(extras);
    }
}
