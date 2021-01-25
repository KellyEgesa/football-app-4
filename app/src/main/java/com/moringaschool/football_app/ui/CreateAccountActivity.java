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

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.createAccountEditTextName)
    EditText mUserName;
    @BindView(R.id.createAccountEditTextEmail)
    EditText mUserEmail;
    @BindView(R.id.createAccountEditTextPassword)
    EditText mUserPassword;
    @BindView(R.id.createAccountEditTextConfirmPassword)
    EditText mUserConfirmPassword;
    @BindView(R.id.createAccountButton)
    Button mCreateAccountButton;
    @BindView(R.id.createAccountTextViewLogIn)
    TextView mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mCreateAccountButton.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccountButton) {
            Toast.makeText(CreateAccountActivity.this, "Creating Account", Toast.LENGTH_LONG).show();
        }
        if (v == mLogIn) {
            Intent intent = new Intent(CreateAccountActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }
}