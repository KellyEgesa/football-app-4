package com.moringaschool.football_app.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.football_app.R;
import com.moringaschool.football_app.models.Competition;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeagueViewHolder> {
    private List<Competition> mLeagues;
    private Context mContext;

    public LeaguesAdapter(Context context, List<Competition> leagues) {
        mContext = context;
        mLeagues = leagues;
    }

    @NonNull
    @Override
    public LeaguesAdapter.LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_card, parent, false);
        LeagueViewHolder viewHolder = new LeagueViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {
        holder.bindLeague(mLeagues.get(position));
    }


    @Override
    public int getItemCount() {
        return mLeagues.size();
    }

    public class LeagueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.leagueEmblem)
        ImageView mLeagueEmblem;
        @BindView(R.id.leagueName)
        TextView mLeagueName;
        @BindView(R.id.matchDay)
        TextView mMatchDay;
        @BindView(R.id.countryOfOrigin)
        TextView mCountryOfOrigin;

        private Context mContext;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindLeague(Competition league) {
            mLeagueName.setText(league.getName());
            mCountryOfOrigin.setText("Country Of Origin: " + league.getArea().getName());
            mMatchDay.setText("Matchday 19 of " + league.getCurrentSeason().getCurrentMatchday());
            if (league.getEmblemUrl() != null) {
                Picasso.get().load((Uri) league.getEmblemUrl()).into(mLeagueEmblem);
            } else if (league.getArea().getEnsignUrl() != null) {
                Picasso.get().load((Uri) league.getArea().getEnsignUrl()).into(mLeagueEmblem);
            }
        }
    }

}
