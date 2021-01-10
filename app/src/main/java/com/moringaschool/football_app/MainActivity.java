package com.moringaschool.football_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.getStarted)
    Button mGetStartedButton;
    @BindView(R.id.userName)
    EditText mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mGetStartedButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mGetStartedButton) {
            Intent intent = new Intent(MainActivity.this, Leagues.class);
            startActivity(intent);
            String user = mUserName.getText().toString();
            Toast.makeText(MainActivity.this, "Welcome: "+user, Toast.LENGTH_LONG).show();
        }
    }
}