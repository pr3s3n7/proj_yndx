package com.example.btm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterVh> {

    private List<NewsModel> itemListNews;
    private Context context;
    private FavDB favDB;
    private SelectedItemNews selectedItemNews;
    private final LayoutInflater inflaterNews;



    public NewsAdapter(Context context, List<NewsModel> itemListNews, SelectedItemNews selectedItemNews) {
        this.itemListNews = itemListNews;
        this.context = context;
        this.inflaterNews = LayoutInflater.from(context);
        this.selectedItemNews = selectedItemNews;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsAdapterVh onCreateViewHolder(@NonNull ViewGroup parentNews, int viewTypeNews) {
        View view = inflaterNews.inflate(R.layout.item_news, parentNews, false);
        favDB = new FavDB(context);
        return new NewsAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterVh holderNews, final int position) {
        NewsModel newsModel = itemListNews.get(position);

        holderNews.tvHeadlineNews.setText(newsModel.getHeadline());
        holderNews.tvSummaryNews.setText(newsModel.getSummary());
        Glide.with(context).load(newsModel.getImage()).into(holderNews.tvIconNews);
    }

    @Override
    public int getItemCount() { return itemListNews.size(); }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public interface SelectedItemNews {
        void selectedItemNews(NewsModel item);
    }

    public class NewsAdapterVh extends RecyclerView.ViewHolder {
        TextView tvSummaryNews, tvHeadlineNews;
        ImageView tvIconNews;
        public NewsAdapterVh(@NonNull final View itemViewNews) {
            super(itemViewNews);
            tvSummaryNews = itemViewNews.findViewById(R.id.summary_news);
            tvIconNews = itemViewNews.findViewById(R.id.img_news);
            tvHeadlineNews = itemViewNews.findViewById(R.id.headline_news);

            itemViewNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItemNews.selectedItemNews(itemListNews.get(getAdapterPosition()));
                }
            });
        }
    }
}
