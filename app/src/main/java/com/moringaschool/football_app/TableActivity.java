package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableActivity extends AppCompatActivity {
    @BindView(R.id.tableName)
    TextView mTableName;
    @BindView(R.id.tableRecyclerView)
    RecyclerView mTableRecyclerView;
    private TableAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
//        ButterKnife.bind(this);
//        Intent intent = getIntent();
//        String tableName = intent.getStringExtra("league");
//        mTableName.setText(tableName+" Table");
//        TableAdapter adapter = new TableAdapter(this, android.R.layout.simple_list_item_1, leagueTable, leagueTableDetailsPlayed, leagueTableDetailsPoints);
//        mList.setAdapter(adapter);

    }
}