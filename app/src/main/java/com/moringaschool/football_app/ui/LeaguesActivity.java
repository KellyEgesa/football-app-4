package com.moringaschool.football_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.football_app.R;
import com.moringaschool.football_app.adapters.LeaguesAdapter;
import com.moringaschool.football_app.models.competition.Competition;
import com.moringaschool.football_app.models.competition.FootballDataCompetitionSearchResponse;
import com.moringaschool.football_app.network.FootBallApi;
import com.moringaschool.football_app.network.FootBallClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaguesActivity extends AppCompatActivity {
    public List<Competition> leagues;
    @BindView(R.id.errorTextView)
    TextView mErrorText;
    @BindView(R.id.user)
    TextView mUser;
    @BindView(R.id.leaguesText)
    TextView mLeaguesText;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private LeaguesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mUser.setText("Username: " + username);

        FootBallApi client = FootBallClient.urlRequest();
        Call<FootballDataCompetitionSearchResponse> call = client.listCompetitions("TIER_ONE");
        call.enqueue(new Callback<FootballDataCompetitionSearchResponse>() {
            @Override
            public void onResponse(Call<FootballDataCompetitionSearchResponse> call, Response<FootballDataCompetitionSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    leagues = response.body().getCompetitions();
                    mAdapter = new LeaguesAdapter(LeaguesActivity.this, leagues);

                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LeaguesActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                            ((LinearLayoutManager) layoutManager).getOrientation());
                    mRecyclerView.addItemDecoration(dividerItemDecoration);
                    mRecyclerView.setHasFixedSize(true);
                    showRecyclerLeague();
                } else {
                    unSuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<FootballDataCompetitionSearchResponse> call, Throwable t) {
                hideProgressBar();
                failureMessage();

            }
        });


    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);

    }

    private void showRecyclerLeague() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLeaguesText.setVisibility(View.VISIBLE);
        mUser.setVisibility(View.VISIBLE);
    }

    private void unSuccessfulMessage() {
        mErrorText.setText("Something went wrong");
        mErrorText.setVisibility(View.VISIBLE);
    }

    private void failureMessage() {
        mErrorText.setText("Failed. Check your Internet Connection");
        mErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(LeaguesActivity.this, "You entered "+query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}