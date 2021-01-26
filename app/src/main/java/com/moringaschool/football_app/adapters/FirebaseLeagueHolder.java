package com.moringaschool.football_app.adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.football_app.R;
import com.moringaschool.football_app.models.competition.Competition;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseLeagueHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    @BindView(R.id.leagueName)
    TextView mLeagueName;
    @BindView(R.id.matchDay)
    TextView mMatchDay;
    @BindView(R.id.countryOfOrigin)
    TextView mCountryOfOrigin;
    @BindView(R.id.addToFavouritesButton)
    Button mAddToFavouritesButton;

    public FirebaseLeagueHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bindLeague(Competition league) {
        mLeagueName.setText(league.getName());
        mCountryOfOrigin.setText("Country Of Origin: " + league.getArea().getName());
        mMatchDay.setText("Matchday " + league.getCurrentSeason().getCurrentMatchday());
        mAddToFavouritesButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

    }
}
