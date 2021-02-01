package com.moringaschool.football_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.football_app.Constants;
import com.moringaschool.football_app.R;
import com.moringaschool.football_app.ui.TableActivity;
import com.moringaschool.football_app.models.competition.Competition;
import com.moringaschool.football_app.util.ItemTouchHelperViewHolder;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeagueViewHolder> implements ItemTouchHelperViewHolder {
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
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {
        holder.bindLeague(mLeagues.get(position));
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }

    @Override
    public int getItemCount() {
        return mLeagues.size();
    }

    public class LeagueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.leagueName)
        TextView mLeagueName;
        @BindView(R.id.matchDay)
        TextView mMatchDay;
        @BindView(R.id.countryOfOrigin)
        TextView mCountryOfOrigin;
        @BindView(R.id.addToFavouritesButton)
        Button mAddToFavouritesButton;

        private Context mContext;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindLeague(Competition league) {
            mLeagueName.setText(league.getName());
            mCountryOfOrigin.setText("Country Of Origin: " + league.getArea().getName());
            mMatchDay.setText("Matchday " + league.getCurrentSeason().getCurrentMatchday());
            mAddToFavouritesButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            if (v == mAddToFavouritesButton) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                DatabaseReference leagueReference = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_LEAGUES)
                        .child(uid);

                DatabaseReference pushRef = leagueReference.push();
                String pushId = pushRef.getKey();
                mLeagues.get(position).setPushId(pushId);
                pushRef.setValue(mLeagues.get(position));

                Toast.makeText(mContext, "League saved to favourites", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(mContext, TableActivity.class);
                intent.putExtra("league", Parcels.wrap(mLeagues.get(position)));
                mContext.startActivity(intent);
            }

        }


    }

}
