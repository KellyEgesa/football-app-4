package com.moringaschool.football_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.football_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.logInTextEmail)
    EditText mUserEmail;
    @BindView(R.id.logInTextPassword)
    EditText mUserPassword;
    @BindView(R.id.logInButton)
    Button mLogInButton;
    @BindView(R.id.logInTextViewCreateAccount)
    TextView mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        mLogInButton.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLogInButton) {
            Toast.makeText(LogInActivity.this, "Loggin in", Toast.LENGTH_LONG).show();
        }
        if (v == mCreateAccount) {
            Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        }
    }
}