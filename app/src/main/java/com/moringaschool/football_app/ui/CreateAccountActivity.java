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
import com.google.firebase.auth.UserProfileChangeRequest;
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

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mCreateAccountButton.setOnClickListener(this);
        mLogIn.setOnClickListener(this);

        loadingScreen();
        createAuthListener();

    }

    @Override
    public void onClick(View v) {
        if (v == mCreateAccountButton) {
            createUser();
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

    private void createAuthListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createUser() {
        final String name = mUserName.getText().toString().trim();
        final String email = mUserEmail.getText().toString().trim();
        final String password = mUserPassword.getText().toString().trim();
        final String confirmPassword = mUserConfirmPassword.getText().toString().trim();

        mName = mUserName.getText().toString().trim();

        if(!isValidEmail(email)|| !isValidName(name) ||!isValidPassword(password, confirmPassword)){
            return;
        }

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            createFirebaseUserProfile(task.getResult().getUser());
                        } else {
                            Toast.makeText(CreateAccountActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);

    }

    private void createFirebaseUserProfile(final FirebaseUser user) {
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(mUserName.getText().toString().trim())
                .build();

        user.updateProfile(userProfileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

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

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserName.setError("Enter valid name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mUserPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mUserConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}