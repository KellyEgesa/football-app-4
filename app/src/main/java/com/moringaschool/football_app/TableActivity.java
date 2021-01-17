package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.football_app.models.competition.Competition;
import com.moringaschool.football_app.models.standings.FootballDataStandingSearchResponse;
import com.moringaschool.football_app.models.standings.Table;
import com.moringaschool.football_app.network.FootBallApi;
import com.moringaschool.football_app.network.FootBallClient;
import com.moringaschool.football_app.ui.LeaguesActivity;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableActivity extends AppCompatActivity {
    @BindView(R.id.tableName)
    TextView mTableName;
    @BindView(R.id.tableRecyclerView)
    RecyclerView mTableRecyclerView;
    @BindView(R.id.tableProgressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.tableErrorText)
    TextView mErrorText;

    private TableAdapter mAdapter;
    private List<Table> table;
    private Competition mLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        mLeague = Parcels.unwrap(getIntent().getParcelableExtra("league"));
        mTableName.setText(mLeague.getName() + " Table");

        FootBallApi client = FootBallClient.urlRequest();
        Call<FootballDataStandingSearchResponse> call = client.listTable(Integer.toString(mLeague.getId()));

        call.enqueue(new Callback<FootballDataStandingSearchResponse>() {
            @Override
            public void onResponse(Call<FootballDataStandingSearchResponse> call, Response<FootballDataStandingSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    table = response.body().getStandings().get(0).getTable();
                    Toast.makeText(TableActivity.this, table.get(0).getTeam().getName(), Toast.LENGTH_LONG).show();

                    mAdapter = new TableAdapter(TableActivity.this, table);
                    mTableRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TableActivity.this);
                    mTableRecyclerView.setLayoutManager(layoutManager);
//                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mTableRecyclerView.getContext(),
//                            ((LinearLayoutManager) layoutManager).getOrientation());
//                    mTableRecyclerView.addItemDecoration(dividerItemDecoration);
                    mTableRecyclerView.setHasFixedSize(true);

                    showRecyclerLeague();
                } else {
                    unSuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<FootballDataStandingSearchResponse> call, Throwable t) {
                hideProgressBar();
                failureMessage();
            }
        });

    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);

    }

    private void showRecyclerLeague() {
        mTableRecyclerView.setVisibility(View.VISIBLE);
        mTableName.setVisibility(View.VISIBLE);
    }

    private void unSuccessfulMessage() {
        mErrorText.setText("Something went wrong");
        mErrorText.setVisibility(View.VISIBLE);
    }

    private void failureMessage() {
        mErrorText.setText("Failed. Check your Internet Connection");
        mErrorText.setVisibility(View.VISIBLE);
    }
}