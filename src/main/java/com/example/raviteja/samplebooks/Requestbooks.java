package com.example.raviteja.samplebooks;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.app.AppCompatActivity;


public class Requestbooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestbooks);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addbooksactionbar, menu);
        return true;
    }
}
