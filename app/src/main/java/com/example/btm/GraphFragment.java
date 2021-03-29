package com.example.btm;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

import static com.example.btm.MainActivity.toolbar;
import static com.example.btm.Singleton.graphDayList;
import static com.example.btm.Singleton.graphMonthList;
import static com.example.btm.Singleton.graphYearList;
import static com.example.btm.Singleton.scaleDown;
import static com.example.btm.Singleton.scaleUp;
import static com.example.btm.Singleton.selId;


public class GraphFragment extends Fragment {

    TextView nameinc, dmy;
    String perFullname;
    CardView cardView;
    Button btnDay, btnMonth, btnYear;
    CandleStickChart graphView;
    CandleDataSet setD;
    GraphModel graphModelDay;
    GraphModel graphModelMonth;
    GraphModel graphModelYear;
    ArrayList<CandleEntry> gValues = new ArrayList<>();

    public GraphFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_graph, container, false);

            btnDay = view.findViewById(R.id.btn_day);
            btnMonth = view.findViewById(R.id.btn_month);
            btnYear = view.findViewById(R.id.btn_year);
            toolbar = view.findViewById(R.id.toolbar_graf);
            nameinc = view.findViewById(R.id.name_inc_graf);
            dmy = view.findViewById(R.id.tv_dmy);
            cardView = view.findViewById(R.id.card_view_graph);
            scaleUp = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
            scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down_slow);
            graphView = view.findViewById(R.id.graph);

            graphView.setDragEnabled(true);
            graphView.setScaleEnabled(true);
            graphView.setHighlightPerTapEnabled(true);
            graphView.setDrawBorders(true);

            YAxis yAxis = graphView.getAxisLeft();
            YAxis rightAxis = graphView.getAxisRight();
            rightAxis.setDrawGridLines(false);
            graphView.requestDisallowInterceptTouchEvent(false);
            XAxis xAxis = graphView.getXAxis();
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(false);
            rightAxis.setTextColor(Color.WHITE);
            yAxis.setDrawLabels(false);
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setAvoidFirstLastClipping(true);
            Legend l = graphView.getLegend();
            l.setEnabled(false);
            Description description = new Description(); description.setText("");
            graphView.setDescription(description);

            Bundle extras = getActivity().getIntent().getExtras();
            perFullname = extras.getString("fl");
            nameinc.setText(perFullname.toUpperCase());

            graphModelDay = graphDayList.get(selId);
            graphModelMonth = graphMonthList.get(selId);
            graphModelYear = graphYearList.get(selId);

            for (int i = 0; i < graphModelDay.c.length; i++) {
                gValues.add(new CandleEntry(i, graphModelDay.getH(i), graphModelDay.getL(i), graphModelDay.getO(i), graphModelDay.getC(i)));
            }
            setD = new CandleDataSet(gValues, "Data for Prices");
            dmy.setText("DAY");
            onClickButtons();
            setDchange();
            CandleData data = new CandleData(setD);
            graphView.setData(data);
            graphView.invalidate();
            graphView.refreshDrawableState();

            return view;
        }

    @SuppressLint("ClickableViewAccessibility")
    public void onClickButtons() {
        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDay.startAnimation(scaleDown);
                if (!dmy.getText().equals("DAY")) {
                    gValues.clear();
                for (int i = 0; i < graphModelDay.c.length; i++) {
                    gValues.add(new CandleEntry(i, graphModelDay.getH(i), graphModelDay.getL(i), graphModelDay.getO(i), graphModelDay.getC(i)));
                }
                    setD = new CandleDataSet(gValues, "Data for Prices");
                    dmy.setText("DAY");
                    setDchange();
                    CandleData data = new CandleData(setD);
                    graphView.setData(data);
                    graphView.invalidate();
                    graphView.refreshDrawableState();
                }
            }
        });
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMonth.startAnimation(scaleDown);
                if (!dmy.getText().equals("MONTH")) {
                    gValues.clear();
                for (int i = 0; i < graphModelMonth.c.length; i++) {
                    gValues.add(new CandleEntry(i, graphModelMonth.getH(i), graphModelMonth.getL(i), graphModelMonth.getO(i), graphModelMonth.getC(i)));
                }
                    setD = new CandleDataSet(gValues, "Data for Prices");
                    dmy.setText("MONTH");
                    setDchange();
                    CandleData data = new CandleData(setD);
                    graphView.setData(data);
                    graphView.invalidate();
                    graphView.refreshDrawableState();
                }
            }
        });
        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnYear.startAnimation(scaleDown);
                if (!dmy.getText().equals("YEAR")) {
                    gValues.clear();
                for (int i = 0; i < graphModelYear.c.length; i++) {
                    gValues.add(new CandleEntry(i, graphModelYear.getH(i), graphModelYear.getL(i), graphModelYear.getO(i), graphModelYear.getC(i)));
                }
                    setD = new CandleDataSet(gValues, "Data for Prices");
                    dmy.setText("YEAR");
                    setDchange();
                    CandleData data = new CandleData(setD);
                    graphView.setData(data);
                    graphView.invalidate();
                    graphView.refreshDrawableState();
                }
            }
        });
    }

    public void setDchange() {
        setD.setColor(Color.rgb(80, 80, 80));
        setD.setShadowColor(Color.LTGRAY);
        setD.setShadowWidth(0.8f);
        setD.setDecreasingColor(Color.RED);
        setD.setDecreasingPaintStyle(Paint.Style.FILL);
        setD.setIncreasingColor(Color.BLUE);
        setD.setIncreasingPaintStyle(Paint.Style.FILL);
        setD.setNeutralColor(Color.LTGRAY);
        setD.setDrawValues(false);
    }
}