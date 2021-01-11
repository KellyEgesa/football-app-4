package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class Leagues extends AppCompatActivity{
    GridView gridView;
    String[] leagues = new String[]{"Premier League", "La Liga", "Seria A", "Bundesliga", "Ligue A", "Major League Soccer"};
    String[] country = new String[]{"England", "Spain", "Italy", "Germany", "France", "USA"};
    String[] champions = new String[]{"Liverpool", "Real Madrid", "Juventus", "Bayern Munich", "Paris Saint German", "Atlanta United"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        gridView = (GridView)findViewById(R.id.leagueCard);
        gridView.setAdapter(new LeagueAdapter(this, leagues, country, champions));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Leagues.this, "League: " + leagues[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Leagues.this, Table.class);
                intent.putExtra("league",leagues[position]);
                startActivity(intent);
            }
        });
    }
}