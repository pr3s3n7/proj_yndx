package com.example.btm;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class UselessAdapter extends RecyclerView.Adapter<UselessAdapter.UselessAdapterVh> implements Filterable {

    private ArrayList<Item> itemList;
    private ArrayList<Item> getItemListFiltered;
    private SelectedItem selectedItem;
    private final LayoutInflater inflater;
    private FavDB favDB;
    private Context context;

    public UselessAdapter(Context context, ArrayList<Item> itemList, SelectedItem selectedItem) {
        this.getItemListFiltered = itemList;
        this.itemList = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.selectedItem = selectedItem;
    }

    @NonNull
    @Override
    public UselessAdapter.UselessAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);

        favDB = new FavDB(context);

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        return new UselessAdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UselessAdapterVh holder, final int position) {
        final Item item = itemList.get(position);

        readCursorData(item, holder);
        holder.tvIcon.setImageResource(item.getIconResource());
        holder.tvTiker.setText(item.getTiker());
        holder.tvFullname.setText(item.getFullname());
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if (charSequence == null && charSequence.length() == 0) {
                    filterResults.count = getItemListFiltered.size();
                    filterResults.values = getItemListFiltered;
                } else {
                    String searchChr = charSequence.toString().toLowerCase();

                    List<Item> resultData = new ArrayList<>();

                    for (Item item : getItemListFiltered) {
                        if (item.getFullname().toLowerCase().contains(searchChr) || item.getTiker().toLowerCase().contains(searchChr)) {
                            resultData.add(item);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                itemList = (ArrayList<Item>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public interface SelectedItem {
        void selectedItem(Item item);
    }

    public class UselessAdapterVh extends RecyclerView.ViewHolder {

        TextView tvTiker, tvFullname;
        ImageView tvIcon;
        Button tvFavourite;

        public UselessAdapterVh (@NonNull View itemView) {
            super(itemView);
            tvTiker = itemView.findViewById(R.id.tiker);
            tvFullname = itemView.findViewById(R.id.fullName);
            tvIcon = itemView.findViewById(R.id.icon);
            tvFavourite = itemView.findViewById(R.id.b_favorite);

            tvFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Item item = itemList.get(position);

                    if (item.getFavorite().equals("0")) {
                        item.setFavorite("1");
                        favDB.insertIntoTheDatabase(item.getTiker(), item.getFullname(),
                                item.getIconResource(), item.getItemId(), item.getFavorite());
                        tvFavourite.setBackgroundResource(R.drawable.ic_favourite_is_true);
                    } else {
                        item.setFavorite("0");
                        favDB.remove_fav(item.getItemId());
                        tvFavourite.setBackgroundResource(R.drawable.ic_favourite_is_false);
                    }
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        selectedItem.selectedItem(itemList.get(getAdapterPosition()));
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(Item item, UselessAdapterVh viewHolder) {
        Cursor cursor = favDB.read_all_data(item.getItemId());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                item.setFavorite(item_fav_status);

                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.tvFavourite.setBackgroundResource(R.drawable.ic_favourite_is_true);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.tvFavourite.setBackgroundResource(R.drawable.ic_favourite_is_false);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                db.close();
        }
    }
}
