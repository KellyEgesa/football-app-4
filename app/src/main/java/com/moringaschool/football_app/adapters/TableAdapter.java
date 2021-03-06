package com.moringaschool.football_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.football_app.R;
import com.moringaschool.football_app.models.standings.Table;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private Context mContext;
    private List<Table> mLeagueTable;

    public TableAdapter(Context context, List<Table> leagueTable) {
        mContext = context;
        mLeagueTable = leagueTable;
    }

    @NonNull
    @Override
    public TableAdapter.TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_league_table, parent, false);
        TableViewHolder tableViewHolder = new TableViewHolder(view);
        return tableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.TableViewHolder holder, int position) {
        holder.bindTable(mLeagueTable.get(position));
    }

    @Override
    public int getItemCount() {
        return mLeagueTable.size();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {
        public Context mContext;
        @BindView(R.id.tableNumber)
        TextView mTableNumber;
        @BindView(R.id.teamName)
        TextView mTeamName;
        @BindView(R.id.gamesPlayed)
        TextView mGamesPlayed;
        @BindView(R.id.win)
        TextView mWin;
        @BindView(R.id.draw)
        TextView mDraw;
        @BindView(R.id.lost)
        TextView mLost;
        @BindView(R.id.goalsFor)
        TextView mGoalsFor;
        @BindView(R.id.goalsAgainst)
        TextView mGoalsAgainst;
        @BindView(R.id.goalDifference)
        TextView mGoalDifference;
        @BindView(R.id.points)
        TextView mPoints;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindTable(Table table) {
            mTableNumber.setText(Integer.toString(table.getPosition()));
            mTeamName.setText(table.getTeam().getName());
            mGamesPlayed.setText(Integer.toString(table.getPlayedGames()));
            mWin.setText(Integer.toString( table.getWon()));
            mDraw.setText(Integer.toString( table.getDraw()));
            mLost.setText(Integer.toString(table.getLost()));
            mGoalsFor.setText(Integer.toString(table.getGoalsFor()));
            mGoalsAgainst.setText(Integer.toString(table.getGoalsAgainst()));
            mGoalDifference.setText(Integer.toString(table.getGoalDifference()));
            mPoints.setText(Integer.toString(table.getPoints()));
        }
    }
}
