package com.moringaschool.football_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LeagueAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mLeagues;
    private String[] mCountry;
    private String[] mChampions;

    public LeagueAdapter(Context context, String[] leagues, String[] country,
                         String[] champions) {
        this.mContext = context;
        this.mLeagues = leagues;
        this.mCountry = country;
        this.mChampions = champions;

    }

    public Object getItems(int position, String[] array) {
        return array[position];
    }

    @Override
    public int getCount() {
        return mLeagues.length;
    }

    @Override
    public Object getItem(int position) {
        String league = mLeagues[position];
        return league;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = inflater.inflate(R.layout.league_card, null);
            TextView leagueView = (TextView) gridView.findViewById(R.id.league);
            leagueView.setText((String) getItem(position));
            TextView countryView = (TextView) gridView.findViewById(R.id.counrty);
            countryView.setText((String) "Country of League: " + getItems(position, mCountry));
            TextView championsView = (TextView) gridView.findViewById(R.id.champions);
            championsView.setText((String) "Current Champions: " + getItems(position, mChampions));
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
