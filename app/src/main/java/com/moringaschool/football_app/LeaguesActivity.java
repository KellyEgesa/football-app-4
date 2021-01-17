package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.football_app.models.Competition;
import com.moringaschool.football_app.models.FootballDataCompetitionSearchResponse;
import com.moringaschool.football_app.network.FootBallApi;
import com.moringaschool.football_app.network.FootBallClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaguesActivity extends AppCompatActivity {
    @BindView(R.id.user)
    TextView mUser;
    GridView gridView;

    public List<Competition> leagues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Toast.makeText(LeaguesActivity.this, username, Toast.LENGTH_LONG).show();
        FootBallApi client = FootBallClient.listCompetitions();
        Call<FootballDataCompetitionSearchResponse> call = client.listCompetitions("TIER_ONE");
        call.enqueue(new Callback<FootballDataCompetitionSearchResponse>() {
            @Override
            public void onResponse(Call<FootballDataCompetitionSearchResponse> call, Response<FootballDataCompetitionSearchResponse> response) {
                if(response.isSuccessful()){
                    leagues = response.body().getCompetitions();

                }
            }

            @Override
            public void onFailure(Call<FootballDataCompetitionSearchResponse> call, Throwable t) {

            }
        });
        mUser.setText("User: " + username);
        gridView = (GridView) findViewById(R.id.leagueCard);
        gridView.setAdapter(new LeagueAdapter(this, leagues, country, champions));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LeaguesActivity.this, Table.class);
                intent.putExtra("league", leagues[position]);
                Toast.makeText(LeaguesActivity.this, "Showing Table of " + leagues[position], Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}