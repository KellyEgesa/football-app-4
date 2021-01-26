package com.moringaschool.football_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.football_app.Constants;
import com.moringaschool.football_app.R;
import com.moringaschool.football_app.models.competition.Competition;
import com.moringaschool.football_app.ui.TableActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

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
        itemView.setOnClickListener(this);
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
        final ArrayList<Competition> league = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_LEAGUES)
                .child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    league.add(dataSnapshot.getValue(Competition.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, TableActivity.class);
                intent.putExtra("league", Parcels.wrap(league.get(itemPosition)));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
