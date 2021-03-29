package com.example.btm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import static com.example.btm.MainActivity.toolbar;
import static com.example.btm.Singleton.priceList;
import static com.example.btm.Singleton.selId;

public class PriceFragment extends Fragment {

    TextView cPrice, hPrice, lPrice, clPrice, oPrice, noConnection;
    String perFullname, pos;
    CardView cardPrice;
    PriceModel priceModel;
    Bundle pered = new Bundle();

    public PriceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price, container, false);
            toolbar = view.findViewById(R.id.toolbar_price);
            cPrice = view.findViewById(R.id.tv_current_price);
            hPrice = view.findViewById(R.id.tv_high_price);
            lPrice = view.findViewById(R.id.tv_low_price);
            clPrice = view.findViewById(R.id.tv_closeday_price);
            oPrice = view.findViewById(R.id.tv_openday_price);
            cardPrice = view.findViewById(R.id.card_view_price);
            noConnection = view.findViewById(R.id.tv_no_connection);

            priceModel = priceList.get(selId);
            for (int i = 0; i < priceList.size(); i++) {
                cPrice.setText(priceModel.getC());
                hPrice.setText(priceModel.getH());
                lPrice.setText(priceModel.getL());
                oPrice.setText(priceModel.getO());
                clPrice.setText(priceModel.getPc());
            }
            if (cPrice.equals("X")) {
                noConShow();
            } else {
                noConHide();
            }
        return view;
    }


    public void noConShow() { noConnection.setVisibility(TextView.VISIBLE); }
    public void noConHide() { noConnection.setVisibility(TextView.GONE); }
}