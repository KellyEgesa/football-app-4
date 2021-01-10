package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class Leagues extends AppCompatActivity {
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
    }
}