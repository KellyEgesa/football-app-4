package com.moringaschool.football_app;

import android.content.Context;
import android.widget.ArrayAdapter;


public class TableAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mLeagueTable;
    private String[] mLeagueTableDetailsPlayed;
    private String[] mLeagueTableDetailsPoints;

    public TableAdapter(Context context,int resources, String[] leagueTable,String[] leagueTableDetailsPlayed,String[] leagueTableDetailsPoints){
        super(context,resources);
        this.mContext = mContext;
        this.mLeagueTable = leagueTable;
        this.mLeagueTableDetailsPlayed = leagueTableDetailsPlayed;
        this.mLeagueTableDetailsPoints = leagueTableDetailsPoints;
    }

    @Override
    public Object getItem(int position){
        String team = mLeagueTable[position];
        String teamGamesPlayed = mLeagueTableDetailsPlayed[position];
        String teamPoints = mLeagueTableDetailsPoints[position];
        return String.format("%d. %s | GamesPlayed: %s Points: %s", position+1, team, teamGamesPlayed, teamPoints);
    }

    @Override
    public int getCount(){
        return mLeagueTable.length;
    }
}
