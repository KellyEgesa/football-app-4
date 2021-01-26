package com.moringaschool.football_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        mLogInButton.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        loadingScreen();
        checkAuthListener();

    }

    @Override
    public void onClick(View v) {
        if (v == mLogInButton) {
            logIn();
        }
        if (v == mCreateAccount) {
            Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    private void loadingScreen() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in");
        progressDialog.setMessage("Authenticating.....");
        progressDialog.setCancelable(false);
    }

    private void checkAuthListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void logIn() {
        String email = mUserEmail.getText().toString().trim();
        String password = mUserPassword.getText().toString().trim();

        if (!isValidEmail(email) || !isValidPassword(password)) {
            return;
        }

        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    private boolean isValidEmail(String email) {
        boolean isEmailValid = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isEmailValid) {
            mUserEmail.setError("Enter valid email");
            return false;
        }
        return isEmailValid;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            mUserPassword.setError("Please create a password containing at least 6 characters");
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
}