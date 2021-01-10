package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class leagues extends AppCompatActivity {
    String[] leagues = new String[]{"Premier League", "La Liga", "Seria A", "Bundesliga"};
    String[] country = new String[]{"England", "Spain", "Italy", "Germany"};
    String[] champions = new String[]{"Liverpool", "Real Madrid", "Juventus", "Bayern Munich"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
    }
}