package com.example.btm;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import static com.example.btm.Singleton.selId;


public class SelectItemActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    SelectedApi selectedApi;
    String perFullname;
    String pos;
    Bundle pered = new Bundle();
    androidx.fragment.app.FragmentManager mf = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_select_item);
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            perFullname = extras.getString("fl");
            pos = extras.getString("posid");
            pered.getString(extras.getString("fl"));
            pered.getInt(extras.getString("posid"));
        }

        selectedApi = App.getApi();
        if (pos.equals("0")) {
            selId = 0;
        } else if (pos.equals("1")) {
            selId = 1;
        } else if (pos.equals("2")) {
            selId = 2;
        } else if (pos.equals("3")) {
            selId = 3;
        } else if (pos.equals("4")) {
            selId = 4;
        } else if (pos.equals("5")) {
            selId = 5;
        } else if (pos.equals("6")) {
            selId = 6;
        } else if (pos.equals("7")) {
            selId = 7;
        }
        mViewPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(mf);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
        public void onBackPressed () {
            super.onBackPressed();
        }

         class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
             public ScreenSlidePagerAdapter(androidx.fragment.app.FragmentManager mf) {
                 super(mf);
             }

             @Override
            public Fragment getItem(int position) {
                 Fragment fragmentSelected = null;
                 switch (position) {
                     case 0:
                        fragmentSelected = new GraphFragment();
                     return fragmentSelected;
                     case 1:
                        fragmentSelected = new NewsFragment();
                     return fragmentSelected;
                     case 2:
                        fragmentSelected = new PriceFragment();
                     return fragmentSelected;
                 }
                 pered.putString("posid", pos);
                 pered.putString("fl", perFullname);
                 fragmentSelected.setArguments(pered);
                 return fragmentSelected;
             }

            @Override
            public int getCount() {
                return NUM_PAGES;
            }
    }
}