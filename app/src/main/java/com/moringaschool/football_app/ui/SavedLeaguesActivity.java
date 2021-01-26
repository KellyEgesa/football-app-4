package com.moringaschool.football_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.football_app.Constants;
import com.moringaschool.football_app.R;
import com.moringaschool.football_app.adapters.FirebaseLeagueHolder;
import com.moringaschool.football_app.models.competition.Competition;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedLeaguesActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.leaguesText)
    TextView mLeaguesText;

    private DatabaseReference mLeaguesReference;
    private FirebaseRecyclerAdapter<Competition, FirebaseLeagueHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);

        ButterKnife.bind(this);
        mLeaguesReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_LEAGUES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Competition> options = new FirebaseRecyclerOptions.Builder<Competition>()
                .setQuery(mLeaguesReference, Competition.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Competition, FirebaseLeagueHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseLeagueHolder firebaseLeagueHolder, int i, @NonNull Competition competition) {
                firebaseLeagueHolder.bindLeague(competition);
            }

            @NonNull
            @Override
            public FirebaseLeagueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_card, parent, false);
                return new FirebaseLeagueHolder(view);
            }
        };

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mFirebaseAdapter);
        hideProgressBar();
        showRecyclerLeague();

    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);

    }

    private void showRecyclerLeague() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLeaguesText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }


}
