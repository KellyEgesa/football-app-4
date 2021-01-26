package com.moringaschool.football_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.football_app.R;

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
            String user = mUserName.getText().toString();
            if(!user.equals("")){
                Intent intent = new Intent(MainActivity.this, LeaguesActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome: "+user, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this, "KINDLY ENTER YOUR NAME!!!", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout_action){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}