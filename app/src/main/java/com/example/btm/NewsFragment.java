package com.example.btm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.btm.MainActivity.toolbar;
import static com.example.btm.Singleton.newsList;
import static com.example.btm.Singleton.selId;

public class NewsFragment extends Fragment implements NewsAdapter.SelectedItemNews {

    String pos, perFullname, notNewsStr = "Новостей еще не появлялось";
    TextView loading, noConnection;
    int size;
    Bundle pered = new Bundle();
    List<NewsModel> newsModels;

    RecyclerView recyclerViewNews;
    NewsAdapter nadapter;

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbar = view.findViewById(R.id.toolbar_news);
        recyclerViewNews = view.findViewById(R.id.list_news);
        loading = view.findViewById(R.id.tv_loading);
        noConnection = view.findViewById(R.id.tv_no_connection_news);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            perFullname = extras.getString("fl");
            pos = extras.getString("posid");
            pered.getString(extras.getString("fl"));
            pered.getInt(extras.getString("posid"));
        }

        newsModels = newsList.get(selId);
        nadapter = new NewsAdapter(activity, newsModels, this);

        recyclerViewNews.setHasFixedSize(true);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        for (int i = 0; i < newsModels.size(); i++) {
                    loading.setVisibility(TextView.GONE);
                    /* API get получает список, но я заметил, что дублируются элементы.
                    Проблема не в списке или адаптере, а в самом API, высылает одинаковые элементы */
                    if (newsModels.size() > 1) {
                        for (int j = 0; j < newsModels.size(); j++) {
                            if (newsModels.get(j).getUrl().equals(newsModels.get(j).getUrl())) {
                                newsModels.remove(j);
                            }
                        }
                    }
                    recyclerViewNews.setAdapter(nadapter);
        }
        if (newsModels.isEmpty()) {
            loading.setText(notNewsStr);
        }
        return view;
    }

    @Override
    public void selectedItemNews(NewsModel item) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
        startActivity(intent);
        getActivity().finish();
    }
}