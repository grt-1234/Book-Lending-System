package com.example.raviteja.samplebooks;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static int RC_SIGN_IN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        loadfragment(new homeclass());
        BottomNavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null)
                {
                    Toast.makeText(MainActivity.this, "yay logged in ",Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()
                                            ))
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
              //  Toast.makeText(this,"home clicked", Toast.LENGTH_SHORT).show();
                fragment=new homeclass();
            break;

            case R.id.navigation_settings:
              //  Toast.makeText(this,"settings clicked", Toast.LENGTH_LONG).show();
                //Context context = this;
                //Bundle bundle = new Bundle();
                fragment=new settingsclass();
                //fragment.setArguments(bundle);
                break;

            case R.id.navigation_mybooks:
               // Toast.makeText(this,"my book clicked", Toast.LENGTH_LONG).show();
                fragment=new mybooksclass();
                break;
        }
        return loadfragment(fragment);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId())
        {
            case R.id.addbooks:
                intent = new Intent(this,addbooksactivity.class);
               // Toast.makeText(this,"addbooks clicked", Toast.LENGTH_LONG).show();
                startActivity(intent);
                break;
            case R.id.borrow:
                intent = new Intent(MainActivity.this, Requestbooks.class);
                //Toast.makeText(this,"request clicked", Toast.LENGTH_LONG).show();
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    public boolean loadfragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            //Toast.makeText(this, "fragment clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
            return false;
    }
}
