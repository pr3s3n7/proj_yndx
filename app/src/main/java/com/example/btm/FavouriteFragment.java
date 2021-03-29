package com.example.btm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.btm.MainActivity.btnNav;
import static com.example.btm.MainActivity.hideNav;
import static com.example.btm.MainActivity.showNav;
import static com.example.btm.MainActivity.toolbar;
import static com.example.btm.Singleton.itemListFav;
import static com.example.btm.Singleton.scaleDown;
import static com.example.btm.Singleton.scaleUp;

public class FavouriteFragment extends Fragment implements FavAdapter.SelectedItemFav  {

    FavAdapter favAdapter;
    RecyclerView recyclerViewFav;
    private FavDB favDB;
    TextView tv_rvIsEmpty;
    Button backFav;

    public FavouriteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_favourite, container, false);

                final AppCompatActivity activity = (AppCompatActivity) getActivity();
                favDB = new FavDB(getContext());

                toolbar = view.findViewById(R.id.toolbar_fav);
                tv_rvIsEmpty = view.findViewById(R.id.rvIsEmpty);
                backFav = view.findViewById(R.id.btn_back_fav);
                backFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnNav.setSelectedItemId(R.id.item_home);
                        showNav();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_layout, new HomeFragment())
                                .commit();
                    }
                });


                scaleUp = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up_small);
                scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);

                activity.setSupportActionBar(toolbar);
                activity.getSupportActionBar().setTitle("");

                recyclerViewFav = view.findViewById(R.id.list_fav);
                recyclerViewFav.setLayoutManager(new LinearLayoutManager(activity));
                recyclerViewFav.setAdapter(favAdapter);
                recyclerViewFav.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
                recyclerViewFav.setHasFixedSize(true);
                recyclerViewFav.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                loadData();

                favAdapter.notifyDataSetChanged();

                setHasOptionsMenu(true);
                return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (itemListFav.size() == 0) { tv_rvIsEmpty.setVisibility(TextView.VISIBLE);
        Log.d("size", String.valueOf(itemListFav.size())); }
    }

    // Тут я сделал список избранного, решил использовать базу Sql, тк считаю это самым удобным способом в данном случае
    private void loadData() {
        if (itemListFav != null) {
            itemListFav.clear();
        }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_TITLE));
                String fullName = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_FULLNAME));
                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavDB.ITEM_IMAGE)));
                FavItem favItem = new FavItem(title, fullName, id, image);
                itemListFav.add(favItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                db.close();
            }

            favAdapter = new FavAdapter(getActivity(), itemListFav, this);

            recyclerViewFav.setAdapter(favAdapter);
    }



    @Override
    public void selectedItemFav(FavItem Item) {
        Intent extras = new Intent(getActivity(), SelectItemActivity.class);
        extras.putExtra("posid", Item.getFavItemId());
        extras.putExtra("fl", Item.getFavFullname());
        startActivity(extras);
    }
}