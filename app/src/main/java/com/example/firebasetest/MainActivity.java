package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EaseInOutCubic;

public class MainActivity<tv> extends AppCompatActivity {
    Button btnDialog,btnVote;
    Context context;
    PieChart pieChart;
    TextView tv;

    FirebaseDatabase database;
    DatabaseReference myRef;

    int[] color = new int[]{Color.BLUE,Color.RED,Color.GRAY};

    int flag = 0;
    long oldYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog = findViewById(R.id.btndialog);
        btnVote = findViewById(R.id.btnVote);
        tv = findViewById(R.id.textView);

        pieChart = findViewById(R.id.pieChart);

        PieDataSet pieDataSet = new PieDataSet(data1(),"chart");
        pieDataSet.setColors(color);
        pieDataSet.setSliceSpace(3);
        //pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setUsePercentValues(true);
        pieData.setValueTextSize(10);
        pieChart.setEntryLabelTextSize(12);
        pieData.setValueTextColor(Color.DKGRAY);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(0);
        pieChart.animateY(1000, EaseInOutCubic);
        pieChart.setData(pieData);
        pieChart.invalidate();

        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);

//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ChartValues");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //oldYes = snapshot.getValue(long.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0){
                    oldYes++;
                    myRef.setValue(oldYes);
                    flag = 1;
                }
            }
        });
        //insertData();

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(MainActivity.this);
               customDialog.show();

            }
        });
    }


    private List<PieEntry> data1() {
        ArrayList<PieEntry> datavalue = new ArrayList<>();

        datavalue.add(new PieEntry(30,"롱패딩"));

        return datavalue;
    }
}