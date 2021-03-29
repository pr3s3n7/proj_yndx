package com.example.btm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavAdapterVh> {

    private List<FavItem> itemListFav;
    private Context context;
    private FavDB favDB;
    private SelectedItemFav selectedItemFav;
    private final LayoutInflater inflaterFav;



    public FavAdapter(Context context, List<FavItem> itemListFav, SelectedItemFav selectedItemFav) {
        this.itemListFav = itemListFav;
        this.context = context;
        this.inflaterFav = LayoutInflater.from(context);
        this.selectedItemFav = selectedItemFav;
    }

    @NonNull
    @Override
    public FavAdapter.FavAdapterVh onCreateViewHolder(@NonNull ViewGroup parentFav, int viewTypeFav) {
        View view = inflaterFav.inflate(R.layout.fav_item, parentFav, false);
        favDB = new FavDB(context);
        return new FavAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapterVh holderFav, final int position) {
        holderFav.tvTikerFav.setText(itemListFav.get(position).getFavTiker());
        holderFav.tvFullnameFav.setText(itemListFav.get(position).getFavFullname());
        holderFav.tvIconFav.setImageResource(itemListFav.get(position).getFavIconResource());
    }

    @Override
    public int getItemCount() { return itemListFav.size(); }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public interface SelectedItemFav {
        void selectedItemFav(FavItem item);
    }

    public class FavAdapterVh extends RecyclerView.ViewHolder {
        TextView tvTikerFav, tvFullnameFav;
        ImageView tvIconFav;
        Button tvFavouriteFav;
        public FavAdapterVh(@NonNull final View itemViewFav) {
            super(itemViewFav);
            tvTikerFav = itemViewFav.findViewById(R.id.fav_tiker);
            tvFullnameFav = itemViewFav.findViewById(R.id.fav_fullName);
            tvIconFav = itemViewFav.findViewById(R.id.fav_icon);
            tvFavouriteFav = itemViewFav.findViewById(R.id.fav_b_favorite);

            tvFavouriteFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    FavItem favItem = itemListFav.get(getAdapterPosition());
                    favDB.remove_fav(favItem.getFavItemId());
                    removeItem(position);
                }
            });

            itemViewFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItemFav.selectedItemFav(itemListFav.get(getAdapterPosition()));
                }
            });
        }
    }

    private void removeItem(int position) {
        itemListFav.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemListFav.size());
    }
}
