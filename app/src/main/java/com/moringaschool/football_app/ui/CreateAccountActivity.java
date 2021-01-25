package com.moringaschool.football_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mCreateAccountButton.setOnClickListener(this);
        mLogIn.setOnClickListener(this);

        loadingScreen();
        createAuthListener();

    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccountButton) {
            Toast.makeText(CreateAccountActivity.this, "Creating Account", Toast.LENGTH_LONG).show();
        }
        if (v == mLogIn) {
            Intent intent = new Intent(CreateAccountActivity.this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void loadingScreen() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Creating Account.....");
        progressDialog.setCancelable(false);
    }

    private void createAuthListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createUser(){
        final String name = mUserName.getText().toString().trim();
        final String email = mUserEmail.getText().toString().trim();
        final String password = mUserPassword.getText().toString().trim();
        final String confirmPassword = mUserConfirmPassword.getText().toString().trim();

        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password){

        }
    }
}