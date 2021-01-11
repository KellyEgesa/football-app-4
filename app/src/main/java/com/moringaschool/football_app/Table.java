package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Table extends AppCompatActivity {
    @BindView(R.id.tableName)
    TextView mTableName;
    @BindView(R.id.listView)
    ListView mList;
    String[] leagueTable = new String[]{"Liverpool", " Manchester United", "Leicester City", "Tottenham Hotspur", "Manchester City", "Southampton", "Everton",
            "Aston Villa", "Chelsea", "West Ham United", "Arsenal", "Leeds United", "Wolverhampton Wanderers", "Crystal Palace", "Newcastle United"
            , "Burnley", " Brighton and Hove Albion", "Fulham", " West Bromwich Albion", "Sheffield United"};
    String[] leagueTableDetailsPlayed = new String[]{"17", "16", "17", "16", "15", "17", "16", "15", "17", "17", "17", "17", "17", "17", "16", "15", "17", "15", "17", "17"};
    String[] leagueTableDetailsPoints = new String[]{"33", "33", "32", "29", "29", "29", "29", "26", "26", "26", "23", "23", "22", "22", "19", "16", "16", "14", "11", "8", "2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String tableName = intent.getStringExtra("league");
        mTableName.setText(tableName+" Table");
        TableAdapter adapter = new TableAdapter(this, android.R.layout.simple_list_item_1, leagueTable, leagueTableDetailsPlayed, leagueTableDetailsPoints);
        mList.setAdapter(adapter);

    }
}